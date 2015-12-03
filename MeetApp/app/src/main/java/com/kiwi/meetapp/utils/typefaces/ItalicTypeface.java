package com.kiwi.meetapp.utils.typefaces;

import android.content.Context;
import android.graphics.Typeface;

import com.kiwi.meetapp.utils.FontManager;
import com.kiwi.meetapp.utils.Fonts;

/**
 * Created by Carlos Alexis on 28/11/2015.
 */
public class ItalicTypeface extends FontManager {
    @Override
    protected Typeface createTypeface(Context context, String type) {
        Typeface typeface;
        if(type.equals(FontManager.typeNormal))
            typeface = Typeface.createFromAsset(context.getAssets(), Fonts.ITALIC);
        else
            typeface = null;
        return typeface;
    }
}
