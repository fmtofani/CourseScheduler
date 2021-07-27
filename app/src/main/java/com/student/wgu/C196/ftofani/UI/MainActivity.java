package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.student.wgu.C196.ftofani.Database.Repository;
import com.student.wgu.C196.ftofani.Entities.Assessment;
import com.student.wgu.C196.ftofani.Entities.Course;
import com.student.wgu.C196.ftofani.Entities.Mentor;
import com.student.wgu.C196.ftofani.Entities.Note;
import com.student.wgu.C196.ftofani.Entities.Term;
import com.student.wgu.C196.ftofani.R;

public class MainActivity extends AppCompatActivity {
    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = new Repository(getApplication());
        Term term = new Term(1, "1", "01-01-21", "01-02-21");
        repository.insert(term);
        term = new Term(2, "2", "02-01-21", "02-02-21");
        repository.insert(term);
        Course course = new Course(1, "English", "in-progress", "01/01/21", "01/02/21", 1);
        repository.insert(course);
        course = new Course(2, "Biology", "in-progress", "01/01/21", "01/02/21", 1);
        repository.insert(course);
        Assessment assessment = new Assessment(1, "Algebra", "Exam", "01/03/21", 1);
        repository.insert(assessment);
        assessment = new Assessment(2, "Biology", "Project", "03/02/21", 1);
        repository.insert(assessment);
        Mentor mentor = new Mentor(1, "John Smith", "123-123-1232", "john.smith@wgu.edu", 1);
        repository.insert(mentor);
        mentor = new Mentor(2, "Joan Sanders", "212-123-3232", "joan.sanders@wgu.edu", 2);
        repository.insert(mentor);
        Note note = new Note(1, "CH2 study", "Ch2 page 98 needs refreshed", 1);
        repository.insert(note);
        note = new Note(2, "Ch3 exam", "I need to take more time in Ch3", 2);
        repository.insert(note);

    }

    //Goto Terms Intent
    public void goToTerms(View view) {
        Intent intent = new Intent(MainActivity.this, ListTerm.class);
        startActivity(intent);
    }

    //Goto Courses Intent
    public void goToCourse(View view) {
        Intent intent = new Intent(MainActivity.this, ListCourse.class);
        startActivity(intent);
    }

    //Goto Assessment Intent
    public void goToAssessment(View view) {
        Intent intent = new Intent(MainActivity.this, ListAssessment.class);
        startActivity(intent);
    }
}

/*
    // Create hamburger menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sharemenu, menu);
        return true;
*/


/*
            // Setup Notifications
            case R.id.notify:
                String dateFromScreen=courseDateStart.getText().toString();
                String myFormat ="MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate=null;
                try {
                    myDate=sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger= myDate.getTime();
                Intent intent = new Intent(EditCourse.this, MyReceiver.class);
                intent.putExtra("key", "message I want to send");
                PendingIntent sender=PendingIntent.getBroadcast(EditCourse.this, ++MainActivity.numAlert,intent, 0);
                return true;
*/


/*

    ---------   TIPS    ----------

ctrl+alt+L ==  CLean up indentation

<!--   --> comment out xml

Change name in manifest for title bar




 */


//Lesson 1
//55:50 1:11 Create Landscape variation
//
//Lesson 4 35:00
//44:15 datepicker
//1:08:00 NOTIFY for different
//1:14 debug
//
//Lesson 4 1:36 delete with something in the repository
//
//
//    At least 5 assessments for each course
//
//    Make sure the EditTerm Shows the Terms Name, StartDate and EndDate
//
//    Allow user to enter alert for start and end dates for each course
//
//    Allow user to enter alert for start and end dates for each assessment
//
//    Notes can be shared
//
//    Allow user to enter alert for start and end dates for each assessment
//
//
//
/*
    Term Names: Term 1, Term 2, Spring

    Course Status: in progress, completed, dropped, plan to take

    Assessments: Performance or Objective

    Detailed: Course, Term and Assessment


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


D.  Create a storyboard to demonstrate application flow that includes each of the menus and screens from part B.


E.  Provide screen shots of generating the signed APK to demonstrate that you have created a deployment package.


Note: Verify that all the required functions of your application are working by executing the apk file.


F.  Reflect on the creation of your mobile application by doing the following:

1.  Explain how your application would be different if it were developed for a tablet rather than a phone, including a discussion of fragments and layouts.

2.  Identify the minimum and target operating system your application was developed under and is compatible with.

3.  Describe (suggested length of 1–2 paragraphs) the challenges you faced during the development of the mobile application.

4.  Describe (suggested length of 1–2 paragraphs) how you overcame each challenge discussed in part F3.

5.  Discuss (suggested length of 1–2 paragraphs) what you would do differently if you did the project again.

6.  Describe how emulators are used and the pros and cons of using an emulator versus using a development device.




 */

/*

       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                                  METHODS LEFT OUT FOR NOW
       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


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
//                //  mProductDao.deleteAllProducts();
//                //  mPartDao.deleteAllParts();
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
//        }




            case R.id.refresh:
                repository=new Repository(getApplication());
                List<Course> allCourses=repository.getAllCourses();
                final CourseAdapter courseAdapter=new CourseAdapter(this);
                RecyclerView recyclerView=findViewById(R.id.recyclerviewListCourse);
                recyclerView.setAdapter(courseAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                courseAdapter.setCourses(allCourses);
                return true;





           case R.id.notifyCourseStart:
                String messageEnd = "The WGU course, " + editName.getText().toString() + " ,is set to end today.";
                String endDateFromScreen = editEndDate.getText().toString();
                String myFormat ="MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate=null;
                try {
                    myDate=sdf.parse(endDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger= myDate.getTime();
                Intent intent = new Intent(EditCourse.this, MyReceiver.class);
                intent.putExtra("key", messageStart);
                PendingIntent sender=PendingIntent.getBroadcast(EditCourse.this, ++MainActivity.numAlert,intent, 0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;



        public void saveCourse(View view) {
            String screenName = editName.getText().toString();
            if (name == null) {
                id=repository.getAllCourses().get(repository.getAllCourses().size()-1).getCourseID();
                Course newCourse = new Course(++id, screenName);
                repository.insert(newCourse);
            } else {
                Course oldCourse = new Course(++id, screenName);
                repository.update(oldCourse);


 */