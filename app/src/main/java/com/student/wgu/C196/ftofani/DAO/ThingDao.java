package com.student.wgu.C196.ftofani.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.student.wgu.C196.ftofani.Entities.Thing;

import java.util.List;

@Dao
public interface ThingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Thing thing);

    @Update
    void update (Thing thing);

    @Delete
    void delete (Thing thing);

    @Query("SELECT * FROM THING_TABLE ORDER BY thingID ASC")
    List<Thing> getAllThings();
}
