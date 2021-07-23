package com.student.wgu.C196.ftofani.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="term_table")
public class Term {

    @PrimaryKey(autoGenerate = true)
    private int termID;

    private String termName;

    public Term(int termID, String termName) {
        this.termID = termID;
        this.termName = termName;
    }


    public int getTermID() {

        return termID;
    }

    public void setTermID(int termID) {

        this.termID = termID;
    }

    public String getTermName() {

        return termName;
    }

    public void setTermName(String termName) {

        this.termName = termName;
    }

    @Override
    public String toString() {
        return "Term{" +
                "termName='" + termName + '\'' +
                '}';
    }

}
