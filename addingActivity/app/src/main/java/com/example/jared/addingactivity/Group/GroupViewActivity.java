package com.example.jared.addingactivity.Group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jared.addingactivity.List;
import com.example.jared.addingactivity.MainActivity;
import com.example.jared.addingactivity.R;
import com.example.jared.addingactivity.Seq.SeqEditActivity;

/**
 * Created by Jared on 12/16/2017.
 */

public class GroupViewActivity extends AppCompatActivity {

    public static final int REQUEST_EDIT_TASK = 8;

    TextView tvTitle;
    RecyclerView rvTasks;
    Button btnEdit;
    GroupViewAdapter adapter;

    public static List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_group_list);

        tvTitle = findViewById(R.id.listTitle);
        rvTasks = findViewById(R.id.rv_tasks);
        btnEdit = findViewById(R.id.editBtn);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(GroupViewActivity.this,GroupEditActivity.class);
                page.putExtra("requestCode", GroupViewActivity.REQUEST_EDIT_TASK);
                startActivity(page);
            }
        });

        int listId = getIntent().getExtras().getInt("listId");
        list = MainActivity.db.queryList(listId);

        adapter = new GroupViewAdapter(list,this);

        rvTasks.setAdapter(adapter);
        rvTasks.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

    }
}
