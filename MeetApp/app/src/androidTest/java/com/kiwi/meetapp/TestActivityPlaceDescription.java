package com.kiwi.meetapp;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kiwi.meetapp.gui.ActivityPlaceDescription;

/**
 * Created by Juan on 28/11/2015.
 */
public class TestActivityPlaceDescription extends ActivityInstrumentationTestCase2<ActivityPlaceDescription> {

    Activity activity;
    public TestActivityPlaceDescription() {
        super(ActivityPlaceDescription.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();

    }

    @SmallTest
    public void testTextButtonSpinner(){
        //testing TextViews
        TextView textview = (TextView) activity.findViewById(R.id.activity_place_description_textView_placeNameText);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.activity_place_description_textView_placeName);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.activity_place_description_textView_category);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.activity_place_description_textView_categoryText);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.activity_place_description_textView_description);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.activity_place_description_textView_descriptionText);
        assertNotNull(textview);

        //testing buttons
        Button button = (Button) activity.findViewById(R.id.activity_place_description_button);
        assertNotNull(button);

        //testing LinearLayout
        LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.activity_place_description_linearLayout);
        assertNotNull(linearLayout);
    }
}
