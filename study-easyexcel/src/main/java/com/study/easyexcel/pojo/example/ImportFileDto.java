package com.study.easyexcel.pojo.example;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

/**
 * 客户增员导入
 * @Author x.zaft
 * @Date 2020/11/3
 * @Version v2.4
 **/
@Data
public class ImportFileDto extends ImportParamDto {

    /**
     * 文件
     */
    private MultipartFile file;



}
