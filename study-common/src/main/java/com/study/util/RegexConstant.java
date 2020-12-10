package com.study.util;

/**
 * @Author x.zaft
 * @Date 2020/12/9
 * @Version
 **/
public class RegexConstant {
    public static final String KEY_ENGLISH_LOT = "[,]";
    public static final String V_ENGLISH_LOT = "^.*[\\,，].*$";
    public static final String V_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
    public static final String V_DATE_1 = "((19|20)[0-9]{2})/(0[1-9]|1[012])";
    public static final String V_PHONE = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,5,8,9]))[0-9]{8}$";
    public static final String V_NUMBER_POSITIVE_TOW_LOT = "^([0-9]{1}|[1-9]+\\d*)(.{0,1}\\d{1,2})?$";

}
