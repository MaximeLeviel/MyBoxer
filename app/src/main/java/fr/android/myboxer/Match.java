package fr.android.myboxer;

import java.util.Calendar;

public class Match {
    private Opposant opposant1;
    private Opposant opposant2;
    private Calendar date;
    private boolean gagne;
    private double lat;
    private double lng;

    public Match(Opposant opposant1, Opposant opposant2, Calendar date, boolean gagne) {
        this.opposant1 = opposant1;
        this.opposant2 = opposant2;
        this.date = date;
        this.gagne = gagne;
    }

    public Match(Opposant opposant1, Opposant opposant2, Calendar date, boolean gagne, double lat, double lng) {
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
    public String getMatchDescription(){
        String gagnant;
        if (gagne){
            gagnant = this.opposant1.getNom();
        }
        else
            gagnant = this.opposant2.getNom();
        return this.opposant1.getNom() + " vs " + this.opposant2.getNom();
    }
}
