package com.oc.mareu.ui.mareu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oc.mareu.R;

public class ListMeetingActivity extends AppCompatActivity {

    private ViewPager mPager;
    ListMeetingPagerAdapter mPagerAdapter;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);
        
        //mToolbar = findViewById(R.id.my_toolbar);

        mPager = (ViewPager) findViewById(R.id.container);

        mPagerAdapter = new ListMeetingPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        FloatingActionButton fab = findViewById(R.id.add_meeting);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMeetingActivity.this, AddMeetingActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ressources, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_menu:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    // TODO: 03/08/2021 restart activity after orientation changed
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        onRestart();
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
                    onDestroy();
        }
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
//        }
        //setContentView(R.layout.activity_list_meeting);
    }
}