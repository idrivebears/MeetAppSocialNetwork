package com.kiwi.meetapp.utils;

import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.kiwi.meetapp.R;
import com.kiwi.meetapp.gui.ActivityCreateEvent;
import com.kiwi.meetapp.utils.typefaces.BlackTypeface;
import com.kiwi.meetapp.utils.typefaces.BoldTypeface;
import com.kiwi.meetapp.utils.typefaces.HairlineTypeface;
import com.kiwi.meetapp.utils.typefaces.HeavyTypeface;
import com.kiwi.meetapp.utils.typefaces.ItalicTypeface;
import com.kiwi.meetapp.utils.typefaces.LightTypeface;
import com.kiwi.meetapp.utils.typefaces.MediumTypeface;
import com.kiwi.meetapp.utils.typefaces.RegularTypeface;
import com.kiwi.meetapp.utils.typefaces.SemiBoldTypeface;
import com.kiwi.meetapp.utils.typefaces.ThinTypeface;

import org.junit.Test;

import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Alejandro Vargas on 01/12/2015.
 */
public class TestFontFacade extends ActivityInstrumentationTestCase2<ActivityCreateEvent> {
    private FontFacade facade;
    Activity activity;

    int BLACK = 0, BOLD = 1, HAIRLINE = 2, HEAVY = 3, ITALIC = 4, LIGHT = 5, MEDIUM = 6, REGULAR = 7, SEMIBOLD = 8, THIN = 9;
    public TestFontFacade() {
        super(ActivityCreateEvent.class);
    }
    public class Font implements Callable<FontFacade> {
        public FontFacade call() throws Exception{
            return FontFacade.getInstance();
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        facade = FontFacade.getInstance();
        //facade=null;

    }

    @Test
    public void testNotNull(){
        assertNotNull(facade);
    }

    @Test
    public void testSetFontViewBlack() throws Exception {

        activity.runOnUiThread(new Runnable() {
            public void run() {

                FontManager fontManager = null;

                Context context = activity.getApplicationContext();
                TextView view = (TextView) activity.findViewById(R.id.activity_event_description_textView_eventDateTime);
                try {
                    fontManager = facade.setFontToView(context, view, BLACK, BlackTypeface.typeNormal);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                assertEquals(fontManager.getClass(),BlackTypeface.class);
            }
        });
    }
    @Test
    public void testSetFontViewBold() throws Exception {

        activity.runOnUiThread(new Runnable() {
            public void run() {
                FontManager fontManager = null;

                Context context = activity.getApplicationContext();
                TextView view = (TextView) activity.findViewById(R.id.activity_event_description_textView_eventDateTime);

                try {
                    fontManager = facade.setFontToView(context, view, BOLD, BlackTypeface.typeNormal);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                assertEquals(fontManager.getClass(), BoldTypeface.class);
            }
        });
    }
    @Test
    public void testSetFontViewHair() throws Exception {

        activity.runOnUiThread(new Runnable() {
            public void run() {
                FontManager fontManager = null;

                Context context = activity.getApplicationContext();
                TextView view = (TextView) activity.findViewById(R.id.activity_event_description_textView_eventDateTime);

                try {
                    fontManager = facade.setFontToView(context,view , HAIRLINE, BlackTypeface.typeNormal);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                assertEquals(fontManager.getClass(),HairlineTypeface.class);
            }
        });
    }
    @Test
    public void testSetFontViewHeavy() throws Exception {

        activity.runOnUiThread(new Runnable() {
            public void run() {
                FontManager fontManager = null;

                Context context = activity.getApplicationContext();
                TextView view = (TextView) activity.findViewById(R.id.activity_event_description_textView_eventDateTime);

                try {
                    fontManager = facade.setFontToView(context, view, HEAVY, BlackTypeface.typeNormal);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                assertEquals(fontManager.getClass(),HeavyTypeface.class);
            }
        });
    }
    @Test
    public void testSetFontViewItalic() throws Exception {

        activity.runOnUiThread(new Runnable() {
            public void run() {
                FontManager fontManager = null;

                Context context = activity.getApplicationContext();
                TextView view = (TextView) activity.findViewById(R.id.activity_event_description_textView_eventDateTime);

                try {
                    fontManager = facade.setFontToView(context,view , ITALIC, BlackTypeface.typeNormal);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                assertEquals(fontManager.getClass(),ItalicTypeface.class);
            }
        });
    }
    @Test
    public void testSetFontViewLight() throws Exception {

        activity.runOnUiThread(new Runnable() {
            public void run() {
                FontManager fontManager = null;

                Context context = activity.getApplicationContext();
                TextView view = (TextView) activity.findViewById(R.id.activity_event_description_textView_eventDateTime);

                try {
                    fontManager = facade.setFontToView(context,view , LIGHT, BlackTypeface.typeNormal);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                assertEquals(fontManager.getClass(),LightTypeface.class);
            }
        });
    }@Test
    public void testSetFontViewMedium() throws Exception {

        activity.runOnUiThread(new Runnable() {
            public void run() {
                FontManager fontManager = null;

                Context context = activity.getApplicationContext();
                TextView view = (TextView) activity.findViewById(R.id.activity_event_description_textView_eventDateTime);

                try {
                    fontManager = facade.setFontToView(context,view , MEDIUM, BlackTypeface.typeNormal);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                assertEquals(fontManager.getClass(),MediumTypeface.class);
            }
        });
    }
    @Test
    public void testSetFontViewRegular() throws Exception {

        activity.runOnUiThread(new Runnable() {
            public void run() {
                FontManager fontManager = null;

                Context context = activity.getApplicationContext();
                TextView view = (TextView) activity.findViewById(R.id.activity_event_description_textView_eventDateTime);

                try {
                    fontManager = facade.setFontToView(context, view, REGULAR, BlackTypeface.typeNormal);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                assertEquals(fontManager.getClass(),RegularTypeface.class);
            }
        });
    }
    @Test
    public void testSetFontViewSemi() throws Exception {

        activity.runOnUiThread(new Runnable() {
            public void run() {
                FontManager fontManager = null;

                Context context = activity.getApplicationContext();
                TextView view = (TextView) activity.findViewById(R.id.activity_event_description_textView_eventDateTime);

                try {
                    fontManager = facade.setFontToView(context, view, SEMIBOLD, BlackTypeface.typeNormal);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                assertEquals(fontManager.getClass(), SemiBoldTypeface.class);
            }
        });
    }
    @Test
    public void testSetFontViewThin() throws Exception {

        activity.runOnUiThread(new Runnable() {
            public void run() {
                FontManager fontManager = null;

                Context context = activity.getApplicationContext();
                TextView view = (TextView) activity.findViewById(R.id.activity_event_description_textView_eventDateTime);

                try {
                    fontManager = facade.setFontToView(context, view, THIN, BlackTypeface.typeNormal);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                assertEquals(fontManager.getClass(), ThinTypeface.class);
            }
        });
    }

    @Test
    public void multiThreadTest() throws Exception{
        int TotalThreads = 20;
        final ExecutorService ex = Executors.newCachedThreadPool();
        HashSet<FontFacade> fonts = new HashSet<FontFacade>();
        HashSet<Callable<FontFacade>> fontCall = new HashSet<Callable<FontFacade>>();

        for(int i = 0; i < TotalThreads; i++ ) ex.submit(new Font());

        ex.invokeAll(fontCall);
        //ex.awaitTermination();
        for(Callable<FontFacade> call : fontCall) fonts.add(call.call());
        for(FontFacade fontFacade : fonts) assertEquals(FontFacade.getInstance().hashCode(),fontFacade.hashCode());
    }
}
