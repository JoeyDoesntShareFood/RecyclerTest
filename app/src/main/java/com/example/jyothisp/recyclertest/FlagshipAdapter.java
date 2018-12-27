package com.example.jyothisp.recyclertest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FlagshipAdapter extends RecyclerView.Adapter<FlagshipAdapter.FlagshipHolder> {

    private ArrayList<Event> eventArrayList;


    public FlagshipAdapter(ArrayList<Event> eventArrayList) {
        this.eventArrayList = eventArrayList;
    }

    public FlagshipAdapter(){}


    public class FlagshipHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;

        public FlagshipHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: open up descriptions.
                }
            });
        }
    }
    @NonNull
    @Override
    public FlagshipHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.flagship_list_item, viewGroup, false);
        return new FlagshipHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlagshipHolder flagshipHolder, int i) {
        Event event = eventArrayList.get(i);
        flagshipHolder.titleTextView.setText(event.getmTitle());
        flagshipHolder.titleTextView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return eventArrayList.size();
    }

}
