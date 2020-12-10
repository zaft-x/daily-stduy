package com.study.pojo.base;
import lombok.Data;

/**
 *
 *@description: 
 *@author: zaft_x
 *@time: 2020/12/10
 * 
 */
@Data
public class ImportResult {

    private Long fileId;

    /**生成文件地址*/
    private String fileUrl;

    /**导入成功条数*/
    private Integer success = 0;

    /**失败条数*/
    private Integer fail = 0;

    /**导入总条数*/
    private Integer total = 0;


}
