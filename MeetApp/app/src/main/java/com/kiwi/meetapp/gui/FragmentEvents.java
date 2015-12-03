package com.kiwi.meetapp.gui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiwi.meetapp.R;
import com.kiwi.meetapp.beans.Event;
import com.kiwi.meetapp.beans.Place;
import com.kiwi.meetapp.gui.adapters.AdapterEvents;
import com.kiwi.meetapp.utils.JSonClient;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Carlos Alexis on 21/11/2015.
 */
public class FragmentEvents extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private AdapterEvents adapterEvents;
    private ArrayList<Event> events;
    private final int REQUEST_CODE_CREATE_EVENT = 87;
    private final String EVENT_DESCRIPTION = "EVENT_DESCRIPTION", EVENT = "Event", PLACE = "Place";
    private SwipeRefreshLayout swipeRefreshLayout;
    private static Fragment fragmentEventsUniqueInstance;

    @SuppressLint("ValidFragment")
    private FragmentEvents(){

    }

    public static Fragment getInstance() {
        if(fragmentEventsUniqueInstance == null) {
            fragmentEventsUniqueInstance = new FragmentEvents();
        }
        return fragmentEventsUniqueInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_activity_events, container, false);

        //Cast to each element from Fragment
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_activity_event_recyclerView);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fragment_activity_event_floatingActionButton);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_activity_event_swipeRefreshLayout);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateEvent.class);
                startActivityForResult(intent, REQUEST_CODE_CREATE_EVENT);
            }
        });
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getActivity().getResources().getColor(R.color.colorPrimary)));

        events = new ArrayList<>();
        events.add(new Event("Raymundo", "Intership", "Tacit Knowledge", "02/12/2015", "Vengan a los interships de Tacit", "Privada"));
        events.add(new Event("Chópira", "Peda", "Mi casa", "16/11/2015", "Caiganle a la peda en mi chan!", "Privada"));
        events.add(new Event("Alexis", "Sesión de Yoga", "Gym del ITESO", "23/11/2015", "Ohmmmmmmm!", "Pública"));
        events.add(new Event("Nacho", "Cena", "Las Wingman de Manuel Acuña", "24/11/2015", "LLeguen puntualmente a las 8", "Privada"));
        events.add(new Event("Walls", "Concierto de death metal", "Bataclan", "25/11/2015", "Va a estar divertido, lleguenle todos. Daremos shots de bienvenida", "Pública"));
        events.add(new Event("Juani8a", "Peda", "Ravensburg", "30/11/2015", "Caiganle al pedononón en mi depa!", "Privada"));
        events.add(new Event("Vargas", "Tour de la primavera", "La primavera", "30/11/2015", "A darle a la bici!", "Pública"));
        events.add(new Event("Camarena", "Juego de ajedrez", "El patio de mi casa", "30/11/2015", "Torneo municipal anual de ajedrez, ¡están todos formalmente invitados!", "Privada"));
        events.add(new Event("Alejandro Velasco Espejo", "Maratón de Cosmos", "EL COSMOS", "31/11/2015", "", "Pública"));
        adapterEvents = new AdapterEvents(getActivity(), events);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterEvents);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Event event = events.get(position);
                        Intent intent = new Intent(getActivity(), ActivityEventsDescription.class);
                        intent.putExtra(EVENT_DESCRIPTION, event);
                        startActivity(intent);
                    }

                })
        );

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CREATE_EVENT) {
            if (data != null && data.getExtras() != null) {
                Place place = data.getParcelableExtra(PLACE);
                Event event = data.getParcelableExtra(EVENT);
                event.setPlace(place.getName());
                events.add(event);
                Collections.rotate(events, 1);
                adapterEvents.notifyDataSetChanged();
                recyclerView.scrollToPosition(0);
            }
        }
    }

    class EventsAsyncTask extends AsyncTask<String, String, String> {

        private JSonClient jSonClient;
        private final String TAG_SUCCESS = "success";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jSonClient = new JSonClient();
        }

        protected String doInBackground(String... args) {
            try {
                String jsonString = jSonClient.GetHTTPData("OUR BACKEND URL ---------> GET EVENTS");

                JSONObject json = new JSONObject(jsonString);
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    //GET ALL THE EVENTS INFO PARSING THE JSON
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            //UPDATE THE ADAPTER EVENTS AND PUT INFO INTO THE UI.
        }
    }

}
