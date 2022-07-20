package com.example.teamsapp.model;

public class Team {
    private int logo;
    private String name;
    private String location;
    private String titles;

    public Team(int logo, String name, String location, String titles) {
        this.logo = logo;
        this.name = name;
        this.location = location;
        this.titles = titles;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }
}
