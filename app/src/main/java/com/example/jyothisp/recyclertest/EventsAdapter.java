package com.example.jyothisp.recyclertest;


import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsHolder> {

    int lastPosition = -1;
    private ArrayList<Event> eventArrayList;


    public class EventsHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView, messageTextVeiw;

        public EventsHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            messageTextVeiw = itemView.findViewById(R.id.message_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: Open up the description of the event.
                    int pos = (int) view.findViewById(R.id.title_text_view).getTag();
                    Toast.makeText(view.getContext(), eventArrayList.get(pos).getmMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     *
     * @param eventArrayList List of events
     */
    public EventsAdapter(ArrayList<Event> eventArrayList) {
        this.eventArrayList = eventArrayList;
    }


    /**
     * Constructor called for the events list.
     */
    public EventsAdapter() {
    }


    public void setData(ArrayList<Event> data) {
        if (eventArrayList != data)
            eventArrayList = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public EventsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_list_tem, viewGroup, false);
        CardView cardView = view.findViewById(R.id.card_view);
        cardView.setPreventCornerOverlap(false); //TODO: Check if this works with all versions.
        EventsHolder holder = new EventsHolder(view);
        return holder;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull EventsHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(@NonNull EventsHolder viewHolder, int i) {
        Event event = eventArrayList.get(i);
        viewHolder.titleTextView.setText(event.getmTitle());
        viewHolder.messageTextVeiw.setText(event.getmMessage());
        viewHolder.titleTextView.setTag(i);
        Animation animation = AnimationUtils.loadAnimation(viewHolder.titleTextView.getContext(),
                (i > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        viewHolder.itemView.startAnimation(animation);
        lastPosition = i;
    }

    @Override
    public int getItemCount() {
        return eventArrayList.size();
    }
}
