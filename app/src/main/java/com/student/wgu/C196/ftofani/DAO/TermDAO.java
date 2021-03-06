package com.student.wgu.C196.ftofani.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.student.wgu.C196.ftofani.Entities.Term;
import java.util.List;

// Required DAO for Room
@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Term term);

    @Update
    void update(Term term);

    @Delete
    void delete(Term term);

    @Query("SELECT * FROM TERM_TABLE ORDER BY termID ASC")
    List<Term> getAllTerms();

    @Query("DELETE FROM term_table")
    void deleteAllTerms();
}
