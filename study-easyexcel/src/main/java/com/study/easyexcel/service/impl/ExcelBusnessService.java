package com.study.easyexcel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.study.easyexcel.annotation.ExcelAnalyze;
import com.study.easyexcel.handler.RowWriteHandler;
import com.study.easyexcel.pojo.example.ExcelImportDto;
import com.study.easyexcel.pojo.example.ExcelImportErrorDto;
import com.study.easyexcel.pojo.example.ImportParamDto;
import com.study.pojo.base.ImportResult;
import com.study.easyexcel.service.ExcelService;
import com.study.util.ExcelUtil;
import com.study.util.ObjectUtil;

/**
 * @Author x.zaft
 * @Date 2020/12/10
 * @Version
 **/
@Component
public class ExcelBusnessService<T, E> extends ExcelService {

    @Override
    @ExcelAnalyze
    public void analiesSingle(Object o, Object o1) {
        super.analiesSingle(o, o1);
    }

    @Override
    public ImportResult deal(List rows, /*JwtParam jwtParam,*/ Object o) {
        List<ExcelImportDto> importList = (List<ExcelImportDto>) rows;
        ImportParamDto params = (ImportParamDto) o;

        ImportResult importResult = new ImportResult();
        System.out.println("=====" + JSONObject.toJSONString(rows));
        List<ExcelImportErrorDto> errorList = importList.parallelStream().filter(entity -> {
            //基础验证
            if (entity.getValidateCode().equals(Boolean.FALSE)) {
                return true;
            }
            return true;
        }).map(this::assemblyBean).collect(Collectors.toList());
        System.out.println("=======" + JSONObject.toJSONString(errorList));
        createErrorFile(errorList, ExcelImportErrorDto.class);
        importResult.setFail(errorList.size());
        importResult.setSuccess(rows.size() - errorList.size());
        importResult.setTotal(rows.size());
        return importResult;
    }

    private void createErrorFile(List<?> errorList, Class<?> clazz) {
        String fileName = "D:\\excel\\" + System.currentTimeMillis() + ExcelUtil.EXCEL_SUFFIX_XLSX;
        System.out.println("=====file url======"+fileName);
        EasyExcel.write(fileName, clazz).sheet("sheet-0").registerWriteHandler(new RowWriteHandler())
                .doWrite(new ArrayList());
    }

    private ExcelImportErrorDto assemblyBean(ExcelImportDto excelImportDto) {
        ExcelImportErrorDto errorDto = ObjectUtil.copy(excelImportDto, ExcelImportErrorDto.class);
        errorDto.setErrorMsg(excelImportDto.getErrorMsg());
        return errorDto;
    }

}
