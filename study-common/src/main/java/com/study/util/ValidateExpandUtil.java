package com.study.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return match("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", value);
    }

    public static boolean valDate(String regex, String value) {
        return match(regex, value);
    }

    public static boolean valPhone(String value) {
        return match("^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,5,8,9]))[0-9]{8}$", value);
    }

    public static boolean valNumber(String regex, String value) {
        return match(regex, value);
    }

    private static boolean match(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
