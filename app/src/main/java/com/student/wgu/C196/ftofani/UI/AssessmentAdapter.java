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

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItemView;
        private final TextView assessmentItemView2;


        private AssessmentViewHolder(View itemView) {
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.assessmentTextView);
            assessmentItemView2 = itemView.findViewById(R.id.assessmentTextView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Assessment current = mAssessment.get(position);
                    Intent intent = new Intent(context, DetailedAssessment.class);
                    intent.putExtra("courseID",current.getAssessmentID());
                    intent.putExtra("assessmentName", current.getAssessmentName());
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
    private List<Assessment> mAssessment; // Cached copy of words

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
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
            holder.assessmentItemView2.setText(Integer.toString(current.getAssessmentID()));
        } else {
            // Covers the case of data not being ready yet.
            holder.assessmentItemView.setText("No Word");
            holder.assessmentItemView2.setText("No Word");
        }

    }

    public void setAssissments(List<Assessment> words) {
        mAssessment = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mAssessment != null)
            return mAssessment.size();
        else return 0;
    }
}

