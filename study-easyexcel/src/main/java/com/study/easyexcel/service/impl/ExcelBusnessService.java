package com.study.easyexcel.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.study.easyexcel.annotation.ExcelAnalyze;
import com.study.easyexcel.pojo.example.ExcelImportDto;
import com.study.easyexcel.pojo.example.ExcelImportErrorDto;
import com.study.easyexcel.pojo.example.ImportParamDto;
import com.study.pojo.base.ImportResult;
import com.study.easyexcel.service.ExcelService;
import com.study.util.ObjectUtil;

/**
 * @Author x.zaft
 * @Date 2020/12/10
 * @Version
 **/
@Component
public class ExcelBusnessService extends ExcelService {

    @Override
    @ExcelAnalyze
    public void analiesSingle(Object o, Object o1) {
        super.analiesSingle(o, o1);
    }

    @Override
    public ImportResult deal(List rows, /*JwtParam jwtParam,*/ Object o) {
        List<ExcelImportDto> importList = (List<ExcelImportDto>)rows;
        ImportParamDto params = (ImportParamDto)o;

        ImportResult importResult = new ImportResult();
        System.out.println("====="+ JSONObject.toJSONString(rows));
        List<ExcelImportErrorDto> errorList = importList.parallelStream().filter(entity -> {
            //基础验证
            if (entity.getValidateCode().equals(Boolean.FALSE)) {
                return true;
            }
            return true;
        }).map(this::assemblyBean).collect(Collectors.toList());
        System.out.println("======="+JSONObject.toJSONString(errorList));
        importResult.setFail(errorList.size());
        importResult.setSuccess(rows.size()-errorList.size());
        importResult.setTotal(rows.size());
        return importResult;
    }

    private ExcelImportErrorDto assemblyBean(ExcelImportDto excelImportDto) {
        ExcelImportErrorDto errorDto = ObjectUtil.copy(excelImportDto, ExcelImportErrorDto.class);
        errorDto.setErrorMsg(excelImportDto.getErrorMsg());
        return errorDto;
    }

}
