package com.cheny.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * TODO
 * <p>Filename: com.cheny.poi.TestPoi.java</p>
 * <p>Date: 2017-05-19 10:33.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class TestPoi {

    public static void main(String[] args) throws Exception{
        FileInputStream inputStream = new FileInputStream(new File("/Users/chenyong/Downloads/models.xls"));

        Workbook wb = new HSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);

        int rownum = sheet.getPhysicalNumberOfRows();
        for(int i=0;i<rownum;i++){
            Row row = sheet.getRow(i);

            int columnum = row.getPhysicalNumberOfCells();

            for(int j=0;j<columnum;j++){
               MergedCell cell = ExcelUtil.get(sheet,i,j);

               System.out.println(cell.toString());


            }

        }
        if(null != wb){
            wb.close();
        }
        if(null != inputStream){
            inputStream.close();
        }
    }
}
