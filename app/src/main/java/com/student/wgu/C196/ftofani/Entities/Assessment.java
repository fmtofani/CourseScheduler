package com.student.wgu.C196.ftofani.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessment_table")
public class Assessment {


    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String assessmentName;
    private String assessmentType;
    private String assessmentDate;
    private int assessmentCourseID;

    public Assessment(int assessmentID, String assessmentName, String assessmentType, String assessmentDate, int assessmentCourseID) {
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.assessmentType = assessmentType;
        this.assessmentDate = assessmentDate;
        this.assessmentCourseID = assessmentCourseID;
    }


    public int getAssessmentID() {

        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {

        this.assessmentID = assessmentID;
    }

    public String getAssessmentName() {

        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {

        this.assessmentName = assessmentName;
    }

    public String getAssessmentType() {

        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {

        this.assessmentType = assessmentType;
    }

    public String getAssessmentDate() {

        return assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {

        this.assessmentDate = assessmentDate;
    }

    public int getAssessmentCourseID() {

        return assessmentCourseID;
    }

    public void setAssessmentCourseID(int assessmentCourseID) {

        this.assessmentCourseID = assessmentCourseID;
    }


    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentName='" + assessmentName + '\'' +
                '}';
    }

}
