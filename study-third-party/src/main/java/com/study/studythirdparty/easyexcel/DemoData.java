package com.study.studythirdparty.easyexcel;

import java.util.Date;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName DemoData
 * @Description: TODO
 * @Author x.zaft
 * @Date 2020/10/20
 * @Version V1.0
 **/
@Data
public class DemoData {
    @ExcelProperty("字符串标题")
    private String string;

    @ExcelProperty("数字标题")
    private Double doubleData;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("是否必须")
    private String hook;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
