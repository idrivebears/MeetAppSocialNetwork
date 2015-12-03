package com.kiwi.meetapp.gui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.kiwi.meetapp.R;
import com.kiwi.meetapp.beans.Place;
import com.kiwi.meetapp.gui.adapters.AdapterPlaces;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ALEX on 16/11/2015.
 */
public class ActivityPlace extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private ArrayList<Place> places, filteredPlaces;
    private AdapterPlaces adapterPlaces;
    private final String PLACE = "Place";
    private final int REQUEST_CODE_CREATE = 77, REQUEST_CODE_DESCRIPTION = 89;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        searchView = (SearchView) (findViewById(R.id.activity_place_searchView));
        recyclerView = (RecyclerView) findViewById(R.id.activity_place_recyclerView);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.activity_place_floatingActionButton);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_place_swipeRefreshLayout);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ActivityCreatePlace.class);
                startActivityForResult(intent, REQUEST_CODE_CREATE);
            }
        });
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

        places = new ArrayList<>();

        places.add(new Place("Tacit Knowledge", "Compañia en Jalisco!", 20.7086277, -103.3781706));
        places.add(new Place("Casa de Alejandro", "Mi casa", -112.43456, 123.43212, 1));
        places.add(new Place("Casa de Carlos", "Mi casa", -112.43456, 123.43212, 2));
        places.add(new Place("Casa de Nacho", "Mi casa", -112.43456, 123.43212, 3));
        places.add(new Place("Casa de Juani", "Mi casa", -112.43456, 123.43212, 4));
        places.add(new Place("Casa de Camarena", "Mi casa", -112.43456, 123.43212, 5));
        places.add(new Place("Casa de Walls", "Mi casa", -112.43456, 123.43212, 4));
        places.add(new Place("Casa de Vargas", "Mi casa", -112.43456, 123.43212, 2));

        filteredPlaces = new ArrayList<>(places);

        adapterPlaces = new AdapterPlaces(this, filteredPlaces);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterPlaces);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Place place = filteredPlaces.get(position);
                        Intent intent = new Intent(ActivityPlace.this, ActivityPlaceDescription.class);
                        intent.putExtra(PLACE, place);
                        startActivityForResult(intent, REQUEST_CODE_DESCRIPTION);

                    }
                })
        );

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final ArrayList<Place> filterPlaces = filter(newText);
                adapterPlaces.animateTo(filterPlaces);
                recyclerView.scrollToPosition(0);
                return true;
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        searchView.clearFocus();
        if(requestCode == REQUEST_CODE_CREATE || requestCode == REQUEST_CODE_DESCRIPTION) {
            if(data != null && data.getExtras() != null) {
                Place place = data.getParcelableExtra(PLACE);
                returnData(place);
            }
        }
    }

    private ArrayList<Place> filter(String query) {
        query = query.toLowerCase();

        final ArrayList<Place> filterPlaces = new ArrayList<>();
        for (Place place : places) {
            final String text = place.getName().toLowerCase();
            if (text.contains(query)) {
                filterPlaces.add(place);
            }
        }
        return filterPlaces;
    }

    private void returnData(Place place) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(PLACE, place);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
    
}
