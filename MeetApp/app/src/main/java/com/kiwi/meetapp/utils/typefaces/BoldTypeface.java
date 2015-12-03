package com.kiwi.meetapp.utils.typefaces;

import android.content.Context;
import android.graphics.Typeface;

import com.kiwi.meetapp.utils.FontManager;
import com.kiwi.meetapp.utils.Fonts;

/**
 * Created by Carlos Alexis on 28/11/2015.
 */
public class BoldTypeface extends FontManager {
    @Override
    protected Typeface createTypeface(Context context, String type) {
        Typeface typeface;
        if(type.equals(FontManager.typeNormal))
            typeface = Typeface.createFromAsset(context.getAssets(), Fonts.BOLD);
        else if(type.equals(FontManager.typeItalic))
            typeface = Typeface.createFromAsset(context.getAssets(), Fonts.BOLD_ITALIC);
        else
            typeface = null;
        return typeface;
    }
}
