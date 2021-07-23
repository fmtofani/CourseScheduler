package com.student.wgu.C196.ftofani.Database;

import android.app.Application;

import com.student.wgu.C196.ftofani.DAO.AssessmentDAO;
import com.student.wgu.C196.ftofani.DAO.CourseDAO;
import com.student.wgu.C196.ftofani.DAO.MentorDAO;
import com.student.wgu.C196.ftofani.DAO.NoteDAO;
import com.student.wgu.C196.ftofani.DAO.TermDAO;
import com.student.wgu.C196.ftofani.DAO.ThingDao;
import com.student.wgu.C196.ftofani.Entities.Assessment;
import com.student.wgu.C196.ftofani.Entities.Course;
import com.student.wgu.C196.ftofani.Entities.Mentor;
import com.student.wgu.C196.ftofani.Entities.Note;
import com.student.wgu.C196.ftofani.Entities.Term;
import com.student.wgu.C196.ftofani.Entities.Thing;

import java.util.List;

public class Repository {
    private ThingDao mThingDAO;
    private List<Thing> mAllThings;
    private TermDAO mTermDAO;
    private List<Term> mAllTerms;
    private CourseDAO mCourseDAO;
    private List<Course> mAllCourses;
    private AssessmentDAO mAssessmentDAO;
    private List<Assessment> mAllAssessments;
    private MentorDAO mMentorDao;
    private List<Mentor> mAllMentors;
    private NoteDAO mNoteDao;
    private List<Note> mAllNotes;

    //private static int NUMBER_OF_THREADS=4;
   // static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public Repository(Application application) {
        DatabaseBuilder db = DatabaseBuilder.getDatabase(application);
        mThingDAO=db.thingDAO();
        mTermDAO=db.termDAO();
    }

    //Delete once working
    public List<Thing> getAllThings() {
        mAllThings=mThingDAO.getAllThings();
        return mAllThings;
    }

    public void insert(Thing thing) {
            mThingDAO.insert(thing);
    }

    public void delete(Thing thing) {
            mThingDAO.delete(thing);
    }

    public void update(Thing thing) {
            mThingDAO.update(thing);
    }

    //Terms
    public List<Term> getAllTerms(){
        mAllTerms=mTermDAO.getAllTerms(){
        return mAllTerms;
    }

    public void insert(Term term){
        mTermDAO.insert(term);
    }

    public void delete(Term term) {
        mTermDAO.delete(term);
    }

    public void update(Term term) {
        mTermDAO.update(term);
    }

    //Courses
    public List<Course> getAllCourses(){
        mAllCourses=mCourseDAO.getAllCourses(){
            return mAllCourses;
    }

    public void insert(Course course){
        mCourseDAO.insert(course);
    }

    public void delete(Course course) {
        mCourseDAO.delete(course);
    }

    public void update(Course course) {
        mCourseDAO.update(course);
    }

    //Assessments
    public List<Assessment> getAllAssessments(){
        mAllAssessments=mAssessmentDAO.getAllAssessments(){
        return mAllAssessments;
    }

    public void insert(Assessment assessment){
        mAssessmentDAO.insert(assessment);
    }

    public void delete(Assessment assessment) {
        mAssessmentDAO.delete(assessment);
    }

    public void update(Assessment assessment) {
        mAssessmentDAO.update(assessment);
    }

    //Mentors
    public List<Mentor> getAllMentors(){
        mAllMentors=mMentorDAO.getAllMentors(){
        return mAllMentors;
    }

    public void insert(Mentor mentor){
        mMentorDAO.insert(mentor);
    }

    public void delete(Mentor mentor) {
        mMentorDAO.delete(mentor);
    }

    public void update(Mentor mentor) {
        mMentorDAO.update(mentor);
    }

    //Notes
    public List<Note> getAllNotes(){
        mAllNotes=mNoteDAO.getAllNotes(){
        return mAllMentors;
    }

    public void insert(Note note){
        mNoteDAO.insert(Note);
    }

    public void delete(Note note) {
        mNoteDAO.delete(note);
    }

    public void update(Note note) {
        mNoteDAO.update(note);
    }

}
