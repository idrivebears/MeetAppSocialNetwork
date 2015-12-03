package com.kiwi.meetapp.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.kiwi.meetapp.R;
import com.kiwi.meetapp.utils.EditTextError;
import com.kiwi.meetapp.beans.Place;
import com.kiwi.meetapp.utils.FontFacade;
import com.kiwi.meetapp.utils.FontManager;

/**
 * Created by ALEX on 16/11/2015.
 */
public class ActivityCreatePlace extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editTextName;
    private EditText editTextDescription;
    private TextView textView;
    private Spinner spinner;
    private Button button, buttonMap;
    private final int REQUEST_CODE = 94;
    private LatLng latLng;
    private final String RESULT = "Result", PLACE = "Place";
    private Place place;
    private int category;
    private EditTextError editTextError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_place);

        editTextName = (EditText) findViewById(R.id.activity_create_place_editText_name);
        editTextDescription = (CustomEditText) findViewById(R.id.activity_create_place_editText_description);
        textView = (TextView)findViewById(R.id.activity_create_place_textView_category);
        spinner = (Spinner)findViewById(R.id.activity_create_place_spinner_category);
        button = (Button)findViewById(R.id.activity_create_place_button_save);
        buttonMap = (Button) findViewById(R.id.activity_create_place_button_setMap);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_create_place_linearLayout);

        try {
            FontFacade fontFacade = FontFacade.getInstance();
            fontFacade.setFontToView(this, editTextName, FontFacade.REGULAR, FontManager.typeNormal);
            fontFacade.setFontToView(this, editTextDescription, FontFacade.REGULAR, FontManager.typeNormal);
            fontFacade.setFontToView(this, textView, FontFacade.REGULAR, FontManager.typeNormal);
            fontFacade.setFontToView(this, spinner, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, button, FontFacade.LIGHT, FontManager.typeNormal);
            fontFacade.setFontToView(this, buttonMap, FontFacade.LIGHT, FontManager.typeNormal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        editTextDescription.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    View view = getCurrentFocus();
                    if (view != null) {
                        linearLayout.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextError = new EditTextError();
                if (!(editTextError.checkError(editTextName) || editTextError.checkError(editTextDescription))) {
                    if (place != null) {
                        place.setDescription(editTextDescription.getText().toString());
                        place.setName(editTextName.getText().toString());
                        place.setCategory(category);
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(PLACE, place);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    } else {
                        Toast.makeText(getBaseContext(), "Por favor elige una ubicación.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCreatePlace.this, ActivityMap.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                if(data != null && data.getExtras() != null) {
                    latLng = data.getParcelableExtra(RESULT);
                    if (latLng != null) {
                        place = new Place();
                        place.setLatitude(latLng.latitude);
                        place.setLongitude(latLng.longitude);
                    }
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using parent.getItemAtPosition(pos)
        category = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
