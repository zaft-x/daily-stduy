package com.study.easypoi;

import java.lang.reflect.Field;
import java.util.Collection;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.export.ExcelExportService;

/**
 * @Author x.zaft
 * @Date 2020/12/10
 * @Version
 **/
public class ExportUtil {

    public static int USE_SXSSF_LIMIT = 100000;

    public static int PRESET_ROWS = 1000;

    public static int EXCEL_MAX_ROW = 65535;

    /**
     * @param entity 表格标题属性
     * @param pojoClass Excel对象Class
     * @param dataSet Excel对象数据List
     */
    public static Workbook createExcelTemplate(ExportParams entity, Class<?> pojoClass, Collection<?> dataSet) {
        Workbook workbook = getWorkbook(entity.getType(), dataSet.size());
        new ExcelExportService().createSheet(workbook, entity, pojoClass, dataSet);
        initPerset(workbook, pojoClass, 1);
        return workbook;
    }

    private static void initPerset(Workbook workbook, Class<?> t, int startRow) {
        Field[] declaredFields = t.getDeclaredFields();
        int hidden = 0;
        for (Field declaredField : declaredFields) {
            ExcelPreset excelPreset = declaredField.getAnnotation(ExcelPreset.class);
            if (null == excelPreset || null == excelPreset.value()) {
                continue;
            }
            initPerset(workbook, startRow, excelPreset.rows(), excelPreset.index(), excelPreset.value(),
                    ++hidden);
        }
    }

    private static void initPerset(Workbook workbook, int startRow, int rows, int col, String[] dataArray,
            int sheetHidden) {
        String hiddenName = "hidden_" + sheetHidden;
        Sheet sheet = workbook.getSheetAt(0);
        Sheet hidden = workbook.createSheet(hiddenName);
        Cell cell = null;
        for (int i = 0, length = dataArray.length; i < length; i++) {
            String name = dataArray[i];
            Row row = hidden.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(name);

        }
        Name namedCell = workbook.createName();
        namedCell.setNameName(hiddenName);
        namedCell.setRefersToFormula(hiddenName + "!$A$1:$A$" + dataArray.length);
        //加载数据,将名称为hidden的
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(hiddenName);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList addressList = new CellRangeAddressList(startRow, rows, col, col);
        HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
        HSSFWorkbook sheets = new HSSFWorkbook();
        // 将sheet设置为隐藏
        workbook.setSheetHidden(sheetHidden, true);
        sheet.addValidationData(validation);
    }

    private static Workbook getWorkbook(ExcelType type, int size) {
        if (ExcelType.HSSF.equals(type)) {
            return new HSSFWorkbook();
        } else if (size < USE_SXSSF_LIMIT) {
            return new XSSFWorkbook();
        } else {
            return new SXSSFWorkbook();
        }
    }

}
