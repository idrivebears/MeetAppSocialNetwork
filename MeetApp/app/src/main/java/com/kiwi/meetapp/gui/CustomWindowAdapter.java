package com.kiwi.meetapp.gui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.kiwi.meetapp.R;

/**
 * Created by Carlos Alexis on 17/11/2015.
 */
public class CustomWindowAdapter {

    public static GoogleMap.InfoWindowAdapter infoWindowAdapter(final Context context){

        return new GoogleMap.InfoWindowAdapter() {

            private final View view = ((Activity)context).getLayoutInflater().inflate(R.layout.map_custom_snippet, null);

            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                String title = marker.getTitle();
                TextView txtTitle = ((TextView) view.findViewById(R.id.snippet_title));
                txtTitle.setText(title);

                String content = marker.getSnippet();
                TextView txtContent = ((TextView) view.findViewById(R.id.snippet_content));
                txtContent.setText(content);

                return view;
            }
        };
    }

}
