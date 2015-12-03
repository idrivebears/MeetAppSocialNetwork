package com.kiwi.meetapp.gui;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiwi.meetapp.R;
import com.kiwi.meetapp.beans.Event;
import com.kiwi.meetapp.gui.adapters.AdapterRecentEvents;
import com.kiwi.meetapp.utils.FontFacade;
import com.kiwi.meetapp.utils.FontManager;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Carlos Alexis on 21/11/2015.
 */
public class FragmentProfile extends Fragment {

    private ImageView imageView;
    private CustomListView customListView;
    private EditText editText;
    private TextView textViewName, textViewAge, textViewLocation, textViewGender;
    private ArrayList<Event> recentEvents;
    private final String EVENT_DESCRIPTION = "EVENT_DESCRIPTION", PROFILE_NAME = "Profile_name", PROFILE_ID = "Profile_ID", PROFILE_URI = "Profile_Uri", MY_PREFERENCES = "My_Preferences", ME = "ME";
    private SharedPreferences sharedPreferences;
    private String userID, userURI, userName;
    private AdapterRecentEvents adapterRecentEvents;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static Fragment fragmentProfileUniqueInstance;

    @SuppressLint("ValidFragment")
    private FragmentProfile(){

    }

    public static Fragment getInstance() {
        if(fragmentProfileUniqueInstance == null) {
            fragmentProfileUniqueInstance = new FragmentProfile();
        }
        return fragmentProfileUniqueInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_activity_profile, container, false);

        //Cast to each element from Fragment
        imageView = (ImageView) view.findViewById(R.id.fragment_activity_profile_imageView);
        customListView = (CustomListView) view.findViewById(R.id.fragment_activity_profile_listView);
        editText = (EditText) view.findViewById(R.id.fragment_activity_profile_editText);
        textViewName = (TextView) view.findViewById(R.id.fragment_activity_profile_textView_name);
        textViewAge = (TextView) view.findViewById(R.id.fragment_activity_profile_textView_age);
        textViewLocation = (TextView) view.findViewById(R.id.fragment_activity_profile_textView_location);
        textViewGender = (TextView) view.findViewById(R.id.fragment_activity_profile_textView_gender);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_activity_profile_swipeRefreshLayout);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            FontFacade fontFacade = FontFacade.getInstance();
            fontFacade.setFontToView(getActivity(), editText, FontFacade.THIN, FontManager.typeNormal);
            fontFacade.setFontToView(getActivity(), textViewName, FontFacade.BLACK, FontManager.typeNormal);
            fontFacade.setFontToView(getActivity(), textViewAge, FontFacade.REGULAR, FontManager.typeNormal);
            fontFacade.setFontToView(getActivity(), textViewLocation, FontFacade.MEDIUM, FontManager.typeNormal);
            fontFacade.setFontToView(getActivity(), textViewGender, FontFacade.LIGHT, FontManager.typeNormal);
        } catch (Exception e) {
            e.printStackTrace();
        }


        sharedPreferences = getActivity().getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);

        userName = sharedPreferences.getString(PROFILE_NAME, null);
        userID = sharedPreferences.getString(PROFILE_ID, null);
        userURI = sharedPreferences.getString(PROFILE_URI, null);

        textViewName.setText(userName);
        new ProfileAsyncTask().execute();

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    View view = getActivity().getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });

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

        recentEvents = new ArrayList<>();

        recentEvents.add(new Event("Chópira", "Mi casa", "Peda", "16 nov", "Caiganle a la peda en mi chan!", "Privada"));
        recentEvents.add(new Event("Alexis", "Gym del ITESO", "Sesión de Yoga", "23 nov", "Ohmmmmmmm!", "Pública"));
        recentEvents.add(new Event("Nacho", "Las Wingman de Manuel Acuña", "Cena", "24 nov", "LLeguen puntualmente a las 8", "Privada"));
        recentEvents.add(new Event("Walls", "Bataclan", "Concierto de death metal", "25 nov", "Va a estar divertido, lleguenle todos. Daremos shots de bienvenida", "Pública"));
        recentEvents.add(new Event("Juani8a", "Ravensburg", "Peda", "30 nov", "Caiganle al pedononón en mi depa!", "Privada"));
        recentEvents.add(new Event("Vargas", "La primavera", "Tour de la primavera", "30 nov", "A darle a la bici!", "Pública"));
        recentEvents.add(new Event("Camarena", "El patio de mi casa", "Juego de ajedrez", "01 dic", "Torneo municipal anual de ajedrez, ¡están todos formalmente invitados!", "Privada"));
        recentEvents.add(new Event("Alejandro Velasco Espejo", "EL COSMOS", "Maratón de Cosmos", "02 dic", "", "Pública"));


        adapterRecentEvents = new AdapterRecentEvents(getContext(), recentEvents);
        customListView.setAdapter(adapterRecentEvents);

        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event recentEvent = recentEvents.get(position);
                Intent intent = new Intent(getActivity(), ActivityEventsDescription.class);
                intent.putExtra(EVENT_DESCRIPTION, recentEvent);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
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

    private class ProfileAsyncTask extends AsyncTask<Void, Void, Integer> {

        private String jsonObjectMe, gender, hometown, birthDate;
        private Bitmap bitmap;
        private int age;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonObjectMe = sharedPreferences.getString(ME, null);
        }

        @Override
        protected Integer doInBackground(Void... params) {
            if(jsonObjectMe != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonObjectMe);
                    if(jsonObject.has("gender")) {
                        gender = jsonObject.getString("gender");
                    }
                    if(jsonObject.has("birthday")) {
                        birthDate = jsonObject.getString("birthday");
                    }
                    if(jsonObject.has("age_range")) {
                        try {
                            age = jsonObject.getJSONObject("age_range").getInt("max");
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if(jsonObject.has("hometown")) {
                        try {
                            hometown = jsonObject.getJSONObject("hometown").getString("name");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (jsonObject.has("picture")) {
                        String profilePicUrl = jsonObject.getJSONObject("picture").getJSONObject("data").getString("url");
                        final URL url = new URL(profilePicUrl);
                        bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        if(bitmap != null) {
                            return 1;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if(gender != null) {
                textViewGender.setText(textViewGender.getText() + ((gender.equals("male"))? "Masculino" : "Femenino"));
            } else {
                textViewGender.setVisibility(View.GONE);
            }
            if(age > 0) {
                textViewAge.setText(textViewAge.getText() + String.valueOf(age));
            } else {
                textViewAge.setVisibility(View.GONE);
            }
            if(hometown != null) {
                textViewLocation.setText(textViewLocation.getText() + hometown);
            } else {
                textViewLocation.setVisibility(View.GONE);
            }
            if(integer > 0) {
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setVisibility(View.GONE);
            }
        }
    }

}
