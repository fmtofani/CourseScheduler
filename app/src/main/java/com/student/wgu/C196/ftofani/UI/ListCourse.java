package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.student.wgu.C196.ftofani.Database.Repository;
import com.student.wgu.C196.ftofani.Entities.Thing;
import com.student.wgu.C196.ftofani.R;

import java.util.List;

public class ListCourse extends AppCompatActivity {


    private Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        repository=new Repository(getApplication());
        List<Thing> allThings=repository.getAllThings();

        RecyclerView recyclerView=findViewById((R.id.recyclerviewListCourse));
        final ThingAdapter thingAdapter=new ThingAdapter(this);
        recyclerView.setAdapter(thingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        thingAdapter.setThings(allThings);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recyclerview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem Item) {
        switch (Item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.refresh:
                repository=new Repository(getApplication());
                List<Thing> allThings=repository.getAllThings();
                final ThingAdapter thingAdapter=new ThingAdapter(this);
                RecyclerView recyclerView=findViewById(R.id.recyclerviewListCourse);
                recyclerView.setAdapter(thingAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                thingAdapter.setThings(allThings);
        }
        return super.onOptionsItemSelected(Item);
    }

    public void goToEditCourses(View view) {
        Intent intent = new Intent(ListCourse.this, EditCourse.class);
        startActivity(intent);
    }
}