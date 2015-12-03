package com.kiwi.meetapp.gui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kiwi.meetapp.R;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by carlo_000 on 16/11/2015.
 */
public class ActivityLogin extends Activity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private FacebookCallback<LoginResult> facebookCallback;
    private Profile profile;
    private AccessToken accessToken;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private final String PROFILE = "Profile", PROFILE_NAME = "Profile_name", PROFILE_ID = "Profile_ID", PROFILE_URI = "Profile_Uri", MY_PREFERENCES = "My_Preferences", FRIENDS = "Friends", ME = "ME", LOGGED = "LOGGED";
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getBoolean(LOGGED, false)) {
            goActivityMain();
        }

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_login);
        loginButton = (LoginButton) findViewById(R.id.activity_login_button_login);

        facebookCallback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken = loginResult.getAccessToken();
                profile = Profile.getCurrentProfile();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        };

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                profile = currentProfile;
                if(profile != null) {
                    editor.putString(PROFILE_NAME, profile.getName());
                    editor.putString(PROFILE_ID, profile.getId());
                    editor.putString(PROFILE_URI, profile.getLinkUri().toString());
                    editor.apply();
                    Bundle paramsUser = new Bundle(), paramsFriends = new Bundle();
                    paramsUser.putString("fields", "id, name, age_range, birthday, hometown, gender, picture.type(large)");
                    paramsFriends.putString("fields", "id, name, age_range, hometown, gender, picture.type(large)");
                    GraphRequestBatch batch = new GraphRequestBatch(
                            new GraphRequest(accessToken, "me", paramsUser, HttpMethod.GET, new GraphRequest.Callback() {
                                @Override
                                public void onCompleted(GraphResponse response) {
                                    if (response != null) {
                                        try {
                                            JSONObject jsonObject = response.getJSONObject();
                                            String jsonObjectMe = jsonObject.toString();
                                            editor.putString(ME, jsonObjectMe);
                                            editor.apply();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }),
                            new GraphRequest(accessToken, "me/friends", paramsFriends, HttpMethod.GET, new GraphRequest.Callback() {
                                @Override
                                public void onCompleted(GraphResponse response) {
                                    if (response != null) {
                                        try {
                                            JSONObject jsonObject = response.getJSONObject();
                                            String jsonObjectFriends = jsonObject.toString();
                                            editor.putString(FRIENDS, jsonObjectFriends);
                                            editor.apply();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            })
                    );
                    batch.addCallback(new GraphRequestBatch.Callback() {
                        @Override
                        public void onBatchCompleted(GraphRequestBatch graphRequests) {
                            // Application code for when the batch finishes
                            editor.putBoolean(LOGGED, true);
                            editor.apply();
                            goActivityMain();
                        }
                    });
                    batch.executeAsync();

                }
            }
        };

        callbackManager = CallbackManager.Factory.create();

        loginButton.setReadPermissions(Arrays.asList("public_profile", "user_friends", "user_birthday", "user_about_me", "user_hometown"));
        loginButton.registerCallback(callbackManager, facebookCallback);

    }

    @Override
    protected void onResume() {
        super.onResume();
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }

    @Override
    protected void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void goActivityMain() {
        Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
        intent.putExtra(PROFILE, profile);
        startActivity(intent);
        finish();
    }

}
