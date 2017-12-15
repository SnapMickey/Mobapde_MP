package com.example.jared.addingactivity.Solo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jared.addingactivity.DatabaseHelper;
import com.example.jared.addingactivity.Group.GroupAddActivity;
import com.example.jared.addingactivity.R;
import com.example.jared.addingactivity.Task;

/**
 * Created by Jared on 12/14/2017.
 */

public class SoloEditActivity extends AppCompatActivity {

    TextView tvDesc;
    Button btnCreateTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_desc);

        tvDesc = findViewById(R.id.taskDesc);
        btnCreateTask = findViewById(R.id.btn_create);

        btnCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String requestCode = getIntent().getStringExtra("requestCode");

                if(requestCode == null){

                    Task newTask = new Task();
                    newTask.setListId(-1);
                    newTask.setLatitude(0);
                    newTask.setLongtitude(0);
                    newTask.setSeq(-1);
                    newTask.setDone(false);
                    newTask.setDescription((String) tvDesc.getText());

                    DatabaseHelper dbHelper = new DatabaseHelper(getBaseContext());
                    dbHelper.insertTask(newTask);

                } else if(Integer.parseInt(requestCode) == GroupAddActivity.REQUEST_ADD_TASK){

                } else if(Integer.parseInt(requestCode) == 1){

                }
            }
        });
    }
}
