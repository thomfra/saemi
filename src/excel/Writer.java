package excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import reader.Day;
import reader.Hour;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Writer {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFCellStyle cellStyle;

    private ArrayList<ResultRow> resultRows;
    private ArrayList<Day> dataValues;
    private ArrayList<Day> controlDates;

    private File file;

    public Writer(ArrayList<Day> dataValues, ArrayList<Day> controlDates) {
        this.dataValues = dataValues;
        this.controlDates = controlDates;
        resultRows = new ArrayList<>();

        initWorkbook();
        initCellStyles();
        createResultForAerosol(dataValues);
//        createResultingValues();
//        write();
        writeForAerosol();
    }

    private void initWorkbook(){
//        file = new File("C:/Users/badea/Desktop/Saemi data/AvarageValue/average.xlsx");
        file = new File("C:/Users/badea/Desktop/Saemi data/AvarageValue/aerosol_average.xlsx");
        try {
            workbook  = new XSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = workbook.getSheetAt(0);
        sheet = workbook.getSheetAt(1);
    }

    private void initCellStyles(){
        cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
    }

    private void createResultForAerosol(ArrayList<Day> controlDates){
        ResultRow resultRow;
        for (int i = 0; i < controlDates.size(); i++) {
            Day day = controlDates.get(i);
            for (int j = 0; j < day.hours.size(); j++) {
                Hour hour = day.hours.get(j);
                Calendar cal = createDate(day, hour);

                resultRow = new ResultRow(cal, hour.dValue, hour.eValue, hour.fValue, hour.gValue, hour.hValue, hour.iValue, hour.jValue, hour.kValue, hour.lValue, hour.mValue, hour.nValue, hour.oValue, hour.pValue, hour.qValue, hour.rValue, hour.sValue, hour.tValue, hour.uValue, hour.vValue, hour.wValue, hour.xValue, hour.yValue, hour.zValue, hour.aaValue, hour.abValue);
                resultRows.add(resultRow);
            }
        }
        
    }

    private Calendar createDate(Day day, Hour hour){

        Calendar cal = Calendar.getInstance();
        cal.setTime(day.actualDate);
        cal.set(Calendar.HOUR_OF_DAY, hour.hourOfDay);
        return cal;
    }

    private void createResultingValues(){
        for (int i = 0; i < controlDates.size(); i++) {
            for (int j = 0; j < dataValues.size(); j++) {
                if(controlDates.get(i).actualDate.getTime() == dataValues.get(j).actualDate.getTime()) {
                    checkAndAddHourValue(controlDates.get(i), dataValues.get(j));
                }
            }
        }
    }

    private void  checkAndAddHourValue(Day controlDay, Day dataDay){
        for (int i = 0; i < controlDay.hours.size(); i++) {
            boolean found = false;
            Hour controlHour = controlDay.hours.get(i);

            for (int j = 0; j < dataDay.hours.size(); j++) {
                Hour dataHour = dataDay.hours.get(j);

                if (controlHour.hourOfDay == dataHour.hourOfDay) {
                    createResultRow(controlDay, controlHour, controlHour.niluAverage, dataHour.ntnuAverage, dataHour.phg, dataHour.rgm);
                    found = true;
                    break;
                }
            }
            if(!found){
                createResultRow(controlDay, controlHour, 0,0,0,0);
            }
        }
    }


    private void createResultRow(Day day, Hour hour, double niluAverage, double ntnuAverage, double phg, double rgm) {
        Calendar cal = createDate(day, hour);
        resultRows.add(new ResultRow(cal, niluAverage, ntnuAverage, rgm, phg));
    }


    private void writeForAerosol(){

        for (int i = 0; i < resultRows.size(); i++) {
            Row row = sheet.createRow(i+1);
            for (int j = 0; j < 25; j++) {
                Cell cell = row.createCell(j);

                if(j == 0){
                    cell.setCellValue(resultRows.get(i).getDate());
                    cell.setCellStyle(cellStyle);
                }

                if(j == 1){
                    if(resultRows.get(i).dValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).dValue);
                    }
                }

                if(j == 2){
                    if(resultRows.get(i).eValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).eValue);
                    }
                }

                if(j == 3){
                    if(resultRows.get(i).fValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).fValue);
                    }
                }

                if(j == 4){
                    if(resultRows.get(i).gValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).gValue);
                    }
                }

                if(j == 5){
                    if(resultRows.get(i).hValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).hValue);
                    }
                }

                if(j == 6){
                    if(resultRows.get(i).iValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).iValue);
                    }
                }
                if(j == 7){
                    if(resultRows.get(i).jValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).jValue);
                    }
                }
                if(j == 8){
                    if(resultRows.get(i).kValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).kValue);
                    }
                }
                if(j == 9){
                    if(resultRows.get(i).lValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).lValue);
                    }
                }
                if(j == 10){
                    if(resultRows.get(i).mValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).mValue);
                    }
                }
                if(j == 11){
                    if(resultRows.get(i).nValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).nValue);
                    }
                }
                if(j == 12){
                    if(resultRows.get(i).oValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).oValue);
                    }
                }
                if(j == 13){
                    if(resultRows.get(i).pValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).pValue);
                    }
                }
                if(j == 14){
                    if(resultRows.get(i).qValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).qValue);
                    }
                }
                if(j == 15){
                    if(resultRows.get(i).rValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).rValue);
                    }
                }
                if(j == 16){
                    if(resultRows.get(i).sValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).sValue);
                    }
                }
                if(j == 17){
                    if(resultRows.get(i).tValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).tValue);
                    }
                }

                if(j == 18){
                    if(resultRows.get(i).uValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).uValue);
                    }
                }
                if(j == 19){
                    if(resultRows.get(i).vValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).vValue);
                    }
                }

                if(j == 20){
                    if(resultRows.get(i).wValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).wValue);
                    }
                }

                if(j == 21){
                    if(resultRows.get(i).xValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).xValue);
                    }
                }

                if(j == 22){
                    if(resultRows.get(i).yValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).yValue);
                    }
                }

                if(j == 23){
                    if(resultRows.get(i).zValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).zValue);
                    }
                }

                if(j == 24){
                    if(resultRows.get(i).aaValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).aaValue);
                    }
                }

                if(j == 25){
                    if(resultRows.get(i).abValue == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).abValue);
                    }
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
    }


    private void write(){

        for (int i = 0; i < resultRows.size(); i++) {
            Row row = sheet.createRow(i+1);
            for (int j = 0; j < 5; j++) {
                Cell cell = row.createCell(j);

                if(j == 0){
                    cell.setCellValue(resultRows.get(i).getDate());
                    cell.setCellStyle(cellStyle);
                }

                if(j == 1){
                    if(resultRows.get(i).niluAverage == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).niluAverage);
                    }
                }

                if(j == 2){
                    if(resultRows.get(i).ntnuAverage == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).ntnuAverage);
                    }
                }

                if(j == 3){
                    if(resultRows.get(i).phg == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).phg);
                    }
                }

                if(j == 4){
                    if(resultRows.get(i).rgm == 0){
                        cell = row.createCell(j);
                    } else {
                        cell.setCellValue(resultRows.get(i).rgm);
                    }
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
    }

}

