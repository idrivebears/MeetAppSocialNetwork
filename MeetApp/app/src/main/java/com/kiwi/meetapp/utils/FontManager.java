package com.kiwi.meetapp.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Carlos Alexis on 28/11/2015.
 */
public abstract class FontManager {

    public static final String typeNormal = "NORMAL", typeItalic = "ITALIC";

    public Typeface getTypeface(Context context, String type) {
        Typeface typeface;

        typeface = createTypeface(context, type);

        return typeface;

    }

    protected abstract Typeface createTypeface(Context context, String type);

}
