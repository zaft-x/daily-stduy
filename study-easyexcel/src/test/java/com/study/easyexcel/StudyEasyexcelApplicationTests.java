package com.study.easyexcel;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.study.easyexcel.handler.SpinnerWriteHandler;
import com.study.easyexcel.pojo.example.ExcelImportDto;
import com.study.easyexcel.pojo.example.FormulaTestDto;
import com.study.easyexcel.pojo.example.ImportFileDto;
import com.study.easyexcel.pojo.example.ImportParamDto;
import com.study.pojo.base.ImportResult;
import com.study.easyexcel.service.impl.ExcelBusnessService;
import com.study.easyexcel.util.ExcelDeal;
import com.study.util.ExcelUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudyEasyexcelApplicationTests {

    @Autowired
    private ExcelBusnessService excelService;

    @Test
    public void formula(){
        List<FormulaTestDto> list = new ArrayList<>();
        FormulaTestDto dto = new FormulaTestDto();
        dto.setIndex1(new BigDecimal(1));
        dto.setIndex2(new BigDecimal(1));
        list.add(dto);
        String fileName = "D:\\excel\\" + System.currentTimeMillis() + ExcelUtil.EXCEL_SUFFIX_XLSX;
        System.out.println("=====file url======"+fileName);
        EasyExcel.write(fileName, FormulaTestDto.class).sheet("sheet-0").registerWriteHandler(new SpinnerWriteHandler(FormulaTestDto.class, 1, -1))
                .doWrite(list);
    }


    @Test
    public void setPerset(){

        List<ExcelImportDto> list = new ArrayList<>();
        ExcelImportDto dto = new ExcelImportDto();
        dto.setName("test-001");
        list.add(dto);
        dto = new ExcelImportDto();
        dto.setName("test-002");
        list.add(dto);

        String fileName = "D:\\excel\\" + System.currentTimeMillis() + ExcelUtil.EXCEL_SUFFIX_XLSX;
        System.out.println("=====file url======"+fileName);
        EasyExcel.write(fileName, ExcelImportDto.class).sheet("sheet-0").registerWriteHandler(new SpinnerWriteHandler(ExcelImportDto.class, 3))
                .doWrite(list);
    }

    @Test
    public void test() throws Exception {
        ImportFileDto request = new ImportFileDto();
        File file = new File("D:\\tools\\E-Mobile\\Download\\000.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), null, inputStream);
        request.setFile(multipartFile);
        ImportResult read = ExcelDeal
                .read(ExcelImportDto.class, /*jwtParam,*/excelService, null, request.getFile(), new ImportParamDto());


        System.out.println(JSONObject.toJSONString(read));

    }

}
