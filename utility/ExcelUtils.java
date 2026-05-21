package utilities;

import java.io.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class ExcelUtils {
    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;

    public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data) throws IOException {
        File file = new File(xlfile);
        
        // Handle empty or non-existent file
        if (file.exists() && file.length() > 0) {
            fi = new FileInputStream(xlfile);
            wb = new XSSFWorkbook(fi);
            fi.close();
        } else {
            wb = new XSSFWorkbook();
        }

        ws = wb.getSheet(xlsheet);
        if (ws == null) ws = wb.createSheet(xlsheet);

        row = ws.getRow(rownum);
        if (row == null) row = ws.createRow(rownum);

        cell = row.createCell(colnum);
        cell.setCellValue(data);

        fo = new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fo.close();
    }
}