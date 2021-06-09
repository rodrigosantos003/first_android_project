package com.example.projetofinal_rodrigosantos;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

        private GoogleMap mMap;

        Geocoder geo;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mapa);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        geo = new Geocoder(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        local();
    }

    private void local(){
        Intent i = getIntent();
        Bundle b = i.getExtras();
        String txt = b.getString("local");

        LatLng coordenadas;

        List<Address> add = null;
        try {
            add = geo.getFromLocationName(txt, 3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Address ad = add.get(0);
        coordenadas = new LatLng(ad.getLatitude(), ad.getLongitude());

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenadas, 13));
        mMap.addMarker(new MarkerOptions().position(coordenadas).title(txt));
    }
}