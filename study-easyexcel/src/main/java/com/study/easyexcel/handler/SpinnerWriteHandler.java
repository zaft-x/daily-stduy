package com.study.easyexcel.handler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.study.easyexcel.annotation.ExcelAnalyze;

/**
 * @Author x.zaft
 * @Date 2020/12/14
 * @Version
 **/
public class SpinnerWriteHandler implements SheetWriteHandler, CellWriteHandler {

    private Class<?> t;

    /** perset row number */
    private int rows;

    /** start row*/
    private int startRow;

    public SpinnerWriteHandler(Class<?> t, int rows) {
        this.t = t;
        this.rows = rows;
    }

    public SpinnerWriteHandler(Class<?> t, int startRow, int rows) {
        this.t = t;
        this.startRow = startRow;
        this.rows = rows;
    }

    public SpinnerWriteHandler() {
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Sheet sheet = writeSheetHolder.getSheet();
        ///开始设置下拉框
        DataValidationHelper helper = sheet.getDataValidationHelper();
        this.setPerset(sheet, helper);
        //下面定时样式的
        /**样式会有问题   sheet.getRow(0)  cell 空*/
    }

    private void setPerset(Sheet sheet, DataValidationHelper helper) {
        Field[] declaredFields = t.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            ExcelAnalyze excelAnalyze = declaredField.getDeclaredAnnotation(ExcelAnalyze.class);
            if (null == excelAnalyze || null == excelAnalyze.perset() || excelAnalyze.perset().length == 0) {
                continue;
            }
            /***起始行、终止行、起始列、终止列**/
            CellRangeAddressList addressList = new CellRangeAddressList(1, rows, excelAnalyze.index(),
                    excelAnalyze.index());
            /***设置下拉框数据**/
            DataValidationConstraint constraint = helper.createExplicitListConstraint(excelAnalyze.perset());
            DataValidation dataValidation = helper.createValidation(constraint, addressList);
            /***处理Excel兼容性问题**/
            if (dataValidation instanceof XSSFDataValidation) {
                dataValidation.setSuppressDropDownArrow(true);
                dataValidation.setShowErrorBox(true);
            } else {
                dataValidation.setSuppressDropDownArrow(false);
            }
            sheet.addValidationData(dataValidation);
        }
    }

    private List<Integer> getFormulas() {
        List<Integer> result = new ArrayList<>();
        Field[] declaredFields = t.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            ExcelAnalyze excelAnalyze = declaredField.getDeclaredAnnotation(ExcelAnalyze.class);
            if (null == excelAnalyze || ObjectUtils.isEmpty(excelAnalyze.formula())) {
                continue;
            }
            result.add(excelAnalyze.index());
        }
        return result;
    }

    private void formula(Cell cell) {
        Field[] declaredFields = t.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            ExcelAnalyze excelAnalyze = declaredField.getDeclaredAnnotation(ExcelAnalyze.class);

            /**
             * : 和  -差  *积  /除
             * */
            String collect = Arrays.stream(excelAnalyze.formula()).boxed()
                    .map(i -> CellReference.convertNumToColString(i) + (cell.getRowIndex() + 1))
                    .collect(Collectors.joining(":"));
            cell.setCellFormula("SUM(" + collect + ")");
        }
    }

    private CellStyle setStyle(Workbook wb) {
        Font font = wb.createFont();
        font.setColor(IndexedColors.RED.index);
        font.setFontName("宋体");
        font.setFontHeight((short) 240);
        font.setBold(true);
        font.setFontHeightInPoints((short) 10);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        /**设置单元格属性（例：文本）*/
//        DataFormat dataFormat = wb.createDataFormat();
//        cellStyle.setDataFormat(dataFormat.getFormat("@"));
        return cellStyle;
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
            Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell,
            Head head, Integer relativeRowIndex, Boolean isHead) {
        /**设置单元格格式为文本*/
        Workbook workbook = writeSheetHolder.getSheet().getWorkbook();

        if(cell.getRowIndex() >= this.startRow && this.getFormulas().contains(cell.getColumnIndex())){
            this.formula(cell);
        }
        cell.setCellStyle(this.setStyle(workbook));

    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
            CellData cellData, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
            List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
//        System.out.println("进入第" +  cell.getRowIndex() + "行,第" + cell.getColumnIndex() + "列数据...");


//        int actualCellRowNum = cell.getRowIndex() + 1;
//        this.formula(cell);
    }

}
