package com.study.util;

/**
 * @Author x.zaft
 * @Date 2020/12/10
 * @Version
 **/
public class ExcelUtil {

    public static void main(String[] args) {
        System.out.println(excelColIndexToStr(1));
        System.out.println(excelColIndexToStr(2));
        System.out.println(excelColIndexToStr(9));
        System.out.println(excelColIndexToStr(44));

    }

    public static final String EXCEL_SUFFIX_XLSX = ".xlsx";

    /**
     * Excel column index begin 1
     */
    public static int excelColStrToNum(String colStr, int length) {
        int num = 0;
        int result = 0;
        for (int i = 0; i < length; i++) {
            char ch = colStr.charAt(length - i - 1);
            num = (ch - 'A' + 1);
            num *= Math.pow(26, i);
            result += num;
        }
        return result;
    }

    /**
     * Excel column index begin 1
     */
    public static String excelColIndexToStr(int columnIndex) {
        if (columnIndex <= 0) {
            return null;
        }
        String columnStr = "";
        columnIndex--;
        do {
            if (columnStr.length() > 0) {
                columnIndex--;
            }
            columnStr = ((char) (columnIndex % 26 + (int) 'A')) + columnStr;
            columnIndex = ((columnIndex - columnIndex % 26) / 26);
        } while (columnIndex > 0);
        return columnStr;
    }

}
