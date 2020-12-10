package com.study.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.ToString;

/**
 * 增员导入excel
 *
 * @Author x.zaft
 * @Date 2020/10/21
 * @Version v2.4
 **/
@Data
@ToString
public class ExcelEntityTest {

    @Excel(name = "*客户名称")
    private String custName;

    @Excel(name = "*名称")
    @ExcelPreset(index = 1, value = {"男人", "女人"}, rows = 2)
    private String test;

    @Excel(name = "*性别")
    @ExcelPreset(index = 2, value = {"男", "女"}, rows = 2)
    private String sexName;

    @Excel(name = "test")
    @ExcelPreset(index = 3, value = {"儿童","成人","老人"})
    private String type;

}
