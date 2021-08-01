package com.oc.mareu.ui.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oc.mareu.R;

public class ListMeetingActivity extends AppCompatActivity {

//    private TabLayout tabLayout;
//    private ViewPager mViewPager;
//    private ListMeetingPageAdapter mPagerAdapter;

    private ViewPager viewPager;
    ListMeetingPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

        viewPager = (ViewPager) findViewById(R.id.container);

        mPagerAdapter = new ListMeetingPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mPagerAdapter);



        FloatingActionButton fab = findViewById(R.id.add_meeting);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMeetingActivity.this, AddMeetingActivity.class);
                startActivity(intent);
            }
        });
    }



}