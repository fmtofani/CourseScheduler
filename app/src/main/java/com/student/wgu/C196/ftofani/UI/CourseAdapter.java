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


        private CourseViewHolder(View itemView) {
            super(itemView);
            courseItemView = itemView.findViewById(R.id.courseTextView);
            courseItemView2 = itemView.findViewById(R.id.courseTextView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Course current = mCourses.get(position);
                    Intent intent = new Intent(context, DetailedCourse.class);
                    intent.putExtra("courseID",current.getCourseID());
                    intent.putExtra("assessmentName", current.getCourseName());
                 //   intent.putExtra("coursePrice", Double.toString(current.getPartPrice()));
                    intent.putExtra("position",position);
                 //   intent.putExtra("productID", current.getProductID());
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
        this.context=context;
    }

    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);

        return new CourseAdapter.CourseViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        if (mCourses != null) {
            Course current = mCourses.get(position);
            holder.courseItemView.setText(current.getCourseName());
            holder.courseItemView2.setText(Integer.toString(current.getCourseID()));
        } else {
            // Covers the case of data not being ready yet.
            holder.courseItemView.setText("No Word");
            holder.courseItemView2.setText("No Word");
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


