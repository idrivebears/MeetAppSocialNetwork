package com.kiwi.meetapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;

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

/**
 * Created by Carlos Alexis on 28/11/2015.
 */
public class FontFacade {

    public static final int BLACK = 0, BOLD = 1, HAIRLINE = 2, HEAVY = 3, ITALIC = 4, LIGHT = 5, MEDIUM = 6, REGULAR = 7, SEMIBOLD = 8, THIN = 9;
    private FontApplySystem fontApplySystem;
    private static FontFacade fontFacade;

    private FontFacade() {
        fontApplySystem = new FontApplySystem();
    }

    public static FontFacade getInstance(){
        if(fontFacade == null) {
            fontFacade = new FontFacade();
        }
        return fontFacade;
    }

    public FontManager setFontToView(Context context, View view, int style, String type) throws Exception{
        FontManager fontManager;
        switch (style) {
            case BLACK:
                fontManager = new BlackTypeface();
                break;
            case BOLD:
                fontManager = new BoldTypeface();
                break;
            case HAIRLINE:
                fontManager = new HairlineTypeface();
                break;
            case HEAVY:
                fontManager = new HeavyTypeface();
                break;
            case ITALIC:
                fontManager = new ItalicTypeface();
                break;
            case LIGHT:
                fontManager = new LightTypeface();
                break;
            case MEDIUM:
                fontManager = new MediumTypeface();
                break;
            case REGULAR:
                fontManager = new RegularTypeface();
                break;
            case SEMIBOLD:
                fontManager = new SemiBoldTypeface();
                break;
            case THIN:
                fontManager = new ThinTypeface();
                break;
            default:
                throw new Exception("Invalid style. Must be BLACK = 0, BOLD = 1, HAIRLINE = 2, HEAVY = 3, ITALIC = 4, LIGHT = 5, MEDIUM = 6, REGULAR = 7, SEMIBOLD = 8, THIN = 9");
        }
        Typeface typeface = fontManager.getTypeface(context, type);
        if(typeface != null)
            fontApplySystem.applyFont(view, typeface);
        return fontManager;
    }

    public static void clearInstance() {
        fontFacade = null;
    }

}
