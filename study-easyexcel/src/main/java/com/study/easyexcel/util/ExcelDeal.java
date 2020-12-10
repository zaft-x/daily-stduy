package com.study.easyexcel.util;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.study.easyexcel.listener.ExcelAnalysisImportListener;
import com.study.easyexcel.pojo.example.ExcelRow;
import com.study.pojo.base.ImportResult;
import com.study.easyexcel.service.ExcelService;
import lombok.extern.slf4j.Slf4j;

/**
 *
 *@description: 
 *@author: zaft_x
 *@time: 2020/12/10
 * 
 */
@Slf4j
public class ExcelDeal {


    public static <T extends ExcelRow, E> ImportResult read(Class<T> tClass, /*JwtParam jwtParam,*/
            ExcelService importService, Integer sheetNo, MultipartFile multipartFile, E e) {

        // 不传默认读取第一个sheet的单元.
        sheetNo = sheetNo == null ? 0 : sheetNo;
        // 具体逻辑处理.
        ExcelAnalysisImportListener<T, E> excelListener = new ExcelAnalysisImportListener<>(tClass, importService,
                /*jwtParam,*/ e);
        ExcelReader excelReader = null;
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            excelReader = EasyExcel.read(inputStream).build();
            // 读取当前的sheet的单元数据.
            ReadSheet readSheet = EasyExcel.readSheet(sheetNo).head(tClass).registerReadListener(excelListener).build();
            excelReader.read(readSheet);
        } catch (IOException ignored) {
            log.error("#ExcelDeal#read解析excel文件异常");
        } finally {
            // 关闭流
            try {
                assert inputStream != null;
                inputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // 这里一定别忘记关闭，读的时候会创建临时文件，到时磁盘会崩
            if (excelReader != null) {
                excelReader.finish();
            }
        }
        return excelListener.getImportResDto();
    }

}
