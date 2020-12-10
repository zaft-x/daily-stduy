package com.study.easyexcel.service.impl;

import org.springframework.stereotype.Component;
import com.study.easyexcel.service.ExcelService;

/**
 * @Author x.zaft
 * @Date 2020/12/3
 * @Version v
 **/
@Component
public class InsuredExcelService extends ExcelService {


   /* public ImportServiceResDto deal(List rows, JwtParam jwtParam, Object o) {
        List<InsuredExcelImportDto> importList = (List<InsuredExcelImportDto>)rows;
        InsuredImportParamDto params = (InsuredImportParamDto)o;
        System.out.println("====="+JSONObject.toJSONString(rows));
        importList.parallelStream().filter(entity->{
            //基础验证
            if (entity.getValidateCode().equals(Boolean.FALSE)){
                return true;
            }
            return true;
        }).map(this::assemblyBean).collect(Collectors.toList());
        return null;
    }

    public InsuredExcelimportErrorDto assemblyBean(InsuredExcelImportDto entity){
        InsuredExcelimportErrorDto errorDto = ObjectUtil.copy(entity, InsuredExcelimportErrorDto.class);
        errorDto.setErrorMsg(entity.getValidateMessage());
        return errorDto;
    }*/

}
