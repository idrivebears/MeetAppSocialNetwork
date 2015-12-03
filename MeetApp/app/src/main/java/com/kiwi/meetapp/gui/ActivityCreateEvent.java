package com.kiwi.meetapp.gui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.kiwi.meetapp.R;
import com.kiwi.meetapp.utils.EditTextError;
import com.kiwi.meetapp.beans.Event;
import com.kiwi.meetapp.beans.Place;
import com.kiwi.meetapp.utils.FontFacade;
import com.kiwi.meetapp.utils.FontManager;

import java.util.Calendar;

/**
 * Created by carlo_000 on 16/11/2015.
 */
public class ActivityCreateEvent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editTextName;
    private EditText editTextDescription;
    private EditText editTextDate;
    private EditText editTextTime;
    private TextView textViewVisiblity;
    private Spinner spinnerVisibility;
    private Button buttonPlace;
    private Event event;
    private Place place;
    private SharedPreferences sharedPreferences;
    private  final String MY_PREFERENCES = "My_Preferences", PROFILE_NAME = "Profile_name", EVENT = "Event", PLACE = "Place";
    private String userName, visibility;
    private Calendar calendar;
    private final int REQUEST_CODE = 541;
    private EditTextError editTextError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        editTextName = (EditText)findViewById(R.id.activity_create_event_editText_name);
        editTextDescription = (CustomEditText)findViewById(R.id.activity_create_event_editText_description);
        editTextDate = (EditText)findViewById(R.id.activity_create_event_editText_date);
        editTextTime = (EditText)findViewById(R.id.activity_create_event_editText_time);
        textViewVisiblity = (TextView)findViewById(R.id.activity_create_event_textView_visibility);
        spinnerVisibility = (Spinner)findViewById(R.id.activity_create_event_spinner_visibility);
        buttonPlace = (Button)findViewById(R.id.activity_create_event_button_place);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_create_event_linearLayout);

        try {
            FontFacade fontFacade = FontFacade.getInstance();
            fontFacade.setFontToView(this, editTextName, FontFacade.REGULAR, FontManager.typeNormal);
            fontFacade.setFontToView(this, editTextDescription, FontFacade.REGULAR, FontManager.typeNormal);
            fontFacade.setFontToView(this, editTextDate, FontFacade.REGULAR, FontManager.typeNormal);
            fontFacade.setFontToView(this, editTextTime, FontFacade.REGULAR, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewVisiblity, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, spinnerVisibility, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, buttonPlace, FontFacade.LIGHT, FontManager.typeNormal);
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

        sharedPreferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        userName = sharedPreferences.getString(PROFILE_NAME, null);
        calendar = Calendar.getInstance();

        buttonPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextError = new EditTextError();
                if (!(editTextError.checkError(editTextName) || editTextError.checkError(editTextDescription) || editTextError.checkError(editTextDate) || editTextError.checkError(editTextTime))) {
                    event = new Event(userName, editTextName.getText().toString(), editTextDescription.getText().toString(), editTextDate.getText().toString() + " " + editTextTime.getText().toString(), visibility);
                    Intent intent = new Intent(getBaseContext(), ActivityPlace.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        });

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new DatePickerDialog(ActivityCreateEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1) + "/" + String.valueOf(year);
                        editTextDate.setText(date);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                alertDialog.show();
            }
        });

        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new TimePickerDialog(ActivityCreateEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = String.valueOf((hourOfDay < 10)? "0" + hourOfDay : hourOfDay) + ":" + String.valueOf((minute < 10)? "0" + minute : minute);
                        editTextTime.setText(time);
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                alertDialog.show();
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.visibility, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerVisibility.setAdapter(adapter);

        spinnerVisibility.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using parent.getItemAtPosition(pos)
        visibility = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE) {
            if(data != null && data.getExtras() != null && data.hasExtra(PLACE)) {
                place = data.getParcelableExtra(PLACE);
                Intent returnIntent = new Intent();
                returnIntent.putExtra(EVENT, event);
                returnIntent.putExtra(PLACE, place);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }
    }
}
