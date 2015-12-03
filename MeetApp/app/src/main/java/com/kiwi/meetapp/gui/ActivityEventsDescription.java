package com.kiwi.meetapp.gui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.kiwi.meetapp.R;
import com.kiwi.meetapp.beans.Comment;
import com.kiwi.meetapp.beans.Event;
import com.kiwi.meetapp.gui.adapters.AdapterComments;
import com.kiwi.meetapp.utils.FontFacade;
import com.kiwi.meetapp.utils.FontManager;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by carlo_000 on 16/11/2015.
 */
public class ActivityEventsDescription extends AppCompatActivity {

    private TextView textViewEventName, textViewEventDescription, textViewAssistance, textViewAssistanceAnswer;
    private TextView textViewEventVisibility, textViewEventDateTime, textViewPlaceName, textViewCreator,  textViewCreatorName, textViewComment;
    private ListView listView;
    private EditText editTextNewComment;
    private ImageView imageViewComment;
    private final String EVENT_DESCRIPTION = "EVENT_DESCRIPTION";
    private Event event;
    private ArrayList<Comment> comments;
    private SharedPreferences sharedPreferences;
    private static final String PROFILE_NAME = "Profile_name", MY_PREFERENCES = "My_Preferences";
    private String userName;
    private AdapterComments adapterComments;
    private ToggleButton toggleButton;
    private boolean assistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);
        textViewEventName=(TextView)findViewById(R.id.activity_event_description_textView_eventName);
        textViewEventDescription=(TextView)findViewById(R.id.activity_event_description_textView_eventDescription);
        textViewEventVisibility=(TextView)findViewById(R.id.activity_event_description_textView_eventVisibility);
        textViewEventDateTime=(TextView)findViewById(R.id.activity_event_description_textView_eventDateTime);
        textViewPlaceName=(TextView)findViewById(R.id.activity_event_description_textView_placeName);
        textViewCreator=(TextView)findViewById(R.id.activity_event_description_textView_creator);
        textViewCreatorName=(TextView)findViewById(R.id.activity_event_description_textView_creatorName);
        listView = (ListView) findViewById(R.id.activity_event_description_listView_comments);
        editTextNewComment=(EditText)findViewById(R.id.activity_event_description_editText_newComment);
        imageViewComment=(ImageView)findViewById(R.id.activity_event_description_imageView_comment);
        textViewAssistance = (TextView) findViewById(R.id.activity_event_description_textView_assistance);
        textViewAssistanceAnswer = (TextView) findViewById(R.id.activity_event_description_textView_assistanceAnswer);
        toggleButton = (ToggleButton) findViewById(R.id.activity_event_description_toggleButton);
        textViewComment = (TextView) findViewById(R.id.activity_event_description_textView_comments);

        try {
            FontFacade fontFacade = FontFacade.getInstance();
            fontFacade.setFontToView(this, textViewEventName, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewEventDescription, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewEventVisibility, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewEventDateTime, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewPlaceName, FontFacade.REGULAR, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewCreator, FontFacade.SEMIBOLD, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewCreatorName, FontFacade.SEMIBOLD, FontManager.typeNormal);
            fontFacade.setFontToView(this, editTextNewComment, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewAssistance, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewAssistanceAnswer, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewComment, FontFacade.SEMIBOLD, FontManager.typeNormal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sharedPreferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        userName = sharedPreferences.getString(PROFILE_NAME, null);

        imageViewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextNewComment.getText().toString().trim().length() > 0 ) {
                    Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int month = calendar.get(Calendar.MONTH);
                    int year = calendar.get(Calendar.YEAR);
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int min = calendar.get(Calendar.MINUTE);
                    String dateTime = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year) + " " + String.valueOf((hour < 10)? "0" + hour : hour) + ":" + String.valueOf((min < 10)? "0" + min : min);
                    comments.add(new Comment(editTextNewComment.getText().toString(), userName, dateTime));
                    adapterComments.notifyDataSetChanged();
                    editTextNewComment.setText("");
                }
            }
        });

        if(getIntent().getExtras() != null && getIntent().hasExtra(EVENT_DESCRIPTION)) {
            event = getIntent().getParcelableExtra(EVENT_DESCRIPTION);
        }

        if(event != null) {
            textViewEventName.setText(String.format("%s%s", textViewEventName.getText(), event.getName()));
            textViewEventDescription.setText(String.format("%s%s", textViewEventDescription.getText(), event.getDescription()));
            textViewEventVisibility.setText(String.format("%s%s", textViewEventVisibility.getText(), event.getVisibility()));
            textViewEventDateTime.setText(String.format("%s%s", textViewEventDateTime.getText(), event.getDate()));
            textViewPlaceName.setText(String.format("%s%s", textViewPlaceName.getText(), event.getPlace()));
            textViewCreatorName.setText(event.getCreatorName());
        }

        comments = new ArrayList<>();
        adapterComments = new AdapterComments(this, comments);
        listView.setAdapter(adapterComments);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    textViewAssistanceAnswer.setText("Si");
                } else {
                    textViewAssistanceAnswer.setText("No");
                }
                assistance = isChecked;
            }
        });

    }
}
