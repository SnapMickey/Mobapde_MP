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
import com.example.jared.addingactivity.Group.GroupEditActivity;
import com.example.jared.addingactivity.ListDrawer;
import com.example.jared.addingactivity.MainActivity;
import com.example.jared.addingactivity.R;
import com.example.jared.addingactivity.Seq.SeqAddActivity;
import com.example.jared.addingactivity.Seq.SeqEditActivity;
import com.example.jared.addingactivity.Tabs.SoloTab;
import com.example.jared.addingactivity.Task;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Jared on 12/14/2017.
 */

public class SoloEditActivity extends AppCompatActivity {


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
        } catch (Exception e) {
            e.printStackTrace();
            requestCode1 = -1;
        }

        requestCode = requestCode1;

        etDesc = findViewById(R.id.taskDesc);
        btnCreateTask = findViewById(R.id.btn_create);

        maps = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        btnCreateTask.setText("Apply Changes");
        maps.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                if (map != null) {
                    MainActivity.refreshMap(map);
                }

                String desc = getIntent().getExtras().getString("desc");
                Double lat = getIntent().getExtras().getDouble("lat");
                Double lng = getIntent().getExtras().getDouble("lng");
                int position;
                if (requestCode != -1) {
                    switch (requestCode) {
                        case SoloTab.REQUEST_EDIT:
                            position = getIntent().getExtras().getInt("position");
                            GroupAddActivity.list.getTasks().remove(position);
                            ListDrawer.drawGroup(map, GroupAddActivity.list.getTasks());
                            etDesc.setText(desc);
                            latLng = new LatLng(lat,lng);
                            marker = map.addMarker(new MarkerOptions().position(latLng));
                            break;
                        case GroupAddActivity.REQUEST_EDIT_TASK:
                            position = getIntent().getExtras().getInt("position");
                            GroupAddActivity.list.getTasks().remove(position);
                            ListDrawer.drawGroup(map, GroupAddActivity.list.getTasks());
                            etDesc.setText(desc);
                            latLng = new LatLng(lat,lng);
                            marker = map.addMarker(new MarkerOptions().position(latLng));
                            break;
                        case SeqAddActivity.REQUEST_EDIT_TASK:
                            position = getIntent().getExtras().getInt("position");
                            GroupAddActivity.list.getTasks().remove(position);
                            ListDrawer.drawSequence(map, SeqAddActivity.list.getTasks());
                            etDesc.setText(desc);
                            latLng = new LatLng(lat,lng);
                            marker = map.addMarker(new MarkerOptions().position(latLng));
                            break;
                        case GroupEditActivity.REQUEST_EDIT_TASK:
                            position = getIntent().getExtras().getInt("position");
                            GroupAddActivity.list.getTasks().remove(position);
                            ListDrawer.drawSequence(map, SeqAddActivity.list.getTasks());
                            etDesc.setText(desc);
                            latLng = new LatLng(lat,lng);
                            marker = map.addMarker(new MarkerOptions().position(latLng));
                            break;
                        case SeqEditActivity.REQUEST_EDIT_TASK:
                            position = getIntent().getExtras().getInt("position");
                            GroupAddActivity.list.getTasks().remove(position);
                            ListDrawer.drawSequence(map, SeqAddActivity.list.getTasks());
                            etDesc.setText(desc);
                            latLng = new LatLng(lat,lng);
                            marker = map.addMarker(new MarkerOptions().position(latLng));
                            break;
                    }
                }

                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        SoloEditActivity.this.latLng = latLng;

                        if (marker != null)
                            marker.remove();

                        marker = map.addMarker(new MarkerOptions().position(latLng));
                    }
                });
            }
        });


        btnCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (latLng != null && !etDesc.getText().toString().matches("")) {

                    Intent resultData = new Intent();
                    int taskId;
                    int position;
                    if (requestCode == -1) {
                        taskId = getIntent().getIntExtra("taskId", 0);
                        Task task = MainActivity.db.queryTask(taskId);
                        task.setDescription(etDesc.getText().toString());
                        task.setLatitude(latLng.latitude);
                        task.setLongtitude(latLng.longitude);

                        MainActivity.db.updateTask(task, taskId);
                        finish();

                    } else if (requestCode == GroupAddActivity.REQUEST_EDIT_TASK) {
                        System.out.println(SoloEditActivity.this.latLng.latitude);
                        position = getIntent().getExtras().getInt("position");
                        resultData.putExtra("position", position);
                        resultData.putExtra("desc", etDesc.getText().toString());
                        resultData.putExtra("lat", SoloEditActivity.this.latLng.latitude);
                        resultData.putExtra("lng", SoloEditActivity.this.latLng.longitude);
                        setResult(Activity.RESULT_OK, resultData);
                        finish();
                    } else if (requestCode == SeqAddActivity.REQUEST_EDIT_TASK) {
                        System.out.println(SoloEditActivity.this.latLng.latitude);
                        position = getIntent().getExtras().getInt("position");
                        resultData.putExtra("position", position);
                        resultData.putExtra("desc", etDesc.getText().toString());
                        resultData.putExtra("lat", SoloEditActivity.this.latLng.latitude);
                        resultData.putExtra("lng", SoloEditActivity.this.latLng.longitude);
                        setResult(Activity.RESULT_OK, resultData);
                        finish();
                    } else if (requestCode == GroupEditActivity.REQUEST_EDIT_TASK) {
                        position = getIntent().getExtras().getInt("position");
                        resultData.putExtra("position", position);
                        System.out.println(SoloEditActivity.this.latLng.latitude);
                        resultData.putExtra("desc", etDesc.getText().toString());
                        resultData.putExtra("lat", SoloEditActivity.this.latLng.latitude);
                        resultData.putExtra("lng", SoloEditActivity.this.latLng.longitude);
                        setResult(Activity.RESULT_OK, resultData);
                        finish();
                    } else if (requestCode == SeqEditActivity.REQUEST_EDIT_TASK) {
                        position = getIntent().getExtras().getInt("position");
                        resultData.putExtra("position", position);
                        System.out.println(SoloEditActivity.this.latLng.latitude);
                        resultData.putExtra("desc", etDesc.getText().toString());
                        resultData.putExtra("lat", SoloEditActivity.this.latLng.latitude);
                        resultData.putExtra("lng", SoloEditActivity.this.latLng.longitude);
                        setResult(Activity.RESULT_OK, resultData);
                        finish();
                    }
                } else if (latLng == null) {
                    Snackbar.make(view, "Missing position", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (etDesc.getText().toString().matches("")) {
                    Snackbar.make(view, "Description cannot be empty", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }
}
