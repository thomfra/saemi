package excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.ArrayList;

public class DataFileHolder {

    private ArrayList<DataFile> dataFiles;
    private ArrayList<Integer> corruptedRows;
    private HSSFSheet sheet;
    private String firstLine = null;
    private int lengtOfFirstLine = 0;

    public DataFileHolder(HSSFWorkbook workbook, String sheetTitle) {

        dataFiles = new ArrayList<>();
        corruptedRows = new ArrayList<>();
        initWorkSheet(workbook, sheetTitle);
    }

    public void addDataFile(DataFile dataFile){
        dataFiles.add(dataFile);
    }

    private void initWorkSheet(HSSFWorkbook workbook, String sheetTitle){
        sheet = workbook.createSheet(sheetTitle);
    }

    public ArrayList<ArrayList<String>> concatAllDataFiles(){

        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (int i = 0; i < dataFiles.size(); i++) {
            DataFile dataFile = dataFiles.get(i);
            chechIfDataFileCorrupt(dataFile, i, result);
            for (int j = 0; j < dataFiles.get(i).lines.size(); j++) {
                if(i == 0 && j == 0) {
                    result.add(dataFile.lines.get(j));
                }
                if(j > 0) {
                    result.add(dataFile.lines.get(j));
                }
            }
        }
        return result;
    }

    private void chechIfDataFileCorrupt(DataFile dataFile, int i, ArrayList<ArrayList<String>> result) {
        if(i == 0){
            lengtOfFirstLine = dataFiles.get(i).lengthOfFirstLine;
            firstLine = dataFiles.get(i).firstLine;
        }
        if(!dataFile.firstLine.equals(firstLine)){
            if(dataFile.lengthOfFirstLine == lengtOfFirstLine+1){
                firstLine = dataFile.firstLine;
                lengtOfFirstLine = dataFile.lengthOfFirstLine;
            }
            for (int j = 0; j < dataFile.lines.size(); j++) {
                corruptedRows.add(result.size()+j);
            }
        }
    }

    public HSSFSheet getSheet() {
        return sheet;
    }

    public ArrayList<Integer> getCorruptedRows() {
        return corruptedRows;
    }
}
