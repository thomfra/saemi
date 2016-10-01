package black_carbon;

import excel.DataFile;
import excel.DataFileHolder;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BlackCarbonCreator {

    private ArrayList<BlackCarbonEntry> blackCarbonEntries = new ArrayList<>();
    private ArrayList<BCDay> days = new ArrayList<>();

    private ArrayList<BlackCarbonRow> resultRows = new ArrayList<>();

    private HSSFWorkbook workbook = new HSSFWorkbook();
    private HSSFCellStyle cellStyle;
    private HSSFCellStyle corruptCellStyle;

    private DataFileHolder sheetOneDataFiles;
    private String day;

    private String path;
    private ArrayList<String> paths;
    private boolean corruptRow;

    public BlackCarbonCreator(ArrayList<String> paths) {
        this.paths = paths;
        initDataFileHolders();
        createDateCellStyles();
        createFilesFromPaths();
        prepateDataForWriting(sheetOneDataFiles);
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
    }

    private void createFilesFromPaths() {
        for (int i = 0; i < paths.size(); i++) {
            DataFile dataFile = new DataFile(new File(paths.get(i)));
            dataFile.setSheetNumber(1);
            sheetOneDataFiles.addDataFile(dataFile);
        }

    }

    private void prepateDataForWriting(DataFileHolder holder) {
        ArrayList<ArrayList<String>> lines = holder.concatAllDataFiles();

        for (int i = 0; i < lines.size(); i++) {

            ArrayList<String> l = lines.get(i);
            int year = Integer.parseInt(l.get(0).substring(0,4));
            int month = Integer.parseInt(l.get(0).substring(5,7));
            int day = Integer.parseInt(l.get(0).substring(8,10));
            int hour = Integer.parseInt(l.get(1).substring(0,2));
            int minute = Integer.parseInt(l.get(1).substring(3,5));
            int second = Integer.parseInt(l.get(1).substring(6,8));
            Calendar cal = Calendar.getInstance();
            cal.set(year, month-1, day, hour, minute,second);

            double val = Double.parseDouble(l.get(5));

            blackCarbonEntries.add(new BlackCarbonEntry(cal, val));

        }

        sortEntriesOnDay();
        createResultRows();
        writeToExcel();

    }

    private void sortEntriesOnDay() {

        BCDay day = new BCDay(blackCarbonEntries.get(0).date.getTime());
        BCHour hour = new BCHour(blackCarbonEntries.get(0).date.getTime(), day);
        int currentDay = blackCarbonEntries.get(0).date.get(Calendar.DAY_OF_MONTH);
        int currentHour = blackCarbonEntries.get(0).date.get(Calendar.HOUR_OF_DAY);
        int currentMonth = blackCarbonEntries.get(0).date.get(Calendar.MONTH)-1;
        int currentYear = blackCarbonEntries.get(0).date.get(Calendar.YEAR);
        days.add(day);
        day.addHour(hour);
        for (int i = 0; i < blackCarbonEntries.size(); i++) {
            BlackCarbonEntry entry = blackCarbonEntries.get(i);

            if(entry.date.get(Calendar.YEAR) > currentYear) {
                day = new BCDay(entry.date.getTime());
                hour = new BCHour(entry.date.getTime(), day);
                currentDay = entry.date.get(Calendar.DAY_OF_MONTH);
                currentHour = entry.date.get(Calendar.HOUR_OF_DAY);
                currentMonth = entry.date.get(Calendar.MONTH);
                currentYear = entry.date.get(Calendar.YEAR);
                days.add(day);
                day.addHour(hour);
                hour.addtoList(entry.value);
            }

            else if(entry.date.get(Calendar.MONTH) > currentMonth) {
                day = new BCDay(entry.date.getTime());
                hour = new BCHour(entry.date.getTime(), day);
                currentDay = entry.date.get(Calendar.DAY_OF_MONTH);
                currentHour = entry.date.get(Calendar.HOUR_OF_DAY);
                currentMonth = entry.date.get(Calendar.MONTH);
                currentYear = entry.date.get(Calendar.YEAR);
                days.add(day);
                day.addHour(hour);
                hour.addtoList(entry.value);
            }

            else if (entry.date.get(Calendar.DAY_OF_MONTH) > currentDay) {
                day = new BCDay(entry.date.getTime());
                hour = new BCHour(entry.date.getTime(), day);
                currentDay = entry.date.get(Calendar.DAY_OF_MONTH);
                currentHour = entry.date.get(Calendar.HOUR_OF_DAY);
                currentMonth = entry.date.get(Calendar.MONTH);
                currentYear = entry.date.get(Calendar.YEAR);
                days.add(day);
                day.addHour(hour);
                hour.addtoList(entry.value);
            }

            else if(entry.date.get(Calendar.HOUR_OF_DAY) > currentHour) {
                hour = new BCHour(entry.date.getTime(), day);
                currentDay = entry.date.get(Calendar.DAY_OF_MONTH);
                currentHour = entry.date.get(Calendar.HOUR_OF_DAY);
                currentMonth = entry.date.get(Calendar.MONTH);
                currentYear = entry.date.get(Calendar.YEAR);
                day.addHour(hour);
                hour.addtoList(entry.value);
            }

            else {
                hour.addtoList(entry.value);
            }
        }
    }

    private void createResultRows() {

        for (int i = 0; i < days.size(); i++) {
            for (int j = 0; j <days.get(i).hours.size(); j++) {
                BCHour hour = days.get(i).hours.get(j);
                hour.calculateAvarage();
                hour.calculateMedian();
                double averageOfHour = hour.average;
                double median = hour.median;

                resultRows.add(new BlackCarbonRow(hour.actualHour, averageOfHour, median));
            }
        }

    }

    private void writeToExcel() {

        HSSFSheet sheet = workbook.createSheet("Sheet 1");

        for (int i = 0; i < resultRows.size(); i++) {
            Row row = sheet.createRow(i);


            for (int j = 0; j < 3; j++) {

                Cell cell = row.createCell(j);

                if (j == 0) {
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(resultRows.get(i).date.getTime());

                }


                if(j == 1) {
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(resultRows.get(i).value);
                }

                if(j == 2) {
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(resultRows.get(i).median);
                }


            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(new File("C:\\Users\\badea\\Desktop\\lol2.xls"));
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
