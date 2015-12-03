package com.kiwi.meetapp.gui;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kiwi.meetapp.R;
import com.kiwi.meetapp.beans.Place;

import java.util.List;

/**
 * Created by carlo_000 on 20/11/2015.
 */
public class FragmentPlaceMap extends Fragment implements OnMapReadyCallback, LocationListener {

    private MapFragment mMap; // Might be null if Google Play services APK is not available.
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private final float CAMERA_ZOOM = 11f;
    private final int REQUEST_CODE = 10;
    private LatLng latLng;
    private Place place;
    private final String PLACE = "Place";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity().getIntent().getExtras() != null && getActivity().getIntent().hasExtra(PLACE)) {
            place = getActivity().getIntent().getParcelableExtra(PLACE);
            if(place != null)
                latLng = new LatLng(place.getLatitude(), place.getLongitude());
        }

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());

        // Showing status
        if(status != ConnectionResult.SUCCESS){ // Google Play Services are not available
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, getActivity(), REQUEST_CODE);
            dialog.show();
        }

        setUpMapIfNeeded();

    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.activity_place_description_fragment_map));
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        try {
            googleMap = mMap.getMap();
            googleMap.setMyLocationEnabled(true);
            googleMap.setInfoWindowAdapter(CustomWindowAdapter.infoWindowAdapter(getActivity()));
            mMap.getMapAsync(this);
        } catch(Exception e) {

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Actual Location
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location location = getLocation();
        if(location != null){
            onLocationChanged(location);
        }

        if(latLng != null && place != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
            googleMap.addMarker(new MarkerOptions()
                    .title(place.getName())
                    .snippet(place.getDescription())
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(latLng));
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
                ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

        locationManager = (LocationManager) getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
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
                ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        return bestLocation;
    }


}
