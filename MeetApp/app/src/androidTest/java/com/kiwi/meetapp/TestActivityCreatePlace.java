package com.kiwi.meetapp;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.kiwi.meetapp.gui.ActivityCreatePlace;

/**
 * Created by Juan on 28/11/2015.
 */
public class TestActivityCreatePlace extends ActivityInstrumentationTestCase2<ActivityCreatePlace> {

    Activity activity;
    public TestActivityCreatePlace() {
        super(ActivityCreatePlace.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    @SmallTest
    public void testTextButtonSpinner(){
        //testing TextViews
        TextView textview = (TextView) activity.findViewById(R.id.activity_create_place_textView_category);
        assertNotNull(textview);

        //testing EditTexts
        EditText editText = (EditText) activity.findViewById(R.id.activity_create_place_editText_description);
        assertNotNull(editText);
        editText = (EditText) activity.findViewById(R.id.activity_create_place_editText_name);
        assertNotNull(editText);

        //testing Button
        Button button = (Button) activity.findViewById(R.id.activity_create_place_button_save);
        assertNotNull(button);
        button = (Button) activity.findViewById(R.id.activity_create_place_button_setMap);
        assertNotNull(button);
        //testing Spinner
        Spinner spinner = (Spinner) activity.findViewById(R.id.activity_create_place_spinner_category);
        assertNotNull(spinner);

    }
}
