package com.oc.mareu.ui.mareu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oc.mareu.R;
import com.oc.mareu.di.DI;
import com.oc.mareu.model.Meeting;
import com.oc.mareu.service.MeetingApiService;

import java.util.List;

public class ListMeetingActivity extends AppCompatActivity {

    private ViewPager mPager;
    ListMeetingPagerAdapter mPagerAdapter;
    LinearLayout hiddenView;
    CardView cardView;
    List<Meeting> mMeeting;
    private MeetingApiService mApiService;
    //impor DI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

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
            case R.id.filter_menu:
                cardView = findViewById(R.id.base_cardview);

                hiddenView = findViewById(R.id.hidden_view);

                        // If the CardView is already expanded, set its visibility
                        //  to gone and change the expand less icon to expand more.
                        if (hiddenView.getVisibility() == View.VISIBLE) {

                            // The transition of the hiddenView is carried out
                            //  by the TransitionManager class.
                            // Here we use an object of the AutoTransition
                            // Class to create a default transition.
                            TransitionManager.beginDelayedTransition(cardView,
                                    new AutoTransition());
                            hiddenView.setVisibility(View.GONE);

                        }

                        // If the CardView is not expanded, set its visibility
                        // to visible and change the expand more icon to expand less.
                        else {

                            TransitionManager.beginDelayedTransition(cardView,
                                    new AutoTransition());
                            hiddenView.setVisibility(View.VISIBLE);

                        }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // TODO: 03/08/2021 restart activity after orientation changed
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //onRestart();
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
                    onRestart();
            // TODO: 05/08/2021 clean list button add doesn't work
            mApiService = DI.getMeetingApiService();
            mApiService.getMeetings().clear();
                    //onDestroy();
        }
         else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            onRestart();
            // TODO: 05/08/2021 clean list
            mApiService = DI.getMeetingApiService();
            mApiService.getMeetings().clear();
            //onDestroy();
        }
        setContentView(R.layout.activity_list_meeting);
    }
}