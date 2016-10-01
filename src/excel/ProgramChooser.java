package excel;

import black_carbon.BlackCarbonCreator;
import reader.DataFileReader;

import java.io.File;
import java.util.ArrayList;

public class ProgramChooser {

    public void createDataSamplePdf(){
//        File directory1 = new File("C:/Users/badea/Desktop/Saemi data/aerosol 2014/");
//        File directory2 = new File("C:/Users/badea/Desktop/Saemi data/aerosol 2015/");
        File directory = new File("C:/Users/badea/Desktop/Saemi data/BlackCarbon/");

        ArrayList<String> paths = new ArrayList<>();

        addPathsToList(directory, paths);
//        addPathsToList(directory2, paths);

        BlackCarbonCreator creator = new BlackCarbonCreator(paths);
    }

    public void createAvarageDataPdf(){

//        File timescalePdf = new File("C:/Users/badea/Desktop/Saemi data/AvarageValue/sam.xlsx");
//        File timescalePdf = new File("C:/Users/badea/Desktop/Saemi data/AvarageValue/ntnu timescale original.xlsx");
//        File ntnuNiloTogetherPdf = new File("C:/Users/badea/Desktop/Saemi data/AvarageValue/ntnu nilu together original.xlsx");

        File file = new File("C:/Users/badea/Desktop/Saemi data/AvarageValue/aerosol_complete.xlsx");

        DataFileReader toBeWritten = new DataFileReader(file);
        toBeWritten = toBeWritten.readContentForAerosol();

        DataFileReader toBeEdited = new DataFileReader(file);
//        toBeEdited = toBeEdited.readCorrectDates();

        Writer writer = new Writer(toBeWritten.days, toBeEdited.days);
    }

    private ArrayList<String> addPathsToList(File directory, ArrayList<String> paths){
        for (File file : directory.listFiles())
        {
            if (file.getAbsolutePath().toLowerCase().contains(".txt")){
                paths.add(file.getAbsolutePath());
            }
        }
        return paths;
    }

}
