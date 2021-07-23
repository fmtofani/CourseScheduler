package com.student.wgu.C196.ftofani.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="mentor_table")
public class Mentor {

    @PrimaryKey(autoGenerate = true)
    private int mentorID;

    private String mentorName;

    public Mentor(int mentorID, String mentorName) {
        this.mentorID = mentorID;
        this.mentorName = mentorName;
    }


    public int getMentorID() {

        return mentorID;
    }

    public void setMentorID(int mentorID) {

        this.mentorID = mentorID;
    }

    public String getMentorName() {

        return mentorName;
    }

    public void setMentorName(String mentorName) {

        this.mentorName = mentorName;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "mentorName='" + mentorName + '\'' +
                '}';
    }


}
