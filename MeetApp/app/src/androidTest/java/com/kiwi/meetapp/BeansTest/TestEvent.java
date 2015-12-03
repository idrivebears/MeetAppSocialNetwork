package com.kiwi.meetapp.BeansTest;

import android.test.AndroidTestCase;

import com.kiwi.meetapp.beans.Event;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Juan on 28/11/2015.
 */
public class TestEvent extends AndroidTestCase {
    private Event event;

    private int image;
    @Before
    public void setUp(){
        event = new Event("juan", "fiesta",  "cumpleaños", "21-22-23",  "private");
    }


    @Test
    public void testCreatorName(){
        String cname = "Juani";
        event.setCreatorName(cname);
        assertEquals(event.getCreatorName(),cname);
    }

    @Test
    public void testName(){
        String sName = "Peda";
        event.setName(sName);
        assertEquals(event.getName(),sName);
    }

    @Test
    public void testDescription(){
        String Sdec = "bici";
        event.setDescription(Sdec);
        assertEquals(event.getDescription(),Sdec);
    }

    @Test
    public void testImage(){
        int Nima = 1;
        event.setImage(Nima);
        assertEquals(event.getImage(),Nima);
    }

    @Test
    public void testVisibility(){
        String SVisi = "public";
        event.setVisibility(SVisi);
        assertEquals(event.getVisibility(),SVisi);
    }

    @Test
    public void testPlace(){
        String SVisi = "barcaca";
        event.setPlace(SVisi);
        assertEquals(event.getPlace(),SVisi);
    }
}
