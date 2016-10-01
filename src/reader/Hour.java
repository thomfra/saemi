package reader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Hour {

    public ArrayList<Double> niluRemList;
    public ArrayList<Double> ntnuRemList;
    public ArrayList<Double> rgmList;
    public ArrayList<Double> phgList;
    public ArrayList<Double> dList, eList, fList, gList, hList, iList, jList, kList, lList, mList, nList, oList, pList, qList, rList, sList, tList, uList, vList, wList, xList, yList, zList, aaList, abList;


    public Calendar actualHour;
    public int hourOfDay;
    public double niluAverage;
    public double ntnuAverage;
    public double rgm;
    public double phg;

    public double dValue, eValue, fValue, gValue, hValue, iValue, jValue, kValue, lValue, mValue, nValue, oValue, pValue, qValue, rValue, sValue, tValue, uValue, vValue, wValue, xValue, yValue, zValue, aaValue, abValue;
    public double[] listOfAllValues;
    private ArrayList<ArrayList<Double>> listOfAllLists;


    public Hour(Date date, Day currentDay) {
        initAllLists();

        actualHour = Calendar.getInstance();
        actualHour.setTime(date);
        hourOfDay = actualHour.get(Calendar.HOUR_OF_DAY);
    }

    public void addToNiluRemList(double d){
        niluRemList.add(d);
    }
    public void addToNtnuRemList(double d){
        ntnuRemList.add(d);
    }

    public void addToRgmList(double rgm){
        rgmList.add(rgm);
    }

    public void addToPhgList(double phg) {
        phgList.add(phg);
    }

    public void addtoList(ArrayList<Double> list, double d){
        list.add(d);
    }

    public void calculateAvarage(){

        addAllListsToList();

        calculateA(niluRemList);
        calculateA(ntnuRemList);
        calculateA(rgmList);
        calculateA(phgList);
        calcDValue();

        for (int i = 0; i < listOfAllValues.length; i++) {
            switch (i){
                case 0:
                    dValue =calculateA(listOfAllLists.get(i));
                    break;
                case 1:
                    eValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 2:
                    fValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 3:
                    gValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 4:
                    hValue=  calculateA(listOfAllLists.get(i));
                    break;
                case 5:
                    iValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 6:
                    jValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 7:
                    kValue=  calculateA(listOfAllLists.get(i));
                    break;
                case 8:
                    lValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 9:
                    mValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 10:
                    nValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 11:
                    oValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 12:
                    pValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 13:
                    qValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 14:
                    rValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 15:
                    sValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 16:
                    tValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 17:
                    uValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 18:
                    vValue=  calculateA(listOfAllLists.get(i));
                    break;
                case 19:
                    wValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 20:
                    xValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 21:
                    yValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 22:
                    zValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 23:
                    aaValue =  calculateA(listOfAllLists.get(i));
                    break;
                case 24:
                    abValue =  calculateA(listOfAllLists.get(i));
                    break;
            }
        }
    }

    private void addAllListsToList() {
        listOfAllLists = new ArrayList<>();
        listOfAllLists.add(dList);
        listOfAllLists.add(eList);
        listOfAllLists.add(fList);
        listOfAllLists.add(gList);
        listOfAllLists.add(hList);
        listOfAllLists.add(iList);
        listOfAllLists.add(jList);
        listOfAllLists.add(kList);
        listOfAllLists.add(lList);
        listOfAllLists.add(mList);
        listOfAllLists.add(nList);
        listOfAllLists.add(oList);
        listOfAllLists.add(pList);
        listOfAllLists.add(qList);
        listOfAllLists.add(rList);
        listOfAllLists.add(sList);
        listOfAllLists.add(tList);
        listOfAllLists.add(uList);
        listOfAllLists.add(vList);
        listOfAllLists.add(wList);
        listOfAllLists.add(xList);
        listOfAllLists.add(yList);
        listOfAllLists.add(zList);
        listOfAllLists.add(aaList);
        listOfAllLists.add(abList);
    }

    private void calcDValue(){
        double sum = 0;
        for (int i = 0; i < dList.size(); i++) {
            BigDecimal bd = new BigDecimal(dList.get(i));
            sum += bd.doubleValue();
        }
        if(dList.size() == 0){
            dValue = 0;
        } else {
            dValue = sum/ dList.size();
        }
    }

    private double calculateA(ArrayList<Double> list){
        double sum = 0;
        double value = 0;
        if(list != null) {
            for (int i = 0; i < list.size(); i++) {
                BigDecimal bd = new BigDecimal(list.get(i));
                sum += bd.doubleValue();
            }

            if(list.size() == 0){
                value = 0;
            } else {
                value = sum/ list.size();
            }
        }

        return value;
    }

    private void initAllLists(){
        listOfAllValues = new double[] {dValue, eValue, fValue, gValue, hValue, iValue, jValue, kValue, lValue, mValue, nValue, oValue, pValue, qValue, rValue, sValue, tValue, uValue, vValue, wValue, xValue, yValue, zValue, aaValue, abValue};
        ntnuRemList = new ArrayList<>();
        niluRemList = new ArrayList<>();
        rgmList = new ArrayList<>();
        phgList = new ArrayList<>();
        dList = new ArrayList<>();
        eList = new ArrayList<>();
        fList = new ArrayList<>();
        gList = new ArrayList<>();
        hList = new ArrayList<>();
        iList = new ArrayList<>();
        jList = new ArrayList<>();
        kList = new ArrayList<>();
        lList = new ArrayList<>();
        mList = new ArrayList<>();
        nList = new ArrayList<>();
        oList = new ArrayList<>();
        pList = new ArrayList<>();
        qList = new ArrayList<>();
        rList = new ArrayList<>();
        sList = new ArrayList<>();
        tList = new ArrayList<>();
        uList = new ArrayList<>();
        vList = new ArrayList<>();
        wList = new ArrayList<>();
        xList = new ArrayList<>();
        yList = new ArrayList<>();
        zList = new ArrayList<>();
        aaList = new ArrayList<>();
        abList = new ArrayList<>();
    }

}
