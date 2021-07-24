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
import com.student.wgu.C196.ftofani.Entities.Mentor;

public class MentorAdapter extends RecyclerView.Adapter<MentorAdapter.MentorViewHolder> {

    class MentorViewHolder extends RecyclerView.ViewHolder {
        private final TextView mentorItemView;
        private final TextView mentorItemView2;


        private MentorViewHolder(View itemView) {
            super(itemView);
            mentorItemView = itemView.findViewById(R.id.courseTextView);
            mentorItemView2 = itemView.findViewById(R.id.courseTextView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Mentor current = mMentors.get(position);
                    Intent intent = new Intent(context, EditMentor.class);
                    intent.putExtra("mentorName", current.getMentorName());
                 //   intent.putExtra("coursePrice", Double.toString(current.getPartPrice()));
                    intent.putExtra("position",position);
                    intent.putExtra("mentorID",current.getMentorID());
                 //   intent.putExtra("productID", current.getProductID());
                    context.startActivity(intent);
                }
            });
        }

    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<Mentor> mMentors; // Cached copy of words

    public MentorAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public MentorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.mentor_list_item, parent, false);

        return new MentorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MentorViewHolder holder, int position) {
        if (mMentors != null) {
            Mentor current = mMentors.get(position);
            holder.mentorItemView.setText(current.getMentorName());
            holder.mentorItemView2.setText(Integer.toString(current.getMentorID()));
        } else {
            // Covers the case of data not being ready yet.
            holder.mentorItemView.setText("No Word");
            holder.mentorItemView2.setText("No Word");
        }

    }

    public void setParts(List<Mentor> words) {
        mMentors = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mMentors != null)
            return mMentors.size();
        else return 0;
    }
}
