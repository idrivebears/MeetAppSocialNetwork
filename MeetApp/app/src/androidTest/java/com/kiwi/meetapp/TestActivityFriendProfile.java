package com.kiwi.meetapp;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kiwi.meetapp.gui.ActivityFriendProfile;

/**
 * Created by Juan on 28/11/2015.
 */
public class TestActivityFriendProfile extends ActivityInstrumentationTestCase2<ActivityFriendProfile> {

    Activity activity;
    public TestActivityFriendProfile() {
        super(ActivityFriendProfile.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();

    }

    @SmallTest
    public void testTextButtonSpinner(){
        //testing TextViews
        TextView textview = (TextView) activity.findViewById(R.id.fragment_activity_profile_textView_age);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.fragment_activity_profile_textView_gender);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.fragment_activity_profile_textView_location);
        assertNotNull(textview);
        textview = (TextView) activity.findViewById(R.id.fragment_activity_profile_textView_name);
        assertNotNull(textview);

        //testing ListView
        ListView listview = (ListView) activity.findViewById(R.id.fragment_activity_profile_listView);
        assertNotNull(listview);
        //testing ImageView
        ImageView imageView = (ImageView) activity.findViewById(R.id.fragment_activity_profile_imageView);
        assertNotNull(imageView);
        //testing EditText
        EditText editText = (EditText) activity.findViewById(R.id.fragment_activity_profile_editText);
        assertNotNull(editText);
        //testing SwipeRefresh
        SwipeRefreshLayout swipe = (SwipeRefreshLayout) activity.findViewById(R.id.fragment_activity_profile_swipeRefreshLayout);
        assertNotNull(swipe);
    }
}
