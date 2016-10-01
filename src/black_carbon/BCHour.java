package black_carbon;

import reader.Day;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BCHour {

    public ArrayList<Double> values = new ArrayList<>();

    public Calendar actualHour;
    public int hourOfDay;
    public double average;
    public double median;

    public BCHour(Date date, BCDay currentDay) {
        actualHour = Calendar.getInstance();
        actualHour.setTime(date);
        hourOfDay = actualHour.get(Calendar.HOUR_OF_DAY);
    }

    public void addtoList(double d){
        values.add(d);
    }

    public void calculateAvarage(){
        double sum = 0;
        if(values.size() > 0) {
            for (int i = 0; i < values.size(); i++) {
                sum += values.get(i);
            }
            average = sum / values.size();
        }
    }

    public void calculateMedian() {
        if(values.size() > 0) {
            int middle = values.size()/2;
            median = values.get(middle);
        }

    }
}
