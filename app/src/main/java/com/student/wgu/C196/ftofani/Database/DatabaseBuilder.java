package com.student.wgu.C196.ftofani.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.student.wgu.C196.ftofani.DAO.AssessmentDAO;
import com.student.wgu.C196.ftofani.DAO.CourseDAO;
import com.student.wgu.C196.ftofani.DAO.MentorDAO;
import com.student.wgu.C196.ftofani.DAO.NoteDAO;
import com.student.wgu.C196.ftofani.DAO.TermDAO;
import com.student.wgu.C196.ftofani.Entities.Assessment;
import com.student.wgu.C196.ftofani.Entities.Course;
import com.student.wgu.C196.ftofani.Entities.Mentor;
import com.student.wgu.C196.ftofani.Entities.Note;
import com.student.wgu.C196.ftofani.Entities.Term;

// Required Database for Room
@Database(entities = {Term.class, Course.class, Assessment.class, Mentor.class, Note.class}, version = 1, exportSchema = false)
public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract TermDAO termDAO();

    public abstract CourseDAO courseDAO();

    public abstract AssessmentDAO assessmentDAO();

    public abstract MentorDAO mentorDAO();

    public abstract NoteDAO noteDAO();

    private static volatile DatabaseBuilder INSTANCE;

    static DatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "MyDatabase.db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
