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

    //   private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
//
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//
//                PartDAO mPartDao = INSTANCE.partDAO();
//                ProductDAO mProductDao = INSTANCE.productDAO();
//
//                // Start the app with a clean database every time.
//                // Not needed if you only populate on creation.
//              //  mProductDao.deleteAllProducts();
//              //  mPartDao.deleteAllParts();
//
//                PartEntity part = new PartEntity(1, "wheel", 3.0, 1);
//                mPartDao.insert(part);
//                part = new PartEntity(2, "brake", 4.0, 2);
//                mPartDao.insert(part);
//                part = new PartEntity(3, "chain", 4.0, 1);
//                mPartDao.insert(part);
//
//                ProductEntity product = new ProductEntity(1, "bike", 6.0);
//                mProductDao.insert(product);
//                product = new ProductEntity(2, "trike", 8.0);
//                mProductDao.insert(product);
//
//        }
//
}
