package com.student.wgu.C196.ftofani.Entities;

//Lesson 2 29:00
//Generate setter and getter

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="thing_table")
public class Thing {
    @PrimaryKey(autoGenerate = true)
    private int thingID;

    private String thingName;

    public Thing(int thingID, String thingName) {
        this.thingID = thingID;
        this.thingName = thingName;
    }

    public int getThingID() {
        return thingID;
    }

    public void setThingID(int thingID) {
        this.thingID = thingID;
    }

    public String getThingName() {
        return thingName;
    }

    public void setThingName(String thingName) {
        this.thingName = thingName;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "thingName='" + thingName + '\'' +
                '}';
    }
}
