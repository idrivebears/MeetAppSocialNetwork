package com.kiwi.meetapp;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kiwi.meetapp.gui.ActivityEventsDescription;

/**
 * Created by Juan on 28/11/2015.
 */
public class TestActivityEventsDescription extends ActivityInstrumentationTestCase2<ActivityEventsDescription> {

    Activity activity;
    public TestActivityEventsDescription() {
        super(ActivityEventsDescription.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();

    }

    @SmallTest
    public void testTextButtonSpinner(){
        //testing TextViews
        TextView textview = (TextView) activity.findViewById(R.id.activity_event_description_textView_eventDateTime);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.activity_event_description_textView_eventDescription);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.activity_event_description_textView_creatorName);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.activity_event_description_textView_creator);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.activity_event_description_textView_eventVisibility);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.activity_event_description_textView_eventName);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.activity_event_description_textView_comments);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.activity_event_description_textView_placeName);
        assertNotNull(textview);
        //testing EditTexts
        EditText editText = (EditText) activity.findViewById(R.id.activity_event_description_editText_newComment);
        assertNotNull(editText);

        //testing ListView
        ListView listview = (ListView) activity.findViewById(R.id.activity_event_description_listView_comments);
        assertNotNull(listview);
        //testing ImageView
        ImageView spinner = (ImageView) activity.findViewById(R.id.activity_event_description_imageView_comment);
        assertNotNull(spinner);
        //testing LinearLayout
        LinearLayout linear = (LinearLayout) activity.findViewById(R.id.activity_event_description_linearLayout_comments);
        assertNotNull(linear);
    }
}
