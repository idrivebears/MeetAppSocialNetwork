package com.kiwi.meetapp.BeansTest;

import android.test.AndroidTestCase;

import com.kiwi.meetapp.beans.Place;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Juan on 28/11/2015.
 */
public class TestPlace extends AndroidTestCase {
    private String name, description;
    private double latitude, longitude;
    private int imageID, category;
    Place place;

    @Before
    public void setUp(){
        name = "la santa";
        description = "peda por mi cumple";
        latitude = 25;
        longitude = 26;
        imageID = 10;
        category = 11;

        place = new Place(name,  description,  latitude,  longitude,  category);
    }
    @Test
    public void testName(){
        String Sname = "Peda";
        place.setName(Sname);
        assertEquals(place.getName(),Sname);
    }
    @Test
    public void testDescription(){
        String Sdec = "por mi cumple";
        place.setDescription(Sdec);
        assertEquals(place.getDescription(),Sdec);
    }

    @Test
    public void testLatitude(){
        double lat = 20;
        place.setLatitude(lat);
        assertEquals(place.getLatitude(),lat);
    }
    @Test
    public void testLongitude(){
        double longi = 20;
        place.setLongitude(longi);
        assertEquals(place.getLongitude(),longi);
    }

    @Test
    public void testCategory(){
        int cat = 25;
        place.setCategory(cat);
        assertEquals(place.getCategory(),cat);
    }

    @Test
    public void testImage(){
        int id = 2;
        place.setImageID(id);
        assertEquals(place.getImageID(),id);
    }

    @Test
    public void testDescribeContents(){
        int ret = 0;
        assertEquals(place.describeContents(),ret);
    }
}
