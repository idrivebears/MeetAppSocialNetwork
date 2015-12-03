package com.kiwi.meetapp.gui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.kiwi.meetapp.R;
import com.kiwi.meetapp.beans.Place;
import com.kiwi.meetapp.utils.FontFacade;
import com.kiwi.meetapp.utils.FontManager;

/**
 * Created by carlo_000 on 20/11/2015.
 */
public class ActivityPlaceDescription extends AppCompatActivity {

    private TextView textViewName, textViewNameText, textViewDescription, textViewDescriptionText, textViewCategory, textViewCategoryText;
    private Button button;
    private Fragment fragmentMap;
    private LinearLayout linearLayout;
    private Place place;
    private final String PLACE = "Place";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_description);

        textViewName = (TextView) findViewById(R.id.activity_place_description_textView_placeName);
        textViewNameText = (TextView) findViewById(R.id.activity_place_description_textView_placeNameText);
        textViewDescription = (TextView) findViewById(R.id.activity_place_description_textView_description);
        textViewDescriptionText = (TextView) findViewById(R.id.activity_place_description_textView_descriptionText);
        textViewCategory = (TextView) findViewById(R.id.activity_place_description_textView_category);
        textViewCategoryText = (TextView) findViewById(R.id.activity_place_description_textView_categoryText);
        button = (Button) findViewById(R.id.activity_place_description_button);
        linearLayout = (LinearLayout) findViewById(R.id.activity_place_description_linearLayout);

        try {
            FontFacade fontFacade = FontFacade.getInstance();
            fontFacade.setFontToView(this, textViewName, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewNameText, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewDescription, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewDescriptionText, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewCategory, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewCategoryText, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, button, FontFacade.LIGHT, FontManager.typeNormal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(PLACE, place);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        if(getIntent().getExtras() != null && getIntent().hasExtra(PLACE)) {
            place = getIntent().getParcelableExtra(PLACE);
            if(place != null) {
                textViewName.setText(place.getName());
                textViewDescription.setText(place.getDescription());
                switch(place.getCategory()) {
                    case 0:
                        textViewCategory.setText("Social");
                        break;
                    case 1:
                        textViewCategory.setText("Comida");
                        break;
                    case 2:
                        textViewCategory.setText("Entretenimiento");
                        break;
                    case 3:
                        textViewCategory.setText("Naturaleza");
                        break;
                    case 4:
                        textViewCategory.setText("Compras");
                        break;
                    default:
                        textViewCategory.setText("Otro");
                        break;
                }
            }
        }

        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();

        fragmentMap = new FragmentPlaceMap();
        fragTransaction.add(linearLayout.getId(), fragmentMap, "fragment_map");
        fragTransaction.commit();

    }
}
