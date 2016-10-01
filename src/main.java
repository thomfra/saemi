import excel.ExcelCreator;
import excel.ProgramChooser;

import java.io.File;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) {


        ProgramChooser chooser = new ProgramChooser();

//        chooser.createAvarageDataPdf();
        chooser.createDataSamplePdf();
    }
}
