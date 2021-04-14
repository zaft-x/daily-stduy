package com.study.easyexcel.pojo.example;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelProperty;
import com.study.easyexcel.annotation.ExcelAnalyze;
import lombok.Data;
import lombok.ToString;

/**
 * 公式调试class
 *
 * @Author x.zaft
 * @Date 2021/4/14
 * @Version
 **/
@Data
@ToString
public class FormulaTestDto {

    @ExcelProperty(value = "数值1")
    @ExcelAnalyze(index = 0)
    private BigDecimal index1;

    @ExcelAnalyze(index = 1)
    @ExcelProperty(value = "数值2")
    private BigDecimal index2;

    @ExcelAnalyze(index = 2)
    @ExcelProperty(value = "数值3")
    private BigDecimal index3;

    @ExcelProperty(value = "数值4")
    @ExcelAnalyze(index = 3, formula = {0, 1})
    private BigDecimal index4;

}
