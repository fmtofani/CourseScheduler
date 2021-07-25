package com.student.wgu.C196.ftofani.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_table")
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String courseName;
    private String courseProgress;
    private String courseStartDate;
    private String courseEndDate;
    private int courseTermID;

    public Course(int courseID, String courseName, String courseProgress, String courseStartDate, String courseEndDate, int courseTermID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseProgress = courseProgress;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseTermID = courseTermID;
    }


    public int getCourseID() {

        return courseID;
    }

    public void setCourseID(int courseID) {

        this.courseID = courseID;
    }

    public String getCourseName() {

        return courseName;
    }

    public void setCourseName(String courseName) {

        this.courseName = courseName;
    }

    public String getCourseProgress() {

        return courseProgress;
    }

    public void setCourseProgress(String courseProgress) {

        this.courseName = courseName;
    }

    public String getCourseStartDate() {

        return courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {

        this.courseStartDate = courseStartDate;
    }

    public String getCourseEndDate() {

        return courseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {

        this.courseEndDate = courseEndDate;
    }

    public int getCourseTermID() {

        return courseTermID;
    }

    public void setCourseTermID(int courseTermID) {

        this.courseTermID = courseTermID;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                '}';
    }

}
