package fr.android.myboxer;

import java.util.Calendar;

public class Match {
    private Opposant opposant1;
    private Opposant opposant2;
    private Calendar date;
    private boolean gagne;

    public Match(Opposant opposant1, Opposant opposant2, Calendar date, boolean gagne) {
        this.opposant1 = opposant1;
        this.opposant2 = opposant2;
        this.date = date;
        this.gagne = gagne;
    }

    public Opposant getOpposant1() {
        return opposant1;
    }

    public Opposant getOpposant2() {
        return opposant2;
    }

    public Calendar getDate() {
        return date;
    }

    public boolean isGagne() {
        return gagne;
    }
}
