package com.kiwi.meetapp.gui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiwi.meetapp.R;
import com.kiwi.meetapp.beans.Friend;
import com.kiwi.meetapp.utils.FontFacade;
import com.kiwi.meetapp.utils.FontManager;

import java.util.ArrayList;

/**
 * Created by carlo_000 on 16/11/2015.
 */
public class AdapterFriends extends RecyclerView.Adapter<AdapterFriends.EventViewHolder> {

    private Context context;
    private ArrayList<Friend> friends;

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName;

        EventViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_friends_imageView_profilePicture);
            textViewName = (TextView) itemView.findViewById(R.id.item_friends_textView_name);
        }
    }

    public AdapterFriends(Context context, ArrayList<Friend> friends) {
        this.context = context;
        this.friends = friends;
    }


    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friends, parent, false);
        EventViewHolder pvh = new EventViewHolder(v);

        return pvh;
    }

    @Override
    public void onBindViewHolder(AdapterFriends.EventViewHolder holder, int position) {
        holder.textViewName.setText(friends.get(position).getName());
        if(friends.get(position).getBitmap() != null)
            holder.imageView.setImageBitmap(friends.get(position).getBitmap());

    }

    @Override
    public int getItemCount() {
        return friends.size();
    }
}
