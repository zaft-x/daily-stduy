package com.study.constant;

/**
 * @Author x.zaft
 * @Date 2020/12/9
 * @Version
 **/
public class RegexConstant {

    /** email */
    public static final String V_EMAIL =
            "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\" + ".[A-Za-z0-9]+$";

    /** mobile phone */
    public static final String V_PHONE =
            "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|" + "([9][1,5,8,9]))[0-9]{8}$";

    /** date  yyyy/MM */
    public static final String V_DATE_0 = "((19|20)[0-9]{2})/(0[1-9]|1[012])";

    /** date  yyyy/MM/dd */
    public static final String V_DATE_1 = "((19|20)[0-9]{2})/(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])";

    /** is contain lot-english */
    public static final String V_R_ENGLISH_LOT = "^.*[\\,].*$";

    /** is contail lolon-english */
    public static final String V_R_ENGLISH_COLON = "^.*[\\:].*$";

    /** 0<= bunber <= 100.00 000.00 */
    public static final String v_number_colon = "^(100|(([1-9]){1}[0-9]?|0{1})(.{0,1}\\d{1,2})?$";

    /** positive two decimal number */
    public static final String V_NUMBER_POSITIVE_TOW_LOT = "^([0-9]{1}|[1-9]+\\d*)(.{0,1}\\d{1,2})?$";

}
