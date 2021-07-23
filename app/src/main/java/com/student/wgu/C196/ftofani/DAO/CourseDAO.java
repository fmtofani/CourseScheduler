package com.student.wgu.C196.ftofani.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.student.wgu.C196.ftofani.Entities.Thing;

import java.util.List;

public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)

    void insert(Thing thing);

    @Update
    void update (Course course);

    @Delete
    void delete (Course course);

    @Query("SELECT * FROM COURSE_TABLE ORDER BY courseID ASC")
    List<Course> getAllCourses();

}
