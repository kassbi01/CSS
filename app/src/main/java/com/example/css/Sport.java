package com.example.css;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;




@Entity(tableName = "diary_table")
public class Sport
{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int eventId;

    private String eventName;

    private String date;

    private String eventLocation;


    public void setSport(String eventName, String date, String eventLocation) {
        this.eventName = eventName;
        this.date = date;
        this.eventLocation = eventLocation;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

}



