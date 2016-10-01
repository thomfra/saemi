package black_carbon;

import excel.DateFormater;
import reader.Hour;

import java.util.ArrayList;
import java.util.Date;

public class BCDay {

    public ArrayList<BCHour> hours;
    public Date actualDate;

    public BCDay(Date date) {
        hours = new ArrayList<>();
        actualDate = DateFormater.removeHoursAndMinute(date);
    }

    public void addHour(BCHour hour){
        hours.add(hour);
    }
}
