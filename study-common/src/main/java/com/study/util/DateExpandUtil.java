package com.study.util;

import java.util.UUID;

/**
 * date extension util
 * @Author x.zaft
 * @Date 2020/12/9
 * @Version
 **/
public class DateExpandUtil {
    public static final String F_DATE_1 = "yyyy/MM";
    public static final String F_DATE_2 = "yyyy/MM/dd";
    public static final String F_DATE_3 = "yyyy/MM/dd HH";
    public static final String F_DATE_4 = "yyyy/MM/dd HH:mm";
    public static final String F_DATE_5 = "yyyy/MM/dd HH:mm:ss";
    public static final String F_DATE_6 = "yyyyMMddHHmmss";
    public static final String F_DATE_7 = "yyyy-MM-dd HH:mm:ss";
    public static final String F_DATE_8 = "yyyy-MM-dd HH:mm";
    public static final String F_DATE_9 = "yyyy-MM-dd HH";
    public static final String F_DATE_10 = "yyyy-MM-dd";
    public static final String F_DATE_11 = "yyyy-MM";
    public static final String F_DATE_12 = "yyyy-MM-dd HH:mm:ss:SSS";


    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(UUID.randomUUID());
    }
}
