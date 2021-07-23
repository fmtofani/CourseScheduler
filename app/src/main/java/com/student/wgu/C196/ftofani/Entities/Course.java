package com.student.wgu.C196.ftofani.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="course_table")
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int courseID;

    private String courseName;

    public Course(int courseID, String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
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

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                '}';
    }

}
