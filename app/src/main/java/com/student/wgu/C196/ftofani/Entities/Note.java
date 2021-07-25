package com.student.wgu.C196.ftofani.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int noteID;
    private String noteName;
    private String noteBody;
    private int noteCourseID;

    public Note(int noteID, String noteName, String noteBody, int noteCourseID) {
        this.noteID = noteID;
        this.noteName = noteName;
        this.noteBody = noteBody;
        this.noteCourseID = noteCourseID;
    }


    public int getNoteID() {

        return noteID;
    }

    public void setNoteID(int noteID) {

        this.noteID = noteID;
    }

    public String getNoteName() {

        return noteName;
    }

    public void setNoteName(String noteName) {

        this.noteName = noteName;
    }


    public String getNoteBody() {

        return noteBody;
    }

    public void setNoteBody(String noteBody) {

        this.noteBody = noteBody;
    }

    public int getNoteCourseID() {

        return noteCourseID;
    }

    public void setNoteCourseID(int noteCourseID) {

        this.noteID = noteID;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteName='" + noteName + '\'' +
                '}';
    }

}
