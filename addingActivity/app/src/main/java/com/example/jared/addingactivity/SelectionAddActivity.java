package com.example.jared.addingactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jared.addingactivity.Group.GroupAddActivity;
import com.example.jared.addingactivity.Solo.SoloAddActivity;

/**
 * Created by Jared on 12/8/2017.
 */

public class SelectionAddActivity extends AppCompatActivity{
    Button solobtn;
    Button groupbtn;
    Button seqbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        solobtn = (Button) findViewById(R.id.solotaskBtn);
        groupbtn = (Button) findViewById(R.id.grouptaskBtn);
        seqbtn = (Button) findViewById(R.id.sequencetaskBtn);;

        solobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(SelectionAddActivity.this,SoloAddActivity.class);
                startActivity(page);
            }
        });

        groupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(SelectionAddActivity.this,GroupAddActivity.class);
                startActivity(page);
            }
        });

        seqbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
