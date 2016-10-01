package excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ExcelCreator {

    private HSSFWorkbook workbook = new HSSFWorkbook();
    private HSSFCellStyle cellStyle;
    private HSSFCellStyle corruptCellStyle;

    private DataFileHolder sheetOneDataFiles;
    private DataFileHolder sheetTwoDataFiles;
    private String day;

    private String path;
    private ArrayList<String> paths;
    private boolean corruptRow;

    public ExcelCreator(ArrayList<String> paths) {
        this.paths = paths;
        initDataFileHolders();
        createDateCellStyles();
        createFilesFromPaths();
        writeToExcelDocument(sheetOneDataFiles);
        writeToExcelDocument(sheetTwoDataFiles);
    }

    private void createDateCellStyles() {
        cellStyle = workbook.createCellStyle();

        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("hh:mm:ss"));

        corruptCellStyle = workbook.createCellStyle();
        corruptCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        corruptCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
    }

    private void createCorruptCellStyle(Cell row) {
        row.setCellStyle((CellStyle) corruptCellStyle);
    }

    private void initDataFileHolders() {
        sheetOneDataFiles = new DataFileHolder(workbook, "Day 1");
        sheetTwoDataFiles = new DataFileHolder(workbook, "Day 2");
    }

    private void createFilesFromPaths() {
        for (int i = 0; i < paths.size(); i++) {
            DataFile dataFile = new DataFile(new File(paths.get(i)));
            if(dataFile.path.contains("_d1_")){
                dataFile.setSheetNumber(1);
                sheetOneDataFiles.addDataFile(dataFile);
            } else {
                dataFile.setSheetNumber(2);
                sheetTwoDataFiles.addDataFile(dataFile);
            }
        }

    }

    private void writeToExcelDocument(DataFileHolder holder) {
        ArrayList<ArrayList<String>> lines = holder.concatAllDataFiles();

        for (int i = 0; i < lines.size(); i++) {

            ArrayList<String> l = lines.get(i);
            Row row = holder.getSheet().createRow(i);
            int year = Integer.parseInt(l.get(0).substring(0,4));
            int month = Integer.parseInt(l.get(0).substring(5,7));
            int day = Integer.parseInt(l.get(0).substring(8,10));
            Calendar cal = Calendar.getInstance();
            cal.set(year, month-1, day, 0, 0,0);

            for (int j = 0; j < l.size(); j++) {
                Cell cell = row.createCell(j);
                if(isCorruptRow(i, holder)){
                    createCorruptCellStyle(cell);
                }
                if (j == 1 && i == 1) {
                    createDateHeader(cell);
                }

                if(j == 0 && i > 0){
                    cell.setCellValue(new Date());
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(cal.getTime());
                }

                if (j == 1) {
                    double d = Double.parseDouble(l.get(j));

                    Date date = DateUtil.getJavaDate(d);
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.set(Calendar.YEAR, year);
                    c.add(Calendar.DAY_OF_MONTH, 1);

                    if (i != 0) {

                        cell.setCellValue(new Date());
                        cell.setCellStyle(cellStyle);
                        cell.setCellValue(c.getTime());

                    }

                } else {
                    if (j > 0) {
                        float f = new Float(Float.parseFloat(l.get(j)));
                        cell.setCellValue(f);
                    }
                }
            }

        }

        try {
            FileOutputStream outputStream = new FileOutputStream(new File("C:\\Users\\badea\\Desktop\\lol.xls"));
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
    }

    private void createDateHeader(Cell cell){
        cell.setCellValue("Date");
    }

    public boolean isCorruptRow(int index, DataFileHolder holder) {
        ArrayList<Integer> corruptIndexes = holder.getCorruptedRows();

        for (int i = 0; i < corruptIndexes.size(); i++) {
            if(index == corruptIndexes.get(i)){
                return true;
            }
        }

        return false;
    }
}
