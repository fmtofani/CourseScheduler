package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.student.wgu.C196.ftofani.Database.Repository;
import com.student.wgu.C196.ftofani.Entities.Course;
import com.student.wgu.C196.ftofani.Entities.Mentor;
import com.student.wgu.C196.ftofani.Entities.Note;
import com.student.wgu.C196.ftofani.R;

import java.util.ArrayList;
import java.util.List;

public class EditNote extends AppCompatActivity {

    Repository repository;
    int Id;
    int numCourseID;
    String name;
    String body;
    EditText editName;
    EditText editBody;
    Note currentNote;
    Course currentCourse;
    public static int numNotes;

    // For Spinner
    private Spinner courseSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Id = getIntent().getIntExtra("noteID", -1);
        repository = new Repository(getApplication());
        for (Note p : repository.getAllNotes()) {
            if (p.getNoteID() == Id) {
                currentNote = p;
            }
        }

       numCourseID = getIntent().getIntExtra("noteCourseID", -1);
        repository = new Repository(getApplication());
        for (Course c : repository.getAllCourses()) {
            if (c.getCourseID() == numCourseID) {
                currentCourse = c;
            }
        }

        editName = findViewById(R.id.editTextNoteName);
        editBody = findViewById(R.id.editTextNoteBody);

        // Setup Spinner to Select CourseID
        courseSpinner = findViewById(R.id.spinnerNoteCourseID);
        List<String> courseList = new ArrayList<>();
        repository = new Repository(getApplication());
        for (Course p : repository.getAllCourses()) {
            courseList.add(p.getCourseName());
        }

        ArrayAdapter<String> courseAdapter = new ArrayAdapter<>(this,
        android.R.layout.simple_spinner_item, courseList);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);

        if (currentNote != null) {
            editName.setText(currentNote.getNoteName());
            editBody.setText(currentNote.getNoteBody());
            courseSpinner.setSelection(numCourseID-1);
        }

        // Keep track of how many Notes
        List<Note> filteredNotes = new ArrayList<>();
        for (Note p : repository.getAllNotes()) {
            if (p.getNoteID() == Id) filteredNotes.add(p);
        }
        numNotes = filteredNotes.size();

    }

    // This method sets up the hamburger menu in the title area
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            //Setup an intent to share the note
            case R.id.share:
                name = editName.getText().toString();
                body = editBody.getText().toString();

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TITLE, name);
                sendIntent.putExtra(Intent.EXTRA_TEXT, body);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;

        }
        return super.onOptionsItemSelected(item);

    }

    public void addNote(View view) {

        String tryName = editName.getText().toString();
        String goodName = "";
        if (tryName.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter a NAME", Toast.LENGTH_LONG).show();
            return;
        } else goodName = tryName;

        String tryBody = editBody.getText().toString();
        String goodBody = "";
        if (tryBody.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter a PHONE NUMBER", Toast.LENGTH_LONG).show();
            return;
        } else goodBody = tryBody;

        String courseName = (String) courseSpinner.getSelectedItem();
        int courseID = -1;
        repository = new Repository(getApplication());
        for (Course p : repository.getAllCourses()) {
            if (p.getCourseName().equals(courseName)) {
                courseID = p.getCourseID();
            }
        }

        if (Id == -1) {
            Id = setNoteID();
            Note p = new Note(Id, goodName, goodBody, courseID);
            repository.insert(p);
        } else {
            Note p = new Note(Id, goodName, goodBody, courseID);
            repository.update(p);
        }
        Intent intent = new Intent(EditNote.this, ListNote.class);
        startActivity(intent);
    }

    int num = 0;
    public int setNoteID() {
        num = 0;
        for (Note m : repository.getAllNotes()) {
            if (m.getNoteID() >= num) {
                num = m.getNoteID();
                num++;
            }
        }
        return num;
    }

    // Delete note button method
    public void deleteNote(View view) {
        if (Id == -1) {
            Intent intent = new Intent(EditNote.this, ListNote.class);
            startActivity(intent);
        } else {
            repository.delete(currentNote);
            Toast.makeText(getApplicationContext(), "Note Deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(EditNote.this, ListNote.class);
            startActivity(intent);
        }
    }

}