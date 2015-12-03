package com.kiwi.meetapp;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.SearchView;

import com.kiwi.meetapp.gui.ActivityPlace;

/**
 * Created by Juan on 28/11/2015.
 */
public class TestActivityPlace extends ActivityInstrumentationTestCase2<ActivityPlace> {

    Activity activity;
    public TestActivityPlace() {
        super(ActivityPlace.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();

    }

    @SmallTest
    public void testTextButtonSpinner(){

        //testing FloatingActionButton
        FloatingActionButton button    = (FloatingActionButton) activity.findViewById(R.id.activity_place_floatingActionButton);
        assertNotNull(button);
        //testing RecyclerView
        RecyclerView editText = (RecyclerView) activity.findViewById(R.id.activity_place_recyclerView);
        assertNotNull(editText);
        //testing SearchView
        SearchView swipe = (SearchView) activity.findViewById(R.id.activity_place_searchView);
        assertNotNull(swipe);
        //testing SearchView
        SwipeRefreshLayout refresh = (SwipeRefreshLayout) activity.findViewById(R.id.activity_place_swipeRefreshLayout);
        assertNotNull(refresh);
    }
}
