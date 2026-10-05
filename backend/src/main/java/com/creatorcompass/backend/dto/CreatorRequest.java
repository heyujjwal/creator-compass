package com.creatorcompass.backend.dto;

public class CreatorRequest {

    private String mood;
    private String daySummary;
    private String availableTime;

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getDaySummary() {
        return daySummary;
    }

    public void setDaySummary(String daySummary) {
        this.daySummary = daySummary;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }
}