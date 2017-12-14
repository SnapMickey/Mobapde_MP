package com.example.jared.addingactivity.Group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jared.addingactivity.DatabaseHelper;
import com.example.jared.addingactivity.List;
import com.example.jared.addingactivity.R;
import com.example.jared.addingactivity.Solo.SoloAddActivity;
import com.example.jared.addingactivity.Task;
import com.example.jared.addingactivity.TemporaryMarkerRepo;

import java.util.ArrayList;

/**
 * Created by Jared on 12/12/2017.
 */

public class GroupAddActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_TASK = 0;
    public static final int REQUEST_EDIT_TASK = 1;

    List list;
    TextView tvListName;
    Button createButton, addTask;
    RecyclerView rvTasks;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_group);


        dbHelper = new DatabaseHelper(getBaseContext());

        tvListName = findViewById(R.id.tv_gtasks);
        createButton = findViewById(R.id.btn_create_grp_list);
        addTask = findViewById(R.id.btn_add_task);
        rvTasks = findViewById(R.id.rv_gtasks);


        list = new List();
        list.setDone(false);
        list.setType("grp");
        list.setTitle("");
        list.setTasks(new ArrayList<Task>());

        /**TEST*/
        Task t = new Task();
        t.setDescription("JARED WINS");
        t.setDone(false);
        t.setSeq(-1);
        t.setLongtitude(0);
        t.setLatitude(0);

        list.addTasks(t);

        /**END_TEST*/

        GroupTasksAddAdapter adapter = new GroupTasksAddAdapter(list, this);

        rvTasks.setAdapter(adapter);
        rvTasks.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                list.setTitle((String) tvListName.getText());

                long listId = dbHelper.insertList(list);

                for(Task t : list.getTasks()){
                    t.setListId((int)listId);
                    dbHelper.insertTask(t);
                }

                finish();
            }
        });

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(GroupAddActivity.this, SoloAddActivity.class);
                page.putExtra("type","add");
                page.putExtra("requestCode", REQUEST_ADD_TASK);
                startActivityForResult(page,REQUEST_ADD_TASK);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_ADD_TASK:
                    String desc = data.getStringExtra("desc");
                    double lat = Double.parseDouble(data.getStringExtra("lat"));
                    double lng = Double.parseDouble(data.getStringExtra("lng"));

                    Task newTask = new Task();
                    newTask.setDone(false);
                    newTask.setSeq(-1);
                    newTask.setLatitude(lat);
                    newTask.setLongtitude(lng);
                    newTask.setListId(-1);
                    list.addTasks(newTask);
                    break;
            }
        }
    }
}
