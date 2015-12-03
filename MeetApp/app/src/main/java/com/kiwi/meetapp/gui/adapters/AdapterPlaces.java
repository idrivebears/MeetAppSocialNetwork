package com.kiwi.meetapp.gui.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiwi.meetapp.R;
import com.kiwi.meetapp.beans.Place;
import com.kiwi.meetapp.utils.FontFacade;
import com.kiwi.meetapp.utils.FontManager;

import java.util.ArrayList;

/**
 * Created by Carlos Alexis on 17/11/2015.
 */
public class AdapterPlaces extends RecyclerView.Adapter<AdapterPlaces.EventViewHolder> {

    private Context context;
    private ArrayList<Place> places;
    private int[] imageResId = {
            R.drawable.nightlife32,
            R.drawable.food32,
            R.drawable.entertainment32,
            R.drawable.nature32,
            R.drawable.shopping32,
            R.drawable.more32
    };

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewPlaceName, textViewPlaceDescription;

        EventViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_place_imageView_placePicture);
            textViewPlaceName = (TextView) itemView.findViewById(R.id.item_place_textView_name);
            textViewPlaceDescription = (TextView) itemView.findViewById(R.id.item_place_textView_description);
        }
    }

    public AdapterPlaces(Context context, ArrayList<Place> places) {
        this.context = context;
        this.places = places;
    }


    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        EventViewHolder pvh = new EventViewHolder(v);

        return pvh;
    }

    @Override
    public void onBindViewHolder(AdapterPlaces.EventViewHolder holder, int position) {
        holder.textViewPlaceName.setText(places.get(position).getName());
        holder.textViewPlaceDescription.setText(places.get(position).getDescription());
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(context, imageResId[places.get(position).getCategory()]));

    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public void setItems(ArrayList<Place> places) {
        this.places = places;
    }

    public Place removeItem(int position) {
        final Place place = places.remove(position);
        notifyItemRemoved(position);
        return place;
    }

    public void addItem(int position, Place place) {
        places.add(position, place);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Place place = places.remove(fromPosition);
        places.add(toPosition, place);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void animateTo(ArrayList<Place> places) {
        applyAndAnimateRemovals(places);
        applyAndAnimateAdditions(places);
        applyAndAnimateMovedItems(places);
    }

    private void applyAndAnimateRemovals(ArrayList<Place> newPlaces) {
        for (int i = places.size() - 1; i >= 0; i--) {
            final Place place = places.get(i);
            if (!newPlaces.contains(place)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(ArrayList<Place> newPlaces) {
        for (int i = 0, count = newPlaces.size(); i < count; i++) {
            final Place place = newPlaces.get(i);
            if (!places.contains(place)) {
                addItem(i, place);
            }
        }
    }

    private void applyAndAnimateMovedItems(ArrayList<Place> newPlaces) {
        for (int toPosition = newPlaces.size() - 1; toPosition >= 0; toPosition--) {
            final Place place = newPlaces.get(toPosition);
            final int fromPosition = places.indexOf(place);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

}
