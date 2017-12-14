package com.example.jared.addingactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        Log.d(TAG, "setupViewPager: Starting");
        adapter.addFragment(new SoloTab(), "TAB1");
        adapter.addFragment(new GroupTab(), "TAB2");
        adapter.addFragment(new SeqTab(), "TAB3");
        Log.d(TAG, (String) adapter.getPageTitle(0));

        viewPager.setAdapter(adapter);

        //https://youtu.be/bNpWGI_hGGg
    }
}
