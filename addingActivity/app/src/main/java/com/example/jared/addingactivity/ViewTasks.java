package com.example.jared.addingactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;

import com.example.jared.addingactivity.Tabs.GroupTab;
import com.example.jared.addingactivity.Tabs.SectionsPageAdapter;
import com.example.jared.addingactivity.Tabs.SeqTab;
import com.example.jared.addingactivity.Tabs.SoloTab;

/**
 * Created by Pons on 15/12/2017.
 */

public class ViewTasks extends AppCompatActivity {

    private static final String TAG = "ViewTasks";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_task);
        Log.d(TAG, "onCreate: Starting");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window window = this.getWindow();
        window.setStatusBarColor(Color.argb(100,111, 175, 140));

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        //
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        FragmentManager cfManager = getSupportFragmentManager();
        SectionsPageAdapter adapter = new SectionsPageAdapter(cfManager);
        Log.d(TAG, "setupViewPager: Starting");
        adapter.addFragment(new SoloTab(), "Solo");
        adapter.addFragment(new GroupTab(), "Group");
        adapter.addFragment(new SeqTab(), "Sequential");
        Log.d(TAG, (String) adapter.getPageTitle(0));

        viewPager.setAdapter(adapter);

        //https://youtu.be/bNpWGI_hGGg
    }
}
