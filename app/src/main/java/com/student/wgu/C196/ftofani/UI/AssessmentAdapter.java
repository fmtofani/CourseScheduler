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
import com.student.wgu.C196.ftofani.Entities.Assessment;

// Required Adapter for Room
public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItemView;
        private final TextView assessmentItemView2;
        private final TextView assessmentItemView3;

        private AssessmentViewHolder(View itemView) {
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.assessmentTextView);
            assessmentItemView2 = itemView.findViewById(R.id.assessmentTextView2);
            assessmentItemView3 = itemView.findViewById(R.id.assessmentTextView3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Assessment current = mAssessment.get(position);
                    Intent intent = new Intent(context, EditAssessment.class);
                    intent.putExtra("assessmentID", current.getAssessmentID());
                    intent.putExtra("assessmentName", current.getAssessmentName());
                    intent.putExtra("assessmentStartDate", current.getAssessmentType());
                    intent.putExtra("assessmentEndDate", current.getAssessmentDate());
                    intent.putExtra("assessmentCourseID", current.getAssessmentCourseID());
                    context.startActivity(intent);
                }
            });
        }

    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<Assessment> mAssessment; // Cached copy of words

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public AssessmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AssessmentViewHolder holder, int position) {
        if (mAssessment != null) {
            Assessment current = mAssessment.get(position);
            holder.assessmentItemView.setText(current.getAssessmentName());
            holder.assessmentItemView2.setText(current.getAssessmentDate());
            holder.assessmentItemView3.setText(current.getAssessmentType());
        } else {
            holder.assessmentItemView.setText("No Word");
            holder.assessmentItemView2.setText("No Word");
            holder.assessmentItemView3.setText("No Word");
        }
    }

    public void setAssessments(List<Assessment> words) {
        mAssessment = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mAssessment != null)
            return mAssessment.size();
        else return 0;
    }

}