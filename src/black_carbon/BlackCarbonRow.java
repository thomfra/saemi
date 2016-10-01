package black_carbon;

import java.util.Calendar;

public class BlackCarbonRow {

    public Calendar date;
    public double value;
    public double median;


    public BlackCarbonRow(Calendar actualHour, double averageOfHour, double median) {
        this.date = actualHour;
        this.value = averageOfHour;
        this.median = median;
    }
}
