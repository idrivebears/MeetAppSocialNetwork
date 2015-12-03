package com.kiwi.meetapp.gui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiwi.meetapp.R;
import com.kiwi.meetapp.beans.Event;
import com.kiwi.meetapp.utils.FontFacade;
import com.kiwi.meetapp.utils.FontManager;

import java.util.ArrayList;

/**
 * Created by carlo_000 on 16/11/2015.
 */
public class AdapterRecentEvents extends BaseAdapter implements Filterable {

    private Context context;
    private ArrayList<Event> recentEvents, filteredRecentEvents;
    private LayoutInflater inflater;

    public static class ViewHolder {

        ImageView imageView;
        TextView textViewDate, textViewPlace, textViewName;

    }

    public AdapterRecentEvents(Context context, ArrayList<Event> recentEvents) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.filteredRecentEvents = recentEvents;
        this.recentEvents = new ArrayList<>(filteredRecentEvents);
    }

    @Override
    public int getCount() {
        return filteredRecentEvents.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredRecentEvents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_recent_events, null);

            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.item_recent_events_imageView);
            holder.textViewDate = (TextView) convertView.findViewById(R.id.item_recent_events_textView_date);
            holder.textViewName = (TextView) convertView.findViewById(R.id.item_recent_events_textView_eventName);
            holder.textViewPlace = (TextView) convertView.findViewById(R.id.item_recent_events_textView_place);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewName.setText(filteredRecentEvents.get(position).getName());
        holder.textViewDate.setText(filteredRecentEvents.get(position).getDate());
        holder.textViewPlace.setText(filteredRecentEvents.get(position).getPlace());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                filteredRecentEvents = (ArrayList<Event>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Event> filterRecentEvents = new ArrayList<>();

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < recentEvents.size(); i++) {
                    String place = recentEvents.get(i).getPlace();
                    if (place.toLowerCase().startsWith(constraint.toString()))  {
                        filterRecentEvents.add(recentEvents.get(i));
                    }
                }

                results.count = filterRecentEvents.size();
                results.values = filterRecentEvents;

                return results;
            }
        };

        return filter;
    }

}
