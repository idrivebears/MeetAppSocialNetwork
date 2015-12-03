package com.kiwi.meetapp.utils;

import android.app.Activity;
import android.graphics.Typeface;
import android.test.ActivityInstrumentationTestCase2;

import com.kiwi.meetapp.gui.ActivityFriendProfile;
import com.kiwi.meetapp.utils.typefaces.BlackTypeface;
import com.kiwi.meetapp.utils.typefaces.BoldTypeface;
import com.kiwi.meetapp.utils.typefaces.HairlineTypeface;
import com.kiwi.meetapp.utils.typefaces.HeavyTypeface;
import com.kiwi.meetapp.utils.typefaces.ItalicTypeface;
import com.kiwi.meetapp.utils.typefaces.LightTypeface;
import com.kiwi.meetapp.utils.typefaces.MediumTypeface;
import com.kiwi.meetapp.utils.typefaces.RegularTypeface;
import com.kiwi.meetapp.utils.typefaces.ThinTypeface;

import org.junit.Test;

/**
 * Created by Alejandro Vargas on 01/12/2015.
 */
public class testTypefaces extends ActivityInstrumentationTestCase2<ActivityFriendProfile> {

    private Activity activity;
    private Typeface typeface;
    private FontManager fontManager;


    public testTypefaces() {
        super(ActivityFriendProfile.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();

    }

    @Test
    public void testBlack(){
        BlackTypeface blackTypeface = new BlackTypeface();
        assertNotNull(blackTypeface);
        typeface = blackTypeface.getTypeface(activity.getApplicationContext(),"NORMAL");
        assertEquals(typeface.getStyle(),0);

        typeface = blackTypeface.getTypeface(activity.getApplicationContext(),"ITALIC");
        assertEquals(typeface.getStyle(),2);
    }
    @Test
    public void testBold(){
        BoldTypeface boldTypeface = new BoldTypeface();
        assertNotNull(boldTypeface);
        typeface = boldTypeface.getTypeface(activity.getApplicationContext(),"NORMAL");
        assertEquals(typeface.getStyle(),1);

        typeface = boldTypeface.getTypeface(activity.getApplicationContext(),"ITALIC");
        assertEquals(typeface.getStyle(),3);
    }
    @Test
    public void testHairline(){
        HairlineTypeface hairlineTypeface = new HairlineTypeface();
        assertNotNull(hairlineTypeface);
        typeface = hairlineTypeface.getTypeface(activity.getApplicationContext(),"NORMAL");
        assertEquals(typeface.getStyle(),0);

        typeface = hairlineTypeface.getTypeface(activity.getApplicationContext(),"ITALIC");
        assertEquals(typeface.getStyle(),2);
    }
    @Test
    public void testHeavy(){
        HeavyTypeface heavyTypeface = new HeavyTypeface();
        assertNotNull(heavyTypeface);
        typeface = heavyTypeface.getTypeface(activity.getApplicationContext(),"NORMAL");
        assertEquals(typeface.getStyle(),0);

        typeface = heavyTypeface.getTypeface(activity.getApplicationContext(),"ITALIC");
        assertEquals(typeface.getStyle(),2);
    }
    @Test
    public void testItalic(){
        ItalicTypeface italicTypeface = new ItalicTypeface();
        assertNotNull(italicTypeface);
        typeface = italicTypeface.getTypeface(activity.getApplicationContext(),"NORMAL");
        assertEquals(typeface.getStyle(),2);

    }
    @Test
    public void testLight(){
        LightTypeface lightTypeface = new LightTypeface();
        assertNotNull(lightTypeface);
        typeface = lightTypeface.getTypeface(activity.getApplicationContext(),"NORMAL");
        assertEquals(typeface.getStyle(),0);

        typeface = lightTypeface.getTypeface(activity.getApplicationContext(),"ITALIC");
        assertEquals(typeface.getStyle(),2);
    }
    @Test
    public void testMedium(){
        MediumTypeface mediumTypeface = new MediumTypeface();
        assertNotNull(mediumTypeface);
        typeface = mediumTypeface.getTypeface(activity.getApplicationContext(),"NORMAL");
        assertEquals(typeface.getStyle(),0);

        typeface = mediumTypeface.getTypeface(activity.getApplicationContext(),"ITALIC");
        assertEquals(typeface.getStyle(),2);
    }
    @Test
    public void testRegular(){
        RegularTypeface regularTypeface = new RegularTypeface();
        assertNotNull(regularTypeface);
        typeface = regularTypeface.getTypeface(activity.getApplicationContext(),"NORMAL");
        assertEquals(typeface.getStyle(),0);
    }
    @Test
    public void testSemibold(){
        BoldTypeface boldTypeface = new BoldTypeface();
        assertNotNull(boldTypeface);
        typeface = boldTypeface.getTypeface(activity.getApplicationContext(),"NORMAL");
        assertEquals(typeface.getStyle(),1);

        typeface = boldTypeface.getTypeface(activity.getApplicationContext(),"ITALIC");
        assertEquals(typeface.getStyle(),3);
    }
    @Test
    public void testThin(){
        ThinTypeface thinTypeface = new ThinTypeface();
        assertNotNull(thinTypeface);
        typeface = thinTypeface.getTypeface(activity.getApplicationContext(),"NORMAL");
        assertEquals(typeface.getStyle(),0);

        typeface = thinTypeface.getTypeface(activity.getApplicationContext(),"ITALIC");
        assertEquals(typeface.getStyle(),2);
    }



}
