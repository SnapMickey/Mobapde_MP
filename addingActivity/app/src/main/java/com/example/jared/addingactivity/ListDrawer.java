package com.example.jared.addingactivity;

import android.graphics.Color;
import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

/**
 * Created by Jared on 12/16/2017.
 */

public class ListDrawer {

    public static void drawGroup(GoogleMap map, ArrayList<Task> tasks){

        if(tasks.isEmpty())
            return;

        double lat1 = -9999999, lat2 = 999999, lng1 = -999999, lng2 = 999999;

        for(Task t : tasks){
            map.addMarker(new MarkerOptions().position(new LatLng(t.getLatitude(), t.getLongtitude())).title(t.getDescription()));

            if(t.latitude + t.longtitude > lat1 + lng1){
                lat1 = t.latitude;
                lng1 = t.longtitude;
            }

            if(t.latitude + t.longtitude < lat2 + lng2){
                lat2 = t.latitude;
                lng2 = t.longtitude;
            }
        }

        LatLng point1 = new LatLng(lat1,lng1);
        LatLng point2 = new LatLng(lat2, lng2);

        Location center = new Location("center");
        center.setLatitude(lat1);
        center.setLongitude(lng1);
        Location end1 = new Location("end1");
        end1.setLatitude(lat1);
        end1.setLongitude(lng1);
        Location end2 = new Location("end2");
        end2.setLatitude(lat2);
        end2.setLongitude(lng2);




        CircleOptions circleOptions = new CircleOptions();

        // Specifying the center of the circle
        circleOptions.center(new LatLng(center.getLatitude(),center.getLongitude()));

        // Radius of the circle
        circleOptions.radius(20);
        if(center.distanceTo(end1) > center.distanceTo(end2))
            circleOptions.radius(center.distanceTo(end1) + 20);
        else
            circleOptions.radius(center.distanceTo(end2) + 20);

        // Border color of the circle
        circleOptions.strokeColor(Color.BLACK);

        // Fill color of the circle
        circleOptions.fillColor(0x30ff0000);

        // Border width of the circle
        circleOptions.strokeWidth(2);

        // Adding the circle to the GoogleMap
        map.addCircle(circleOptions);

    }

    public static void drawSequence(GoogleMap map, ArrayList<Task> tasks){
        if(tasks.isEmpty())
            return;

        map.addMarker(new MarkerOptions().position(new LatLng(tasks.get(0).getLatitude(), tasks.get(0).getLongtitude())).title(tasks.get(0).getDescription()));

        for(int i = 0; i < tasks.size()-1; i++){

            LatLng task1 = new LatLng(tasks.get(i).getLatitude(),tasks.get(i).getLongtitude());
            LatLng task2 = new LatLng(tasks.get(i+1).getLatitude(),tasks.get(i+1).getLongtitude());
            map.addPolyline(new PolylineOptions().add(task1,task2).width(6).color(Color.BLUE)
                    .visible(true));

            map.addMarker(new MarkerOptions().position(new LatLng(tasks.get(i+1).getLatitude(), tasks.get(i+1).getLongtitude())).title(tasks.get(i+1).getDescription()));
        }
    }
}
