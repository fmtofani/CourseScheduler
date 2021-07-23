package com.student.wgu.C196.ftofani.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.student.wgu.C196.ftofani.Entities.Thing;

import java.util.List;

public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)

    void insert(Thing thing);

    @Update
    void update (Assessment assessment);

    @Delete
    void delete (Assessment assessment);

    @Query("SELECT * FROM ASSESSMENT_TABLE ORDER BY assessmentID ASC")
    List<Assessment> getAllAssessments();
}
