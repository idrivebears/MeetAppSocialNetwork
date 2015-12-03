package com.kiwi.meetapp;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.kiwi.meetapp.gui.ActivityCreateEvent;

/**
 * Created by Juan on 28/11/2015.
 */
public class TestActivityCreateEvent extends ActivityInstrumentationTestCase2<ActivityCreateEvent> {

    Activity activity;

    public TestActivityCreateEvent() {
        super(ActivityCreateEvent.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();

    }

    @SmallTest
    public void testTextViewButton(){
        //testing TextViews
        TextView textview = (TextView) activity.findViewById(R.id.activity_create_event_textView_visibility);
        assertNotNull(textview);
        //testing Button
        Button button = (Button) activity.findViewById(R.id.activity_create_event_button_place);
        assertNotNull(button);



    }
    @SmallTest
    public void testSpinner(){
        Spinner spinner = (Spinner) activity.findViewById(R.id.activity_create_event_spinner_visibility);
        assertNotNull(spinner);
    }
    @SmallTest
    public void testEditText(){
        //testing EditTexts
        EditText editText = (EditText) activity.findViewById(R.id.activity_create_event_editText_description);
        assertNotNull(editText);
        editText = (EditText) activity.findViewById(R.id.activity_create_event_editText_date);
        assertNotNull(editText);
        editText = (EditText) activity.findViewById(R.id.activity_create_event_editText_name);
        assertNotNull(editText);
        editText = (EditText) activity.findViewById(R.id.activity_create_event_editText_time);
        assertNotNull(editText);
    }

}
