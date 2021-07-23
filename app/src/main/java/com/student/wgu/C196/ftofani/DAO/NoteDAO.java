package com.student.wgu.C196.ftofani.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.student.wgu.C196.ftofani.Entities.Thing;

import java.util.List;

public interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)

    void insert(Note note);

    @Update
    void update (Note note);

    @Delete
    void delete (Note note);

    @Query("SELECT * FROM NOTE_TABLE ORDER BY noteID ASC")
    List<Thing> getAllNotes();
}
