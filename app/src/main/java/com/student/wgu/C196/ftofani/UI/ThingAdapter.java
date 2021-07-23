package com.student.wgu.C196.ftofani.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.student.wgu.C196.ftofani.Entities.Thing;
import com.student.wgu.C196.ftofani.R;

import java.util.List;

public class ThingAdapter extends RecyclerView.Adapter<ThingAdapter.ThingViewHolder> {

    class ThingViewHolder extends RecyclerView.ViewHolder {
        private final TextView thingItemView;
        private final TextView thingItemView2;
        private ThingViewHolder(View itemView) {
            super (itemView);
            thingItemView=itemView.findViewById(R.id.textView);
            thingItemView2=itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition(); //Lesson 3 29:00
                    final Thing current=mThings.get(position);
                    Intent intent = new Intent(context, ListCourse.class);
                    intent.putExtra("id", current.getThingID());
                    intent.putExtra("name", current.getThingName());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Thing> mThings;
    private final Context context;
    private final LayoutInflater mInflater;

    public ThingAdapter(Context context) {
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ThingAdapter.ThingViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.list_item,parent, false);

        return new ThingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ThingAdapter.ThingViewHolder holder, int position) {
        if(mThings != null) {
            Thing current=mThings.get(position);
            holder.thingItemView.setText(current.getThingName());
            holder.thingItemView2.setText(Integer.toString(current.getThingID()));
        }
        else{
            holder.thingItemView.setText("No Thing Name");
            holder.thingItemView2.setText("No Such ID");
        }

    }

    public void setThings(List<Thing> things) {
        mThings=things;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mThings.size();
    }
}
