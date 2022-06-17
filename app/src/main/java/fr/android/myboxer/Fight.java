package fr.android.myboxer;

import java.util.Calendar;

public class Fight {
    private final Opposant opposant1;
    private final Opposant opposant2;
    private final Calendar date;
    private final boolean gagne;
    private double lat;
    private double lng;

    public Fight(Opposant opposant1, Opposant opposant2, Calendar date, boolean gagne) {
        this.opposant1 = opposant1;
        this.opposant2 = opposant2;
        this.date = date;
        this.gagne = gagne;
    }

    public Fight(Opposant opposant1, Opposant opposant2, Calendar date, boolean gagne, double lat, double lng) {
        this.opposant1 = opposant1;
        this.opposant2 = opposant2;
        this.date = date;
        this.gagne = gagne;
        this.lat = lat;
        this.lng = lng;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getFightDescription(){
        return this.opposant1.getNom() + " vs " + this.opposant2.getNom();
    }
}
