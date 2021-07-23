package com.student.wgu.C196.ftofani.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.student.wgu.C196.ftofani.Entities.Thing;

import java.util.List;

public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)

    void insert(Term term);

    @Update
    void update (Term term);

    @Delete
    void delete (Term term);

    @Query("SELECT * FROM TERM_TABLE ORDER BY termID ASC")
    List<Term> getAllTerms();
}
