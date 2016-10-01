package excel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ResultRow {

    private Date d;
    private int hour;
    private Calendar date;
    public double niluAverage;
    public double ntnuAverage;
    public double phg;
    public double rgm;
    public double dValue, eValue, fValue, gValue, hValue, iValue, jValue, kValue, lValue, mValue, nValue, oValue, pValue, qValue, rValue, sValue, tValue, uValue, vValue, wValue, xValue, yValue, zValue, aaValue, abValue;

    public ResultRow(Calendar cal, double niluAverage, double ntnuAverage, double pgh, double rgm) {
        d = cal.getTime();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        this.date = cal;
        this.niluAverage = niluAverage;
        this.ntnuAverage = ntnuAverage;
        this.phg = pgh;
        this.rgm = rgm;
    }

    public ResultRow(Calendar cal, double dValue, double eValue,double fValue,double gValue,double hValue,double iValue, double jValue, double kValue, double lValue,double mValue, double nValue, double oValue, double pValue, double qValue,
                     double rValue, double sValue, double tValue, double uValue,  double vValue, double wValue,  double xValue, double yValue, double zValue, double aaValue, double abValue){
        d = cal.getTime();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        this.dValue = dValue;
        this.eValue = eValue;
        this.fValue = fValue;
        this.gValue = gValue;
        this.hValue = hValue;
        this.iValue = iValue;
        this.jValue = jValue;
        this.kValue = kValue;
        this.lValue = lValue;
        this.mValue = mValue;
        this.nValue = nValue;
        this.oValue = oValue;
        this.pValue = pValue;
        this.qValue = qValue;
        this.rValue = rValue;
        this.sValue = sValue;
        this.tValue = tValue;
        this.uValue = uValue;
        this.vValue = vValue;
        this.wValue = wValue;
        this.xValue = xValue;
        this.yValue = yValue;
        this.zValue = zValue;
        this.aaValue = aaValue;
        this.abValue = abValue;
    }

    public Date getDate(){
        return d;
    }

}
