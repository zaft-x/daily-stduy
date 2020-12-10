package com.study.easyexcel.listener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.fastjson.JSONObject;
import com.study.easyexcel.pojo.example.ExcelRow;
import com.study.pojo.base.ImportResult;
import com.study.easyexcel.service.ExcelService;
import com.study.util.StringExpandUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 导入监听
 *
 * @description:
 * @author: zaft_x
 * @time: 2020/12/1
 */
@Slf4j
public class ExcelAnalysisImportListener<T extends ExcelRow, E> extends AnalysisEventListener<T> {

    @Getter
    private final List<T> rowList;

    @Getter
    private ImportResult importResDto;

    private ExcelService excelService;

    private final Class<T> tClass;

//    private final JwtParam jwtParam;

    private E e;

    private static final int MAX_ROW_LIMIT = 1000;

    Set<String> repeats = Collections.synchronizedSet(new HashSet());

    public ExcelAnalysisImportListener(Class<T> tClass, ExcelService importService, /*JwtParam jwtParam,*/ E e) {
        rowList = new ArrayList<>();
        this.tClass = tClass;
        this.excelService = importService;
//        this.jwtParam = jwtParam;
        this.e = e;
    }

    /**
     * 重写invoke方法获得除Excel第一行表头之后的数据，
     * @param row 数据
     * @param analysisContext 上下文
     */
    @Override
    public void invoke(T row, AnalysisContext analysisContext) {
        this.analysisRow(row);
        rowList.add(row);
        if (rowList.size() > MAX_ROW_LIMIT) {
            throw new RuntimeException("最大不能超过%s条数据");
        }
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        // 得到请求头的数据.
        LinkedList<String> headList = new LinkedList<>(headMap.values());
        List<String> afterProcessList = headList.stream().filter(h -> !Objects.isNull(h))
                .map(head -> head.replace("*", "")).collect(Collectors.toList());
        Field[] fields = tClass.getDeclaredFields();
        List<String> fieldList = new ArrayList<>();
        for (Field f : fields) {
            if (f.isAnnotationPresent(ExcelProperty.class)) {
                ExcelProperty excelProperty = f.getAnnotation(ExcelProperty.class);
                fieldList.add(excelProperty.value()[0].replace("*", ""));
            }
        }
        for (String h : afterProcessList) {
            if (!fieldList.contains(h)) {
                throw new RuntimeException("表头[" + h + "]不存在");
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        importResDto = excelService.deal(rowList, /*jwtParam,*/ e);
    }

    /**
     * 解析数据
     */
    private void analysisRow(T row) {
        boolean repeat = true;
        try {
            repeat = repeatValidate(row);
        } catch (Exception ex) {
            log.info("====重复验证异常====" + ex);
        }
        if (repeat) {
            excelService.analiesSingle(row, e);
        }
    }


    private boolean repeatValidate(T row) throws Exception {
        if (repeats.contains(JSONObject.toJSONString(row))) {
            Field dataMapField = row.getClass().getSuperclass().getDeclaredField(StringExpandUtil.DATA_MAP);
            dataMapField.setAccessible(true);
            Map<String, Object> dataMap = (Map<String, Object>) dataMapField.get(row);
            if (null == dataMap) {
                dataMap = new HashMap<>(8);
            }
            dataMap.put("pepeat", true);
            dataMapField.set(row, dataMap);
            return false;
        }
        return true;
    }


    /**
     * 出现异常回调
     *
     * @param exception 异常
     * @param context 内容
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        // ExcelDataConvertException:当数据转换异常的时候，会抛出该异常，此处可以得知第几行，第几列的数据
        if (exception instanceof ExcelDataConvertException) {
            int columnIndex = ((ExcelDataConvertException) exception).getColumnIndex() + 1;
            int rowIndex = ((ExcelDataConvertException) exception).getRowIndex() + 1;
            String message = "第" + rowIndex + "行，第" + columnIndex + "列" + "数据格式有误，请核实";
            log.error("#SocExcelAnalysisImportListener#onException excel导入发生异常 message={}", message);

        } else if (exception instanceof RuntimeException) {
            log.error("#SocExcelAnalysisImportListener#onException excel导入发生运行时异常 message={}",
                    exception.getLocalizedMessage());
            throw new RuntimeException(exception.getLocalizedMessage());
        } else {
            log.error("#SocExcelAnalysisImportListener#onException excel导入发生未知异常 message={}",
                    exception.getLocalizedMessage());
            throw new RuntimeException(exception.getLocalizedMessage());
        }
        throw new RuntimeException("Excel系统导入异常");
    }

}
