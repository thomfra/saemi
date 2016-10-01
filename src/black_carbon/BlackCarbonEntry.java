package black_carbon;

import java.util.Calendar;

public class BlackCarbonEntry {

    public Calendar date;
    public double value;

    public BlackCarbonEntry(Calendar date, double value) {
        this.date = date;
        this.value = value;
    }
}
