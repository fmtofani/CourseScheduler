package com.student.wgu.C196.ftofani.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Required Entitys for Room
@Entity(tableName = "mentor_table")
public class Mentor {

    @PrimaryKey(autoGenerate = true)
    private int mentorID;
    private String mentorName;
    private String mentorPhone;
    private String mentorEmail;
    private int mentorCourseID;

    public Mentor(int mentorID, String mentorName, String mentorPhone, String mentorEmail, int mentorCourseID) {
        this.mentorID = mentorID;
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;
        this.mentorCourseID = mentorCourseID;
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

    public void setMentorPhone(String mentorPhone) {
        this.mentorPhone = mentorPhone;
    }

    public String getMentorPhone() {
        return mentorPhone;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public void setMentorCourseID(int mentorCourseID) {
        this.mentorCourseID = mentorCourseID;
    }

    public int getMentorCourseID() {
        return mentorCourseID;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "mentorName='" + mentorName + '\'' +
                '}';
    }


}
