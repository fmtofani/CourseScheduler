package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.student.wgu.C196.ftofani.Database.Repository;
import com.student.wgu.C196.ftofani.Entities.Course;
import com.student.wgu.C196.ftofani.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditCourse extends AppCompatActivity {

//
//
// Add Note Button
// Add Mentor Button
//
//
//
    Repository repository;
    int id;
    String name;
    EditText editID;
    EditText editName;
    Course currentCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = getIntent().getStringExtra("name");
        editID = findViewById(R.id.courseID);
        editName = findViewById(R.id.courseName);
        editName.setText(name);
        repository = new Repository(getApplication());

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sharemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem Item) {
        switch (Item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
// Sharing method. If using an emulator, you must have gmail setup or else Conversations setup in Messages to send a text
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                // (Optional) Here we're setting the title of the content
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Send message title");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
/*
                //Setup Notifications
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
        }
        return super.onOptionsItemSelected(Item);
    }

    /*
        public void saveCourse(View view) {
            String screenName = editName.getText().toString();
            if (name == null) {
                id=repository.getAllCourses().get(repository.getAllCourses().size()-1).getCourseID();
                Course newCourse = new Course(++id, screenName);
                repository.insert(newCourse);
            } else {
                Course oldCourse = new Course(++id, screenName);
                repository.update(oldCourse);
            }
        }
    */
    public void date(View view) {
    }


}