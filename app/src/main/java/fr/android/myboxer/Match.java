package fr.android.myboxer;

import java.util.Calendar;

public class Match {
    private Opposant opposant1;
    private Opposant opposant2;
    private Calendar date;

    public Match(Opposant opposant1, Opposant opposant2, Calendar date) {
        this.opposant1 = opposant1;
        this.opposant2 = opposant2;
        this.date = date;
    }
}
