package com.kiwi.meetapp.BeansTest;

import android.test.AndroidTestCase;

import com.kiwi.meetapp.beans.Friend;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Juan on 28/11/2015.
 */
public class TestsFriend extends AndroidTestCase {
    private int age;
    private String name, gender, location, facebookID;
    //private Bitmap bitmap;
    Friend friend;

    @Before
    public void setUp(){
        name = "Alejandro";
        gender = "male";
        location = "santa";
        age = 21;
        facebookID = "avargaz";
        //bitmap =null;
        friend = new Friend( name,  age,  gender,  location);
    }
    @Test
    public void testName(){
        String Sname = "Camarena";
        friend.setName(Sname);
        assertEquals(friend.getName(),Sname);
    }
    @Test
    public void testGender(){
        String Sdec = "shemale";
        friend.setGender(Sdec);
        assertEquals(friend.getGender(),Sdec);
    }

    @Test
    public void testLocation(){
        String loc = "casa";
        friend.setLocation(loc);
        assertEquals(friend.getLocation(),loc);
    }
    @Test
    public void testAge(){
        int age = 20;
        friend.setAge(age);
        assertEquals(friend.getAge(),age);
    }
    @Test
    public void testFacebookID(){
        String fb ="cam";
        friend.setFacebookID(fb);
        assertEquals(friend.getFacebookID(),fb);
    }

    @Test
    public void testDescribeContents(){
        int ret = 0;
        assertEquals(friend.describeContents(),ret);
    }
}
