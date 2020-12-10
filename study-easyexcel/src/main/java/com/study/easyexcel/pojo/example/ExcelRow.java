package com.study.easyexcel.pojo.example;

import java.util.Map;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 基础“行”对象
 */

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ExcelRow extends BaseRowModel {

    private static final int SUCCESS_CODE = 0;

    /**
     * 所属行数，从0开始
     */
    @ExcelIgnore
    private int rowNum = SUCCESS_CODE;

    /**
     * 数据解析后存储
     */
    private Map<String,Object>  dataMap;

    /**
     * 校验码，当承载“行”的对象有设置注解，且校验不通过时，会将结果放置于此字段
     */
    @ExcelIgnore
    private Boolean validateCode = Boolean.TRUE;

    /**
     * 校验消息，业务尽量使用校验码做判断
     */
    @ExcelIgnore
    private String errorMsg;


    /**
     * 验证失败的情况
     *
     * @param message 失败提示信息.
     */
    public void error(String message) {
        this.validateCode = Boolean.FALSE;
        this.errorMsg = message;
    }

}
