package reader;

import excel.DateFormater;

import java.util.ArrayList;
import java.util.Date;

public class Day {

    public ArrayList<Hour> hours;
    public Date actualDate;

    public Day(Date date) {
        hours = new ArrayList<>();
        actualDate = DateFormater.removeHoursAndMinute(date);
    }

    public void addHour(Hour hour){
        hours.add(hour);
    }
}
