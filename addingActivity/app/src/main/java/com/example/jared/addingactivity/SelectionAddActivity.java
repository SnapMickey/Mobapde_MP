package com.example.jared.addingactivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.jared.addingactivity.Group.GroupAddActivity;
import com.example.jared.addingactivity.Seq.SeqAddActivity;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Window window = this.getWindow();
        window.setStatusBarColor(Color.argb(100,186,65,48));

        solobtn = (Button) findViewById(R.id.solotaskBtn);
        groupbtn = (Button) findViewById(R.id.grouptaskBtn);
        seqbtn = (Button) findViewById(R.id.sequencetaskBtn);;

        solobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(SelectionAddActivity.this,SoloAddActivity.class);
                startActivity(page);
                finish();
            }
        });

        groupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(SelectionAddActivity.this,GroupAddActivity.class);
                startActivity(page);
                finish();
            }
        });

        seqbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(SelectionAddActivity.this,SeqAddActivity.class);
                startActivity(page);
                finish();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
