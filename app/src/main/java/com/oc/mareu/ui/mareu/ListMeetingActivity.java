package com.oc.mareu.ui.mareu;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;

import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.transition.AutoTransition;
import android.transition.TransitionManager;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;


import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oc.mareu.R;
import com.oc.mareu.di.DI;
import com.oc.mareu.model.Meeting;
import com.oc.mareu.service.MeetingApiService;

public class ListMeetingActivity extends AppCompatActivity {

    private ViewPager mPager;
    private ListMeetingPagerAdapter mPagerAdapter;
    private LinearLayout hiddenView;
    private CardView cardView;
    private List<Meeting> mMeeting;
    private MeetingApiService mApiService;
    private Spinner meetingRoomFilter;
    private Button meetingRoomFilterBtn;
    private TextView meetingDateFilter;
    private Button meetingDateFilterBtn;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    ArrayAdapter<Meeting> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

        setRoomSpinner();

        meetingRoomFilter = findViewById(R.id.spinner_room_filter);
        meetingRoomFilterBtn = findViewById(R.id.roomFilterBtn);

        meetingDateFilter = findViewById(R.id.textView_DateFilter);
        meetingDateFilterBtn = findViewById(R.id.dateFilterBtn);

        mPager = findViewById(R.id.container);

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

        //Filter by room button
        meetingRoomFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (meetingRoomFilter.getSelectedItem().toString().equals("choisissez votre salle"))
                {
                    Toast.makeText(getApplicationContext(),"Veillez chosir une salle",
                            Toast.LENGTH_LONG).show();
                }else {
                    //mApiService.getFilteredByRoomMeetings(meetingRoomFilter.getSelectedItem().toString());

                    //notifyDataSetChanged();
                }

            }
        });

        //Filter By date button
        meetingDateFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (meetingDateFilter.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Veillez chosir la date",
                            Toast.LENGTH_LONG).show();
                }else {
                    //mApiService.getFilteredByRoomMeetings(meetingDateFilter);

                    //notifyDataSetChanged();
                }

            }
        });

        /**
         * DatePicker for Filter
         */
        meetingDateFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                Locale.setDefault(Locale.FRANCE);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ListMeetingActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        day, month, year
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }

        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyy" + dayOfMonth + "/" + month + "/" +year);
                String date = dayOfMonth + "/" + month + "/" + year;
                meetingDateFilter.setText(date);

            }

        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ressources, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:
                cardView = findViewById(R.id.filterCardView);

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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        //onRestart();
        // Checks the orientation of the screen

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
                    onRestart();
            mApiService = DI.getMeetingApiService();
            mApiService.getMeetings().clear();
        }
         else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            onRestart();
            mApiService = DI.getMeetingApiService();
            mApiService.getMeetings().clear();
        }
        setContentView(R.layout.activity_list_meeting);
    }

    /**
     * setRoomSpinner method
     */
    public void setRoomSpinner() {

        Spinner spinner = (Spinner) findViewById(R.id.spinner_room_filter);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterRoom = ArrayAdapter.createFromResource(this,
                R.array.spinner_filter_room, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterRoom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapterRoom);

    }
}