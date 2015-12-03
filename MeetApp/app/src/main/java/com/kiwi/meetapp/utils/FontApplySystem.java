package com.kiwi.meetapp.utils;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Carlos Alexis on 28/11/2015.
 */
public class FontApplySystem {

    public void applyFont(View view, Typeface typeface) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                applyFont(((ViewGroup) view).getChildAt(i), typeface);
            }
        } else if (view instanceof Button) {
            ((Button) view).setTypeface(typeface);
        } else if (view instanceof TextView) {
            ((TextView) view).setTypeface(typeface);
        }
    }

}
