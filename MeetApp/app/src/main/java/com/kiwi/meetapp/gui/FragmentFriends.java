package com.kiwi.meetapp.gui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiwi.meetapp.R;
import com.kiwi.meetapp.beans.Friend;
import com.kiwi.meetapp.gui.adapters.AdapterFriends;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Carlos Alexis on 21/11/2015.
 */
public class FragmentFriends extends Fragment {

    private RecyclerView recyclerView;
    private final String FRIEND = "FRIEND", MY_PREFERENCES = "My_Preferences", FRIENDS = "Friends";
    private AdapterFriends adapterFriends;
    private ArrayList<Friend> friends;
    private SharedPreferences sharedPreferences;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static Fragment fragmentFriendsUniqueInstance;

    @SuppressLint("ValidFragment")
    private FragmentFriends(){

    }

    public static Fragment getInstance() {
        if(fragmentFriendsUniqueInstance == null) {
            fragmentFriendsUniqueInstance = new FragmentFriends();
        }
        return fragmentFriendsUniqueInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_activity_friends, container, false);

        //Cast to each element from Fragment
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_friends_recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_activity_friends_swipeRefreshLayout);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        friends = new ArrayList<>();

        adapterFriends = new AdapterFriends(getActivity(), friends);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterFriends);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Friend friend = friends.get(position);
                        Intent intent = new Intent(getActivity(), ActivityFriendProfile.class);
                        intent.putExtra(FRIEND, friend);
                        startActivity(intent);
                    }

                })
        );

        new ProfileAsyncTask().execute();

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

    private class ProfileAsyncTask extends AsyncTask<Void, Void, Void> {

        private String jsonObjectFriends;

        public ProfileAsyncTask() {
            jsonObjectFriends =  sharedPreferences.getString(FRIENDS, null);
        }

        @Override
        protected Void doInBackground(Void... params) {
            if(jsonObjectFriends != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonObjectFriends);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject friendJsonObject = jsonArray.getJSONObject(i);
                        int age = 0;
                        String gender = "", hometown = "", facebookID = "";
                        if(friendJsonObject.has("age_range")) {
                            try {
                                age = friendJsonObject.getJSONObject("age_range").getInt("max");
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if(friendJsonObject.has("gender")) {
                            gender = friendJsonObject.getString("gender");
                        }
                        if(friendJsonObject.has("hometown")) {
                            try {
                                hometown = friendJsonObject.getJSONObject("hometown").getString("name");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if(friendJsonObject.has("id")) {
                            facebookID = friendJsonObject.getString("id");
                        }
                        Friend friend = new Friend(friendJsonObject.getString("name"), age, gender.equals("male")? "Masculino": gender.equals("")? null : "Femenino", hometown.equals("")? null : hometown);
                        if (friendJsonObject.has("picture")) {
                            String profilePicUrl = friendJsonObject.getJSONObject("picture").getJSONObject("data").getString("url");
                            final URL url = new URL(profilePicUrl);
                            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            if(bitmap != null) {
                                friend.setBitmap(bitmap);
                            }
                        }
                        friend.setFacebookID(facebookID.equals("")? null : facebookID);
                        friends.add(friend);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapterFriends.notifyDataSetChanged();
        }
    }

}
