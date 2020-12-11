package com.study.easypoi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Workbook;
import com.study.util.ExcelUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author x.zaft
 * @Date 2020/12/10
 * @Version
 **/
@Slf4j
public class ExcelEasyPoi {


    public static void main(String[] args) {

        String fileName = "D:\\excel\\" + System.currentTimeMillis() + ExcelUtil.EXCEL_SUFFIX_XLSX;
        Workbook workbook = ExportUtil
                .createExcelTemplate(new ExportParams(), ExcelEntityTest.class, new ArrayList<>());

        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            log.error("创建xls文件失败, msg:{}", e.getMessage());
        }finally {
            System.out.println("======="+fileName);
        }

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
        } catch (Exception e) {
            log.error("error:", e);
        } finally {
//            file.delete();
        }
    }

}
