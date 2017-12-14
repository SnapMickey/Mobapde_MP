package com.example.jared.addingactivity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.jared.addingactivity.Tabs.GroupTab;
import com.example.jared.addingactivity.Tabs.SectionsPageAdapter;
import com.example.jared.addingactivity.Tabs.SeqTab;
import com.example.jared.addingactivity.Tabs.SoloTab;

/**
 * Created by Pons on 15/12/2017.
 */

public class ViewTasks extends AppCompatActivity {
    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SoloTab(), "TAB1");
        adapter.addFragment(new GroupTab(), "TAB2");
        adapter.addFragment(new SeqTab(), "TAB3");

        viewPager.setAdapter(adapter);

        //https://youtu.be/bNpWGI_hGGg
    }
}
