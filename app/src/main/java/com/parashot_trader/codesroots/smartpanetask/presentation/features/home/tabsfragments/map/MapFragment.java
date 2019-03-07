package com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.map;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.parashot_trader.codesroots.smartpanetask.R;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    MapView mapView;
    GoogleMap map;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private double currentlat, currentlang;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        callPermission();
        SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);
        return view;
    }

    private void callPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 11);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.isBuildingsEnabled();
        map.setOnInfoWindowClickListener(this);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        map.setIndoorEnabled(true);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(getActivity(), location -> {
            LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
            currentlat = location.getLatitude();
            currentlang = location.getLongitude();
            map.addMarker(new MarkerOptions().position(sydney).title("Your location"));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(6).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            createMarker(27.1770706, 31.1669084, "First Location", "Assuit ");
            createMarker(26.5605332, 31.6630057, "Second Location ", " Sohage");
            createMarker(30.0169184, 31.1196024, "Third Location", "Giza ");
        });

    }

    protected void createMarker(double latitude, double longitude, String title, String snippet) {

        map.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet + ": "+getDistance(currentlat, currentlang, latitude, longitude)+"k.m")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location)));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String uri = "http://maps.google.com/maps?f=d&hl=en&saddr=" + marker.getPosition().latitude + ","
                + marker.getPosition().longitude + "&daddr=" +
                currentlang + "," + currentlang;
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }


    private String getDistance(double latA, double lngA, double latB, double lngB) {
        Location locationA = new Location("point A");
        locationA.setLatitude(latA);
        locationA.setLongitude(lngA);
        Location locationB = new Location("point B");
        locationB.setLatitude(latB);
        locationB.setLongitude(lngB);
        float distance = locationA.distanceTo(locationB);
        return String.valueOf(distance / 1000);
    }

}
