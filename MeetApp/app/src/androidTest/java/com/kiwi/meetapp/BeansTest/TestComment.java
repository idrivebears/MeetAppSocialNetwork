package com.kiwi.meetapp.BeansTest;

import android.test.AndroidTestCase;

import com.kiwi.meetapp.beans.Comment;

import org.junit.Before;
import org.junit.Test;


/**
 * Created by Juan on 28/11/2015.
 */
public class TestComment extends AndroidTestCase {
    Comment comment;
    @Before
    public void setUp(){
        comment = new Comment("DescriptionTest","Alex","21-12-15");
    }
    @Test
    public void testSetUp(){
        assertEquals(comment.getDescription(),"DescriptionTest");
        assertEquals(comment.getCreatorName(),"Alex");
        assertEquals(comment.getDate(),"21-12-15");
    }
    @Test
    public void testDescription(){
        String description = "NewDesc";
        comment.setDescription(description);
        assertEquals(comment.getDescription(),description);
    }
    @Test
    public void testName(){
        String name  = "Juan";
        comment.setCreatorName(name);
        assertEquals(comment.getCreatorName(),name);
    }
    @Test
    public void testDate(){
        String date = "22-12-15";
        comment.setDate(date);
        assertEquals(comment.getDate(),date);
    }

}
