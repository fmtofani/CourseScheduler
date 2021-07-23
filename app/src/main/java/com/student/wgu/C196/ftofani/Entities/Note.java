package com.student.wgu.C196.ftofani.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int noteID;

    private String noteName;

    public Note(int noteID, String noteName) {
        this.noteID = noteID;
        this.noteName = noteName;
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

    @Override
    public String toString() {
        return "Note{" +
                "noteName='" + noteName + '\'' +
                '}';
    }

}
