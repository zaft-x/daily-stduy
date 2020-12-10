package com.study.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.study.constant.RegexConstant;

/**
 * @Author x.zaft
 * @Date 2020/12/9
 * @Version
 **/
public class ValidateExpandUtil {
    public static boolean valAddress(String regex, String value) {
        return match(regex, value);
    }

    public static boolean valEmail(String value) {
        return match(RegexConstant.V_EMAIL, value);
    }

    public static boolean valDate(String regex, String value) {
        return match(regex, value);
    }

    public static boolean valPhone(String value) {
        return match(RegexConstant.V_PHONE, value);
    }

    public static boolean valNumber(String regex, String value) {
        return match(regex, value);
    }

    private static boolean match(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }


    public static boolean valIDCard(String value) {
        return IDCardUtil.isIDCard(value);
    }

}
