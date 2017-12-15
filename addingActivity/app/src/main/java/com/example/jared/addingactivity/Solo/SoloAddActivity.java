package com.example.jared.addingactivity.Solo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jared.addingactivity.DatabaseHelper;
import com.example.jared.addingactivity.Group.GroupAddActivity;
import com.example.jared.addingactivity.ListDrawer;
import com.example.jared.addingactivity.MainActivity;
import com.example.jared.addingactivity.R;
import com.example.jared.addingactivity.Seq.SeqAddActivity;
import com.example.jared.addingactivity.Task;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Jared on 12/12/2017.
 */

public class SoloAddActivity extends AppCompatActivity {

    EditText etDesc;
    Button btnCreateTask;

    public SupportMapFragment maps;
    public GoogleMap map;
    public LatLng latLng;
    public Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_desc);
        final int requestCode;
        int requestCode1;
        try {
            requestCode1 = getIntent().getExtras().getInt("requestCode");
        }catch(Exception e){
            e.printStackTrace();
            requestCode1 = -1;
        }

        requestCode = requestCode1;

        etDesc = findViewById(R.id.taskDesc);
        btnCreateTask = findViewById(R.id.btn_create);

        maps = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        maps.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                if(map != null) {
                    MainActivity.refreshMap(map);
                }

                if(requestCode != -1){
                    switch(requestCode){
                        case GroupAddActivity.REQUEST_ADD_TASK:
                            ListDrawer.drawGroup(map, GroupAddActivity.list.getTasks());
                            break;
                        case SeqAddActivity.REQUEST_ADD_TASK:
                            ListDrawer.drawSequence(map,SeqAddActivity.list.getTasks());
                            break;
                    }
                }

                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        SoloAddActivity.this.latLng = latLng;

                        if(marker != null)
                           marker.remove();

                       marker = map.addMarker(new MarkerOptions().position(latLng));
                    }
                });
            }
        });






        btnCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(latLng != null && !etDesc.getText().toString().matches("")) {

                Intent resultData = new Intent();

                if (requestCode == -1) {

                    Task newTask = new Task();
                    newTask.setListId(-1);
                    newTask.setLatitude(SoloAddActivity.this.latLng.latitude);
                    newTask.setLongtitude(SoloAddActivity.this.latLng.longitude);
                    newTask.setSeq(-1);
                    newTask.setDone(false);
                    newTask.setDescription(etDesc.getText().toString());

                    MainActivity.db.insertTask(newTask);
                    finish();

                } else if (requestCode == GroupAddActivity.REQUEST_ADD_TASK) {
                    System.out.println(SoloAddActivity.this.latLng.latitude);
                    resultData.putExtra("desc", etDesc.getText().toString());
                    resultData.putExtra("lat", SoloAddActivity.this.latLng.latitude);
                    resultData.putExtra("lng", SoloAddActivity.this.latLng.longitude);
                    setResult(Activity.RESULT_OK, resultData);
                    finish();
                } else if (requestCode == SeqAddActivity.REQUEST_ADD_TASK) {
                    System.out.println(SoloAddActivity.this.latLng.latitude);
                    resultData.putExtra("desc", etDesc.getText().toString());
                    resultData.putExtra("lat", SoloAddActivity.this.latLng.latitude);
                    resultData.putExtra("lng", SoloAddActivity.this.latLng.longitude);
                    setResult(Activity.RESULT_OK, resultData);
                    finish();
                }
            }
            else if(latLng == null) {
                Snackbar.make(view, "Missing position", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            else if(etDesc.getText().toString().matches("")){
                Snackbar.make(view, "Description cannot be empty", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            }
        });
    }
}
