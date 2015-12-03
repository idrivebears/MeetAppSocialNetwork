package com.kiwi.meetapp;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;

import com.kiwi.meetapp.gui.ActivityLogin;

/**
 * Created by Juan on 28/11/2015.
 */
public class TestActivityLogin extends ActivityInstrumentationTestCase2<ActivityLogin> {

    Activity activity;
    public TestActivityLogin() {
        super(ActivityLogin.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();

    }

    @SmallTest
    public void testTextButtonSpinner(){
        //testing TextViews
        Button button = (Button) activity.findViewById(R.id.activity_login_button_login);
        assertNotNull(button);
    }
}
