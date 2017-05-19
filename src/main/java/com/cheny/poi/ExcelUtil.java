package com.cheny.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * TODO
 * <p>Filename: com.cheny.poi.ExcelUtil.java</p>
 * <p>Date: 2017-05-19 11:04.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class ExcelUtil {
    private  ExcelUtil(){}

    public static MergedCell get(Sheet sheet, int row, int column){
        int sheetMergeCount = sheet.getNumMergedRegions();
        MergedCell mergedCell = new MergedCell(row,column,row,column);
        mergedCell.setValue(getCellValue(sheet.getRow(row).getCell(column)));
        if(sheetMergeCount > 0){
            for(int i=0;i<sheetMergeCount;i++){
                CellRangeAddress range = sheet.getMergedRegion(i);
                int r0 = range.getFirstRow();
                int r1 = range.getLastRow();
                int c0 = range.getFirstColumn();
                int c1 = range.getLastColumn();
                if(row >= r0 && row <= r1 && column >= c0 && column <= c1){
                    mergedCell = new MergedCell(r0,c0,r1,c1);
                    mergedCell.setValue(getCellValue(sheet.getRow(r0).getCell(c0)));
                    break;
                }
            }
        }
        return mergedCell;
    }

    private  static String getCellValue(Cell cell){
        if(cell == null) return "";
        switch (cell.getCellTypeEnum()){
            case _NONE:
            case BLANK:
            case ERROR:
                return "";
            case FORMULA:
                return cell.getCellFormula();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }
}
