package com.student.wgu.C196.ftofani.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.student.wgu.C196.ftofani.Entities.Assessment;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessment assessment);

    @Update
    void update(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("SELECT * FROM assessment_table ORDER BY assessmentID ASC")
    List<Assessment> getAllAssessments();

    @Query("DELETE FROM assessment_table")
    void deleteAllAssessments();
}
