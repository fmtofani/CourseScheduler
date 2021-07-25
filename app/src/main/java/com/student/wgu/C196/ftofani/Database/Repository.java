package com.student.wgu.C196.ftofani.Database;

import android.app.Application;

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

import java.util.List;

public class Repository {
    private TermDAO mTermDAO;
    private List<Term> mAllTerms;
    private CourseDAO mCourseDAO;
    private List<Course> mAllCourses;
    private AssessmentDAO mAssessmentDAO;
    private List<Assessment> mAllAssessments;
    private MentorDAO mMentorDAO;
    private List<Mentor> mAllMentors;
    private NoteDAO mNoteDAO;
    private List<Note> mAllNotes;


    public Repository(Application application) {
        DatabaseBuilder db = DatabaseBuilder.getDatabase(application);
        mTermDAO = db.termDAO();
        mCourseDAO = db.courseDAO();
        mAssessmentDAO = db.assessmentDAO();
        mMentorDAO = db.mentorDAO();
        mNoteDAO = db.noteDAO();
    }


    //Terms
    public List<Term> getAllTerms() {
        mAllTerms = mTermDAO.getAllTerms();
        return mAllTerms;
    }

    public void insert(Term term) {
        mTermDAO.insert(term);
    }

    public void delete(Term term) {
        mTermDAO.delete(term);
    }

    public void update(Term term) {
        mTermDAO.update(term);
    }

    //Courses
    public List<Course> getAllCourses() {
        mAllCourses = mCourseDAO.getAllCourses();
        return mAllCourses;
    }

    public void insert(Course course) {
        mCourseDAO.insert(course);
    }

    public void delete(Course course) {
        mCourseDAO.delete(course);
    }

    public void update(Course course) {
        mCourseDAO.update(course);
    }

    //Assessments
    public List<Assessment> getAllAssessments() {
        mAllAssessments = mAssessmentDAO.getAllAssessments();
        return mAllAssessments;
    }

    public void insert(Assessment assessment) {
        mAssessmentDAO.insert(assessment);
    }

    public void delete(Assessment assessment) {
        mAssessmentDAO.delete(assessment);
    }

    public void update(Assessment assessment) {
        mAssessmentDAO.update(assessment);
    }

    //Mentors
    public List<Mentor> getAllMentors() {
        mAllMentors = mMentorDAO.getAllMentors();
        return mAllMentors;
    }

    public void insert(Mentor mentor) {
        mMentorDAO.insert(mentor);
    }

    public void delete(Mentor mentor) {
        mMentorDAO.delete(mentor);
    }

    public void update(Mentor mentor) {
        mMentorDAO.update(mentor);
    }

    //Notes
    public List<Note> getAllNotes() {
        mAllNotes = mNoteDAO.getAllNotes();
        return mAllNotes;
    }

    public void insert(Note note) {
        mNoteDAO.insert(note);
    }

    public void delete(Note note) {
        mNoteDAO.delete(note);
    }

    public void update(Note note) {
        mNoteDAO.update(note);
    }

}
