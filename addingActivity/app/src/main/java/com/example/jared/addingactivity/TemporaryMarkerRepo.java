package com.example.jared.addingactivity;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

/**
 * Created by Jared on 12/13/2017.
 */

public class TemporaryMarkerRepo {
    private ArrayList<Marker> markers;

    public void addMarker(Marker marker){
        markers.add(marker);
    }

    public void removeMarker(int markerId){
        markers.remove(markerId);
    }

    public ArrayList<Marker> getMarkers() {
        return markers;
    }

    public void setMarkers(ArrayList<Marker> markers) {
        this.markers = markers;
    }
}
