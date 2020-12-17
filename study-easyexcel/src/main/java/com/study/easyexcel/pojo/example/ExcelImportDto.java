package com.study.easyexcel.pojo.example;

import com.alibaba.excel.annotation.ExcelProperty;
import com.study.constant.RegexConstant;
import com.study.easyexcel.annotation.ExcelAnalyze;
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
public class ExcelImportDto extends ExcelRow {

    @ExcelProperty(value = "*客户名称")
    @ExcelAnalyze(nonEmpty = true)
    private String custName;

    @ExcelProperty(value = "*签约套餐")
    @ExcelAnalyze(nonEmpty = true)
    private String comboName;

    @ExcelProperty(value = "*合同编号")
    @ExcelAnalyze(nonEmpty = true)
    private String contractCode;

    @ExcelProperty(value = "*姓名")
    @ExcelAnalyze(nonEmpty = true)
    private String name;

    @ExcelProperty(value = "*国籍")
    @ExcelAnalyze(nonEmpty = true, formatCategory = 6, message = "国籍")
    private String nationalityName;

    @ExcelProperty(value = "*证件类型")
    @ExcelAnalyze(nonEmpty = true, formatCategory = 5, format = "staff.staff_info.credentialsType", message = "证件类型")
    private String credentialsTypeName;

    @ExcelProperty(value = "*证件号码")
    @ExcelAnalyze(nonEmpty = true, formatCategory = 9, message = "证件号码")
    private String credentialsCode;

    /** 枚举 */
    @ExcelProperty(value = "*性别")
    @ExcelAnalyze(nonEmpty = true, index = 7, formatCategory = 5, format = "staff.staff_info.sex", message = "性别", perset = {"男","女"})
    private String sexName;

    @ExcelProperty(value = "*出生日期")
    @ExcelAnalyze(nonEmpty = true, formatCategory = 0, format = RegexConstant.V_DATE_1, message = "出生日期")
    private String birthDate;

    @ExcelProperty(value = "*手机号码")
    @ExcelAnalyze(nonEmpty = true, formatCategory = 3, message = "手机号码")
    private String phone;

    @ExcelProperty(value = "*参保地")
    @ExcelAnalyze(nonEmpty = true, formatCategory = 99, format = RegexConstant.V_R_ENGLISH_LOT, message = "参保地")
    private String insuredAddress;

    /** 枚举 */
    @ExcelProperty(value = "*户籍性质")
    @ExcelAnalyze(nonEmpty = true, message = "户籍性质")
    private String censusName;

    @ExcelProperty(value = "*缴纳标准")
    @ExcelAnalyze(nonEmpty = true, message = "缴纳标准")
    private String payStandardName;

    @ExcelProperty(value = "备注")
    private String remarks;

    @ExcelProperty(value = "社保政策包")
    private String socPolicyName;

    @ExcelProperty(value = "*当地是否首次参保社保")
    @ExcelAnalyze(nonEmpty = true, formatCategory = 5, format = "staff.staff_info.condition", message = "当地是否首次参保社保")
    private String socInsuredFirst;

    /** 多个以英文逗号分隔 */
    @ExcelProperty(value = "社保可选险种")
    private String socInsuranceNames;

    @ExcelProperty(value = "*社保起缴月")
    @ExcelAnalyze(nonEmpty = true, formatCategory = 0, format = RegexConstant.V_DATE_0, message = "社保起缴月")
    private String socStartMonth;

    @ExcelProperty(value = "*社保基数")
    @ExcelAnalyze(nonEmpty = true, formatCategory = 4, format = RegexConstant.V_NUMBER_POSITIVE_TOW_LOT,
            message = "社保基数")
    private String socInsuredRadix;

    @ExcelProperty(value = "公积金政策包")
    private String accPolicyName;

    @ExcelProperty(value = "当地是否首次参保公积金")
    @ExcelAnalyze(nonEmpty = true, formatCategory = 5, format = "staff.staff_info.condition", message = "当地是否首次参保公积金")
    private String accInsuredFirst;

    /** 多个以英文逗号分隔 */
    @ExcelProperty(value = "公积金可选险种")
    private String accInsuranceNames;

    @ExcelProperty(value = "公积金起缴月")
    @ExcelAnalyze(formatCategory = 0, format = RegexConstant.V_DATE_0, message = "公积金起缴月")
    private String accStartMonth;

    @ExcelProperty(value = "公积金基数")
    @ExcelAnalyze(formatCategory = 4, format = RegexConstant.V_NUMBER_POSITIVE_TOW_LOT, message = "社保基数")
    private String accInsuredRadix;

    @ExcelProperty(value = "住房公积金比例（%）(企：个）")
    @ExcelAnalyze(formatCategory = 11 ,format = RegexConstant.V_R_ENGLISH_COLON, message = "住房公积金比例（%）(企：个）")
    private String accRate;

    @ExcelProperty(value = "证件开始时间")
    @ExcelAnalyze(formatCategory = 0, format = RegexConstant.V_DATE_0, message = "证件开始时间")
    private String credentialsStartTime;

    @ExcelProperty(value = "证件结束时间")
    @ExcelAnalyze(formatCategory = 0, format = RegexConstant.V_DATE_0, message = "证件结束时间")
    private String credentialsEndTime;

    /** 枚举 */
    @ExcelProperty(value = "民族")
    @ExcelAnalyze(formatCategory = 8, message = "民族")
    private String ethnicName;

    @ExcelProperty(value = "邮箱")
    @ExcelAnalyze(formatCategory = 2, message = "邮箱")
    private String email;

    @ExcelProperty(value = "婚姻状况")
    @ExcelAnalyze(formatCategory = 5, format = "staff.staff_info.marriage", message = "婚姻状况")
    private String marriageName;

    @ExcelProperty(value = "来华时间")
    @ExcelAnalyze(formatCategory = 0, format = RegexConstant.V_DATE_0, message = "来华时间")
    private String comeChinaTime;

    /** 枚举 */
    @ExcelProperty(value = "干部身份")
    private String cadreStatusName;

    /** 枚举 */
    @ExcelProperty(value = "政治面貌")
    @ExcelAnalyze(formatCategory = 5, format = "staff.staff_info.politicalStatus", message = "政治面貌")
    private String politicalStatusName;

    /** 枚举 */
    @ExcelProperty(value = "是否残疾人")
    @ExcelAnalyze(formatCategory = 5, format = "staff.staff_info.condition", message = "是否残疾人")
    private String disabledPersonName;

    @ExcelProperty(value = "残疾人证号")
    private String disabledPersonCode;

    @ExcelProperty(value = "户籍地邮编")
    private String censusZipCode;

    /** 省  详细地址 */
    @ExcelProperty(value = "户籍地省市")
    private String censusProvinceCity;

    @ExcelProperty(value = "户籍详细地址")
    private String censusAddress;

    @ExcelProperty(value = "通讯地邮编")
    private String communicationZipCode;

    /** 省  详细地址 */
    @ExcelProperty(value = "通讯地省市")
    private String communicationProvinceCity;

    @ExcelProperty(value = "通讯地详细地址")
    private String communicationAddress;

    @ExcelProperty(value = "入户时间")
    @ExcelAnalyze(formatCategory = 0, format = RegexConstant.V_DATE_1, message = "入户时间")
    private String entryCensusTime;

    @ExcelProperty(value = "毕业院校")
    private String school;

    @ExcelProperty(value = "毕业时间")
    @ExcelAnalyze(formatCategory = 0, format = RegexConstant.V_DATE_1, message = "毕业时间")
    private String graduationTime;

    @ExcelProperty(value = "所学专业")
    private String specialty;

    /** 枚举 */
    @ExcelProperty(value = "学历")
    @ExcelAnalyze(formatCategory = 5, format = "staff.staff_education.education", message = "学历")
    private String educationName;

    /** 枚举 */
    @ExcelProperty(value = "学位")
    @ExcelAnalyze(formatCategory = 5, format = "staff.staff_education.degree", message = "学位")
    private String degreeName;

    /** 省 市 区 */
    @ExcelProperty(value = "工作地址")
    @ExcelAnalyze(formatCategory = 1, format = RegexConstant.V_R_ENGLISH_LOT, message = "工作地址")
    private String workAddress;

    /** 枚举 */
    @ExcelProperty(value = "岗位类别")
    @ExcelAnalyze(formatCategory = -1, format = "", message = "岗位类别")
    private String postCategoryName;

    @ExcelProperty(value = "职位")
    private String postName;

    @ExcelProperty(value = "员工合同起始时间")
    @ExcelAnalyze(formatCategory = 0, format = RegexConstant.V_DATE_1, message = "员工合同起始时间")
    private String contractStartTime;

    @ExcelProperty(value = "员工合同终止时间")
    @ExcelAnalyze(formatCategory = 0, format = RegexConstant.V_DATE_1, message = "员工合同终止时间")
    private String contractEndTime;

    @ExcelProperty(value = "参加工作时间")
    @ExcelAnalyze(formatCategory = 0, format = RegexConstant.V_DATE_1, message = "参加工作时间")
    private String firstTimeWorking;

    @ExcelProperty(value = "专业技术等级")
    private String technicalLevel;

    @ExcelProperty(value = "上家公司名称")
    private String lastCompanyName;

    @ExcelProperty(value = "上家公司账号")
    private String lastCompanyAccount;

    @ExcelProperty(value = "定点医院名称")
    private String designatedHospital;

    @ExcelProperty(value = "开户银行")
    @ExcelAnalyze(formatCategory = 10, message = "开户银行")
    private String bankName;

    /** 省 市 */
    @ExcelProperty(value = "开户地")
    private String openProvince;

    @ExcelProperty(value = "支行名称")
    private String openBank;

    @ExcelProperty(value = "银行卡号")
    private String bankCardAccount;

}
