package com.example.coronacasestracker.model;

public class CoronaStats {


    private String country;
    private int newCases;
    private  int newdeath;
    private int latestTotalCases;
    private int diffFromPrevDay;

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public int getNewdeath() {
        return newdeath;
    }

    public void setNewdeath(int newdeath) {
        this.newdeath = newdeath;
    }

    public int getDiffFromPrevDay() {
        return diffFromPrevDay;
    }

    public void setDiffFromPrevDay(int diffFromPrevDay) {
        this.diffFromPrevDay = diffFromPrevDay;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }


}