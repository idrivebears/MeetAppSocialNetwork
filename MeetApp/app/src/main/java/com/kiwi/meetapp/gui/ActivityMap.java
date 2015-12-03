package com.kiwi.meetapp.gui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kiwi.meetapp.R;
import com.kiwi.meetapp.utils.FontFacade;
import com.kiwi.meetapp.utils.FontManager;

import java.util.List;

/**
 * Created by Carlos Alexis on 17/11/2015.
 */
public class ActivityMap extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private MapFragment mMap; // Might be null if Google Play services APK is not available.
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private final float CAMERA_ZOOM = 11f;
    private final int REQUEST_CODE = 10;
    private Button button;
    private static LatLng latLng;
    private final String RESULT = "Result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        button = (Button) findViewById(R.id.activity_map_button);

        try {
            FontFacade fontFacade = FontFacade.getInstance();
            fontFacade.setFontToView(this, button, FontFacade.LIGHT, FontManager.typeNormal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(latLng != null) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(RESULT, latLng);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMap.this);
                    builder.setMessage("Debe seleccionar una ubicación por favor.");
                    builder.setPositiveButton("Ok", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

        Bundle extra = getIntent().getExtras();
        if(extra != null){

        }

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        // Showing status
        if(status != ConnectionResult.SUCCESS){ // Google Play Services are not available
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, REQUEST_CODE);
            dialog.show();
        }

        setUpMapIfNeeded();

        // Setting a click event handler for the map
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker. This will be displayed on taping the marker
                markerOptions.title("Mi Ubicación");
                markerOptions.snippet("");

                // Clears the previously touched position
                googleMap.clear();

                // Animating to the touched position
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                googleMap.addMarker(markerOptions);
                ActivityMap.latLng = latLng;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.fragment_map));
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        googleMap = mMap.getMap();
        googleMap.setMyLocationEnabled(true);
        googleMap.setInfoWindowAdapter(CustomWindowAdapter.infoWindowAdapter(this));
        mMap.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Actual Location
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location location = getLocation();
        if(location != null){
            onLocationChanged(location);
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);

        // Showing the current location in Google Map
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(CAMERA_ZOOM));
        locationManager.removeUpdates(this);

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
    @Override
    public void onProviderEnabled(String provider) {}
    @Override
    public void onProviderDisabled(String provider) {}

    private Location getLocation() {

        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for(String provider : providers) {
            Location l = locationManager.getLastKnownLocation(provider);
            if(l == null) {
                continue;
            }
            if(bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        return bestLocation;
    }

}
