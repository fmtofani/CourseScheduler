package com.student.wgu.C196.ftofani.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="assessment_table")
public class Assessment {


    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String assessmentName;

    public Assessment(int assessmentID, String assessmentName) {
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
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

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentName='" + assessmentName + '\'' +
                '}';
    }

}
