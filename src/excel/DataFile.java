package excel;

import java.io.*;
import java.util.ArrayList;

public class DataFile {

    private File file;
    public ArrayList<ArrayList<String>> lines;
    public String path;
    public String day;
    public String firstLine;
    public int lengthOfFirstLine = 0;
    public int sheetNumber;

    public DataFile(File file) {
        this.file = file;
        initLocalMember();
        readLinesOfFile();
        createFirstLine();
    }

    private void initLocalMember() {
        path = file.getPath();
//        day = path.substring(path.indexOf("-")+1, path.indexOf("_"));
        day = path.substring(path.indexOf("-")+1, path.indexOf("."));
    }

    private void readLinesOfFile() {
        lines = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line = null;
            while ((line = br.readLine()) != null) {
                ArrayList<String> list = new ArrayList<>();
                if(line.contains("000000")){
                    addStringArrayToList(list, line.split(" "));
                    lines.add(list);
                }

            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void addStringArrayToList(ArrayList<String> list, String[] stringA){

        for (int i = 0; i < stringA.length; i++) {
            if(!stringA[i].equals(""))
                list.add(stringA[i]);
        }
    }

    private void createFirstLine(){
        String res = "";
        for (int i = 1; i < lines.get(0).size(); i++) {
            lengthOfFirstLine += 1;
            res+= lines.get(0).get(i);
        }
        firstLine = res;
    }

    public void setSheetNumber(int number){
        sheetNumber = number;
    }
}
