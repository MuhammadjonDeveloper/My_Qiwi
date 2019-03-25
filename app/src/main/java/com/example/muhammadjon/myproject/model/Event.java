package com.example.muhammadjon.myproject.model;

public class Event {
    private int id;
    private String time;
    private String name;
    private String date;
    private boolean status;
    private String language;

    public Event(String name, String date) {
        this.name = name;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public boolean isStatus() {
        return status;
    }

    public String getLanguage() {
        return language;
    }
}
