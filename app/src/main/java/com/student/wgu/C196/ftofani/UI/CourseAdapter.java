package com.student.wgu.C196.ftofani.UI;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.student.wgu.C196.ftofani.R;
import com.student.wgu.C196.ftofani.Entities.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseItemView;
        private final TextView courseItemView2;
        private final TextView courseItemView3;
        private final TextView courseItemView4;
        private final TextView courseItemView5;


        private CourseViewHolder(View itemView) {
            super(itemView);
            courseItemView = itemView.findViewById(R.id.courseTextView);
            courseItemView2 = itemView.findViewById(R.id.courseTextView2);
            courseItemView3 = itemView.findViewById(R.id.courseTextView3);
            courseItemView4 = itemView.findViewById(R.id.courseTextView4);
            courseItemView5 = itemView.findViewById(R.id.courseTextView5);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Course current = mCourses.get(position);
                    Intent intent = new Intent(context, DetailedCourse.class);
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("courseName", current.getCourseName());
                    intent.putExtra("courseProgress", current.getCourseProgress());
                    intent.putExtra("courseStartDate", current.getCourseStartDate());
                    intent.putExtra("courseEndDate", current.getCourseEndDate());
                    intent.putExtra("courseTermId", current.getCourseTermID());
                    context.startActivity(intent);
                }
            });
        }

    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<Course> mCourses; // Cached copy of words

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
        return new CourseViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        if (mCourses != null) {
            final Course current = mCourses.get(position);
            holder.courseItemView.setText(current.getCourseName());
            holder.courseItemView2.setText(current.getCourseProgress());
            holder.courseItemView3.setText(current.getCourseStartDate());
            holder.courseItemView4.setText(current.getCourseEndDate());
            holder.courseItemView5.setText(Integer.toString(current.getCourseTermID()));
        } else {
            // Covers the case of data not being ready
            holder.courseItemView.setText("No Word");
            holder.courseItemView2.setText("No Word");
            holder.courseItemView3.setText("No Word");
            holder.courseItemView4.setText("No Word");
            holder.courseItemView5.setText("No Word");

        }

    }

    public void setCourses(List<Course> words) {
        mCourses = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCourses != null)
            return mCourses.size();
        else return 0;
    }
}


