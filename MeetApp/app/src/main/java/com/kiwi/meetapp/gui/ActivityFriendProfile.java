package com.kiwi.meetapp.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiwi.meetapp.R;
import com.kiwi.meetapp.beans.Event;
import com.kiwi.meetapp.beans.Friend;
import com.kiwi.meetapp.gui.adapters.AdapterRecentEvents;
import com.kiwi.meetapp.utils.FontFacade;
import com.kiwi.meetapp.utils.FontManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Carlos Alexis on 19/11/2015.
 */
public class ActivityFriendProfile extends AppCompatActivity {

    private ImageView imageView;
    private CustomListView customListView;
    private TextView textViewName, textViewAge, textViewLocation, textViewGender;
    private EditText editText;
    private ArrayList<Event> recentEvents;
    private final String EVENT_DESCRIPTION = "EVENT_DESCRIPTION", FRIEND = "FRIEND";
    private Friend friend;
    private AdapterRecentEvents adapterRecentEvents;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_profile);

        imageView = (ImageView) findViewById(R.id.fragment_activity_profile_imageView);
        customListView = (CustomListView) findViewById(R.id.fragment_activity_profile_listView);
        editText = (EditText) findViewById(R.id.fragment_activity_profile_editText);
        textViewName = (TextView) findViewById(R.id.fragment_activity_profile_textView_name);
        textViewAge = (TextView) findViewById(R.id.fragment_activity_profile_textView_age);
        textViewLocation = (TextView) findViewById(R.id.fragment_activity_profile_textView_location);
        textViewGender = (TextView) findViewById(R.id.fragment_activity_profile_textView_gender);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.fragment_activity_profile_swipeRefreshLayout);

        try {
            FontFacade fontFacade = FontFacade.getInstance();
            fontFacade.setFontToView(this, editText, FontFacade.THIN, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewName, FontFacade.BLACK, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewAge, FontFacade.REGULAR, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewLocation, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(this, textViewGender, FontFacade.LIGHT, FontManager.typeNormal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(getIntent().getExtras() != null && getIntent().hasExtra(FRIEND)) {
            friend = getIntent().getParcelableExtra(FRIEND);
            if(friend != null) {
                textViewName.setText(friend.getName());
                if(friend.getAge() > 0) {
                    textViewAge.setText(textViewAge.getText() + String.valueOf(friend.getAge()));
                }else {
                    textViewAge.setVisibility(View.GONE);
                }
                if(friend.getGender() != null) {
                    textViewGender.setText(textViewGender.getText() + friend.getGender());
                }else {
                    textViewGender.setVisibility(View.GONE);
                }
                if(friend.getLocation() != null) {
                    textViewLocation.setText(textViewLocation.getText() + friend.getLocation());
                }else {
                    textViewLocation.setVisibility(View.GONE);
                }
                if(friend.getBitmap() != null) {
                    imageView.setImageBitmap(friend.getBitmap());
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
        }

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    View view = getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });

        recentEvents = new ArrayList<>();

        recentEvents.add(new Event("Chópira", "Mi casa", "Peda", "16 nov", "Caiganle a la peda en mi chan!", "Privada"));
        recentEvents.add(new Event("Alexis", "Gym del ITESO", "Sesión de Yoga", "23 nov", "Ohmmmmmmm!", "Pública"));
        recentEvents.add(new Event("Nacho", "Las Wingman de Manuel Acuña", "Cena", "24 nov", "LLeguen puntualmente a las 8", "Privada"));
        recentEvents.add(new Event("Walls", "Bataclan", "Concierto de death metal", "25 nov", "Va a estar divertido, lleguenle todos. Daremos shots de bienvenida", "Pública"));
        recentEvents.add(new Event("Juani8a", "Ravensburg", "Peda", "30 nov", "Caiganle al pedononón en mi depa!", "Privada"));
        recentEvents.add(new Event("Vargas", "La primavera", "Tour de la primavera", "01 dic", "A darle a la bici!", "Pública"));
        recentEvents.add(new Event("Camarena", "El patio de mi casa", "Juego de ajedrez", "01 dic", "Torneo municipal anual de ajedrez, ¡están todos formalmente invitados!", "Privada"));
        recentEvents.add(new Event("Alejandro Velasco Espejo", "EL COSMOS", "Maratón de Cosmos", "02 dic", "", "Pública"));


        adapterRecentEvents = new AdapterRecentEvents(this, recentEvents);
        customListView.setAdapter(adapterRecentEvents);

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapterRecentEvents.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                      Event recentEvent = recentEvents.get(position);
                                                      Intent intent = new Intent(getBaseContext(), ActivityEventsDescription.class);
                                                      intent.putExtra(EVENT_DESCRIPTION, recentEvent);
                                                      startActivity(intent);
                                                  }
                                              }

        );

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }, 1000);

            }
        });

    }
}
