package com.kiwi.meetapp.gui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kiwi.meetapp.R;
import com.kiwi.meetapp.beans.Event;
import com.kiwi.meetapp.utils.FontFacade;
import com.kiwi.meetapp.utils.FontManager;

import java.util.ArrayList;

/**
 * Created by carlo_000 on 16/11/2015.
 */
public class AdapterEvents extends RecyclerView.Adapter<AdapterEvents.EventViewHolder> {

    private static Context context;
    private ArrayList<Event> events;

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewCreatorName, textViewEventName, textViewEventDescription, textViewEventDate, textViewEventPlace;

        EventViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_events_imageView_creatorPicture);
            textViewCreatorName = (TextView) itemView.findViewById(R.id.item_events_textView_creatorName);
            textViewEventName = (TextView) itemView.findViewById(R.id.item_events_textView_eventName);
            textViewEventDescription = (TextView) itemView.findViewById(R.id.item_events_textView_eventDescription);
            textViewEventDate = (TextView) itemView.findViewById(R.id.item_events_textView_eventDate);
            textViewEventPlace = (TextView) itemView.findViewById(R.id.item_events_textView_eventPlace);
        }
    }

    public AdapterEvents(Context context, ArrayList<Event> events) {
        AdapterEvents.context = context;
        this.events = events;
    }


    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_events, parent, false);
        EventViewHolder pvh = new EventViewHolder(v);

        return pvh;
    }

    @Override
    public void onBindViewHolder(AdapterEvents.EventViewHolder holder, int position) {
        holder.textViewCreatorName.setText(events.get(position).getCreatorName());
        holder.textViewEventDate.setText(events.get(position).getDate());
        holder.textViewEventPlace.setText(events.get(position).getPlace());
        holder.textViewEventDescription.setText(events.get(position).getDescription());
        holder.textViewEventName.setText(events.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
