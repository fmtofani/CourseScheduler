package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.student.wgu.C196.ftofani.Database.Repository;
import com.student.wgu.C196.ftofani.Entities.Mentor;
import com.student.wgu.C196.ftofani.Entities.Note;
import com.student.wgu.C196.ftofani.R;

import java.util.ArrayList;
import java.util.List;

public class EditMentor extends AppCompatActivity {

//
//
//
// Pull courseID From Intent
//
//
//
//
    Repository repository;
    List<Mentor> allMentors;
    int Id;
    String name;
    String phone;
    String email;
    EditText editName;
    EditText editPhone;
    EditText editEmail;
    int courseID;
    Mentor currentMentor;
    public static int numMentors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mentor);
        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Id = getIntent().getIntExtra("mentorID", -1);

        repository = new Repository(getApplication());
        allMentors = repository.getAllMentors();
        for (Mentor p : allMentors) {
            if (p.getMentorID() == Id) {
                currentMentor = p;
            }
        }
        editName = findViewById(R.id.editTextMentorName);
        editPhone = findViewById(R.id.editTextMentorPhone);
        editEmail = findViewById(R.id.editTextMentorEmail);
        if (currentMentor != null) {
            editName.setText(currentMentor.getMentorName());
            editPhone.setText(currentMentor.getMentorPhone());
            editEmail.setText(currentMentor.getMentorEmail());
        }

        List<Mentor> filteredMentors = new ArrayList<>();
        for (Mentor p : repository.getAllMentors()) {
            if (p.getMentorID() == Id) filteredMentors.add(p);
        }
        numMentors = filteredMentors.size();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void addMentor(View view) {
        if (Id == -1) {
            Id = allMentors.get(allMentors.size() - 1).getMentorID();
            Mentor p = new Mentor(++Id, editName.getText().toString(),
                    editPhone.getText().toString(),
                    editEmail.getText().toString(), courseID);
            repository.insert(p);
        } else {
            Mentor p = new Mentor(Id, editName.getText().toString(),
                    editPhone.getText().toString(),
                    editEmail.getText().toString(), courseID);
            repository.update(p);
        }
        Intent intent = new Intent(EditMentor.this, EditCourse.class);
        startActivity(intent);
    }

    public void deleteMentor(View view) {
        if (Id == -1) {
            Intent intent = new Intent(EditMentor.this, EditCourse.class);
            startActivity(intent);
        } else {
            repository.delete(currentMentor);
            Toast.makeText(getApplicationContext(), "Mentor Deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(EditMentor.this, EditCourse.class);
            startActivity(intent);
        }
    }


}