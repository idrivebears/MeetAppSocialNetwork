package com.kiwi.meetapp.gui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kiwi.meetapp.R;
import com.kiwi.meetapp.beans.Comment;
import com.kiwi.meetapp.utils.FontFacade;
import com.kiwi.meetapp.utils.FontManager;

import java.util.ArrayList;

/**
 * Created by Carlos Alexis on 19/11/2015.
 */
public class AdapterComments extends BaseAdapter {

    private Context context;
    private ArrayList<Comment> comments;
    private LayoutInflater inflater;

    public static class ViewHolder {

        TextView textViewDate, textViewCreator, textViewDescription;

    }

    public AdapterComments(Context context, ArrayList<Comment> comments) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_comment, null);

            holder = new ViewHolder();

            holder.textViewDate = (TextView) convertView.findViewById(R.id.item_comment_date);
            holder.textViewCreator = (TextView) convertView.findViewById(R.id.item_comment_creator);
            holder.textViewDescription = (TextView) convertView.findViewById(R.id.item_comment_description);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewCreator.setText(comments.get(position).getCreatorName());
        holder.textViewDate.setText(comments.get(position).getDate());
        holder.textViewDescription.setText(comments.get(position).getDescription());

        return convertView;
    }

}