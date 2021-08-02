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
import com.student.wgu.C196.ftofani.Entities.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteItemView;
        private final TextView noteItemView2;
        private final TextView noteItemView3;

        private NoteViewHolder(View itemView) {
            super(itemView);
            noteItemView = itemView.findViewById(R.id.noteTextView);
            noteItemView2 = itemView.findViewById(R.id.noteTextView2);
            noteItemView3 = itemView.findViewById(R.id.noteTextView3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Note current = mNotes.get(position);
                    Intent intent = new Intent(context, EditNote.class);
                    intent.putExtra("noteID", current.getNoteID());
                    intent.putExtra("noteName", current.getNoteName());
                    intent.putExtra("noteBody", current.getNoteBody());
                    intent.putExtra("noteCourseID", current.getNoteCourseID());
                    context.startActivity(intent);
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<Note> mNotes; // Cached copy of words

    public NoteAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.note_list_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        if (mNotes != null) {
            Note current = mNotes.get(position);
            holder.noteItemView.setText(current.getNoteName());
            holder.noteItemView2.setText(current.getNoteBody());
            holder.noteItemView3.setText(Integer.toString(current.getNoteCourseID()));
        } else {
            holder.noteItemView.setText("No Word");
            holder.noteItemView2.setText("No Word");
            holder.noteItemView3.setText("No Word");
        }
    }

    public void setNotes(List<Note> words) {
        mNotes = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }

}