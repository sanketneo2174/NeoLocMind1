package com.example.android.neolocmind1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.MapView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {

    MapView mapView;
    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mapboxMap) {

                // Customize map with markers, polylines, etc.

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    /*class temp implements OnMapReadyCallback{
        public void onMapReady(GoogleMap gmap){

        }
    }*/
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }
    public void onPause(){
        super.onPause();
        mapView.onPause();
    }
    public void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }
    public void onLowMemory(){
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
