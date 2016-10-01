package reader;

import excel.DateFormater;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.xmlbeans.soap.SOAPArrayType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DataFileReader {

    private static final int ROWS = 106315;

    private static final int AERO_ROWS = 25297;
    private static final int AERO_NUMBER_OF_COLUMNS_SHEET1 = 16;
    private static final int AERO_NUMBER_OF_COLUMNS_SHEET2 = 25;

    private File inputFile;
    public ArrayList<Date> resultDate;
    private ArrayList<Double> resultValue;
    public ArrayList<Day> days;
    private FileInputStream fileInputStream;
    private Day currentDay;
    private Hour currentHour;

    public DataFileReader(File inputFile) {
        this.inputFile = inputFile;
        this.days = new ArrayList<>();
    }



    public DataFileReader readContent(){

        try {
            fileInputStream = new FileInputStream(inputFile);

            Workbook workbook = WorkbookFactory.create(fileInputStream);

            Sheet sheet = workbook.getSheetAt(0);
            FormulaEvaluator formulaEval = workbook.getCreationHelper().createFormulaEvaluator();

            for (int i = 1; i <= AERO_ROWS; i++) {

                Row row = sheet.getRow(i);
                if(row == null){
                    row = sheet.createRow(i);
                }
                System.out.printf(i+"\t\t");
                for (int j = 0; j < 7; j++) {
                    Cell cell = row.getCell(j);
                    if(cell != null){

                    switch(cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                                if (j == 0) {
                                    if (currentDay == null) {
                                        currentDay = new Day(date);
                                        days.add(currentDay);
                                    }
                                    checkDay(date);
                                }
                                if (j == 1) {
                                    convertAndAddHour(date);
                                }

                            }

                            if(j == 6) {
                                currentHour.addToNtnuRemList(cell.getNumericCellValue());
                                System.out.print(cell.getNumericCellValue() + "\t\t");
                            }
                            break;

                        case Cell.CELL_TYPE_FORMULA:
                            String value=formulaEval.evaluate(cell).formatAsString();
                            if(j == 4) {
                                currentHour.addToPhgList(Double.parseDouble(value));
                            }if(j == 5) {
                                currentHour.addToRgmList(Double.parseDouble(value));
                            }
                            break;

                    }

                    }
                }
                System.out.println("");
            }
            currentHour.calculateAvarage();
            currentDay.addHour(currentHour);
            fileInputStream.close();
            concatAllAvarageValues();
        } catch (IOException |InvalidFormatException e) {
            e.printStackTrace();
        }

        return this;
    }

    public DataFileReader readContentForAerosol(){
        try {
            fileInputStream = new FileInputStream(inputFile);

            Workbook workbook = WorkbookFactory.create(fileInputStream);

//            Sheet sheet = workbook.getSheetAt(0);
            Sheet sheet = workbook.getSheetAt(1);
            FormulaEvaluator formulaEval = workbook.getCreationHelper().createFormulaEvaluator();

            for (int i = 1; i <= AERO_ROWS; i++) {

                Row row = sheet.getRow(i);
                if(row == null){
                    row = sheet.createRow(i);
                }
                System.out.printf(i+"\t\t");
                for (int j = 1; j < 28; j++) {
                    Cell cell = row.getCell(j);
                    if(cell != null){

                        switch(cell.getCellType()) {
                            case Cell.CELL_TYPE_BOOLEAN:
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                                    if (j == 1) {
                                        if (currentDay == null) {
                                            currentDay = new Day(date);
                                            days.add(currentDay);
                                        }
                                        checkDay(date);
                                        convertAndAddHour(date);
                                    }
                                }

                                if(j == 4){
                                    currentHour.addtoList(currentHour.dList, cell.getNumericCellValue());
                                }

                                if(j == 5) {
                                    currentHour.addtoList(currentHour.eList, cell.getNumericCellValue());
                                }

                                if(j == 6) {
                                    currentHour.addtoList(currentHour.fList, cell.getNumericCellValue());
                                }

                                if(j == 7) {
                                    currentHour.addtoList(currentHour.gList, cell.getNumericCellValue());
                                }

                                if(j == 8) {
                                    currentHour.addtoList(currentHour.hList, cell.getNumericCellValue());
                                }

                                if(j == 9) {
                                    currentHour.addtoList(currentHour.iList, cell.getNumericCellValue());
                                }
                                if(j == 10) {
                                    currentHour.addtoList(currentHour.jList, cell.getNumericCellValue());
                                }
                                if(j == 11) {
                                    currentHour.addtoList(currentHour.kList, cell.getNumericCellValue());
                                }
                                if(j == 12) {
                                    currentHour.addtoList(currentHour.lList, cell.getNumericCellValue());
                                }
                                if(j == 13) {
                                    currentHour.addtoList(currentHour.mList, cell.getNumericCellValue());
                                }
                                if(j == 14) {
                                    currentHour.addtoList(currentHour.nList, cell.getNumericCellValue());
                                }
                                if(j == 15) {
                                    currentHour.addtoList(currentHour.oList, cell.getNumericCellValue());
                                }

                                if(j == 16) {
                                    currentHour.addtoList(currentHour.pList, cell.getNumericCellValue());
                                }

                                if(j == 17) {
                                    currentHour.addtoList(currentHour.qList, cell.getNumericCellValue());
                                }

                                if(j == 18) {
                                    currentHour.addtoList(currentHour.rList, cell.getNumericCellValue());
                                }

                                if(j == 19) {
                                    currentHour.addtoList(currentHour.sList, cell.getNumericCellValue());
                                }

                                if(j == 20) {
                                    currentHour.addtoList(currentHour.tList, cell.getNumericCellValue());
                                }

                                if(j == 21) {
                                    currentHour.addtoList(currentHour.uList, cell.getNumericCellValue());
                                }

                                if(j == 22) {
                                    currentHour.addtoList(currentHour.vList, cell.getNumericCellValue());
                                }

                                if(j == 23) {
                                    currentHour.addtoList(currentHour.wList, cell.getNumericCellValue());
                                }

                                if(j == 24) {
                                    currentHour.addtoList(currentHour.xList, cell.getNumericCellValue());
                                }
                                if(j == 25) {
                                    currentHour.addtoList(currentHour.yList, cell.getNumericCellValue());
                                }
                                if(j == 26) {
                                    currentHour.addtoList(currentHour.zList, cell.getNumericCellValue());
                                }
                                if(j == 27) {
                                    currentHour.addtoList(currentHour.aaList, cell.getNumericCellValue());
                                }
                                if(j == 28) {
                                    currentHour.addtoList(currentHour.abList, cell.getNumericCellValue());
                                }
                                break;
                        }
                    }
                }
                System.out.println("");
            }
            currentHour.calculateAvarage();
            currentDay.addHour(currentHour);
            fileInputStream.close();
            concatAllAvarageValues();
        } catch (IOException |InvalidFormatException e) {
            e.printStackTrace();
        }

        return this;
    }


    public DataFileReader readCorrectDates(){

        try {
            File file = new File("C:/Users/badea/Desktop/Saemi data/AvarageValue/ntnu timescale original.xlsx");
            fileInputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fileInputStream);

            Sheet sheet = workbook.getSheetAt(1);

            for (int i = 0; i <= 10151; i++) {
                Row row = sheet.getRow(i);
                System.out.printf(i+"\t\t");

                for (int j = 0; j < 2; j++) {
                    Cell cell = row.getCell(j);


                    if(cell != null){

                        switch(cell.getCellType()) {
                            case Cell.CELL_TYPE_BLANK:
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                break;
                            case Cell.CELL_TYPE_NUMERIC:

                                if (DateUtil.isCellDateFormatted(cell)) {
                                    Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                                    if (j == 0) {
                                        if (currentDay == null) {
                                            currentDay = new Day(date);
                                            currentHour = new Hour(date, currentDay);
                                            days.add(currentDay);
                                        }

                                        checkDayAndHour(date, i);
                                    }
                                }

                                if(j == 1) {
                                    currentHour.addToNiluRemList(cell.getNumericCellValue());
                                }
                                break;
                        }
                    }
                }
                System.out.println("");
            }
            currentHour.calculateAvarage();
            currentDay.addHour(currentHour);
            fileInputStream.close();
            makeControlResultDate();
        } catch (IOException |InvalidFormatException e) {
            e.printStackTrace();
        }

        return this;
    }

    private void checkDayAndHour(Date date, int i){

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int currentHour = this.currentHour.actualHour.get(Calendar.HOUR_OF_DAY);
        int readHour = calendar.get(Calendar.HOUR_OF_DAY);

        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(DateFormater.removeHoursAndMinute(date));

        if(dateCal.getTimeInMillis() > currentDay.actualDate.getTime()){
            this.currentHour.calculateAvarage();
            currentDay.addHour(this.currentHour);
            System.out.print("Added new hour: "+format.format(date) + "\t\t");
            currentDay = new Day(date);
            this.currentHour = new Hour(date, currentDay);
            days.add(currentDay);
        }

        if(currentHour < readHour){
            this.currentHour.calculateAvarage();
            currentDay.addHour(this.currentHour);
            this.currentHour = new Hour(date, currentDay);
            System.out.print("Added new hour: "+format.format(date) + "\t\t");
        }
    }

    private void checkDay(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print(format.format(date) + "\t\t");

        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(DateFormater.removeHoursAndMinute(date));
        Date d = dateCal.getTime();
        if(dateCal.getTimeInMillis() > currentDay.actualDate.getTime()){
            currentHour.calculateAvarage();
            currentDay.addHour(this.currentHour);
            currentDay = new Day(date);
            currentHour = new Hour(date, currentDay);
            days.add(currentDay);
        }
    }

    private void convertAndAddHour(Date date){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        System.out.print(format.format(date) + "\t\t");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (this.currentHour == null) {
            this.currentHour = new Hour(date, currentDay);
        }

        int currentHour = this.currentHour.actualHour.get(Calendar.HOUR_OF_DAY);
        int readHour = calendar.get(Calendar.HOUR_OF_DAY);

        if(currentHour < readHour){
            this.currentHour.calculateAvarage();
            currentDay.addHour(this.currentHour);
            this.currentHour = new Hour(date, currentDay);
        }
    }

    private void concatAllAvarageValues(){
        resultDate = new ArrayList<>();
        resultValue = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < days.size(); i++) {
            c.setTime(days.get(i).actualDate);
            resultDate.add(c.getTime());
            for (int j = 0; j < days.get(i).hours.size(); j++) {
                c.set(Calendar.HOUR_OF_DAY, days.get(i).hours.get(j).hourOfDay);
                resultDate.add(c.getTime());
                resultValue.add(days.get(i).hours.get(j).ntnuAverage);
            }
        }
    }

    private void makeControlResultDate(){
        resultDate = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < days.size(); i++) {
            c.setTime(days.get(i).actualDate);
            resultDate.add(c.getTime());
        }
    }
}
