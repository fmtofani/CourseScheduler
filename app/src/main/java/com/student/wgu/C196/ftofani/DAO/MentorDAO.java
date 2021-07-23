package com.student.wgu.C196.ftofani.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.student.wgu.C196.ftofani.Entities.Thing;

import java.util.List;

public interface MentorDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)

    void insert(Mentor mentor);

    @Update
    void update (Mentor mentor);

    @Delete
    void delete (Mentor mentor);

    @Query("SELECT * FROM MENTOR_TABLE ORDER BY mentorID ASC")
    List<Mentor> getAllMentors();
}
