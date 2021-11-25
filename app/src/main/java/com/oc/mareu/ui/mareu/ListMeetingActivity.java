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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.transition.AutoTransition;
import android.transition.TransitionManager;

import android.view.View;

import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;


import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oc.mareu.R;
import com.oc.mareu.di.DI;
import com.oc.mareu.model.Meeting;
import com.oc.mareu.service.DummyMeetingApiService;
import com.oc.mareu.service.MeetingApiService;
import com.oc.mareu.ui.mareu.ListMeetingRecyclerViewAdapter;

public class ListMeetingActivity extends AppCompatActivity {

    private ViewPager mPager;
    private ListMeetingPagerAdapter mPagerAdapter;
    private LinearLayout hiddenView;
    private CardView cardView;
    private List<Meeting> mMeeting;
    private MeetingApiService mApiService;
    private TextView meetingDateFilter;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    //ListMeetingRecyclerViewAdapter adapter;
    ArrayAdapter<Meeting> adapter;
    String[] categories={"All","Réunion 1","Réunion 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

        setRoomSpinner();

        meetingDateFilter = findViewById(R.id.textView_DateFilter);

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
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        searchView.onActionViewCollapsed();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Log.d("newText1",query);
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Log.d("newText",newText);
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:
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
                    //onDestroy();
        }
         else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            onRestart();
            mApiService = DI.getMeetingApiService();
            mApiService.getMeetings().clear();
            //onDestroy();
        }
        setContentView(R.layout.activity_list_meeting);
    }
    ListView myListView;
    Spinner mySpinner;

    /**
     * setRoomSpinner method
     */
    public void setRoomSpinner() {

        Spinner spinner = (Spinner) findViewById(R.id.spinner_room);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterRoom = ArrayAdapter.createFromResource(this,
                R.array.spinner_room, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterRoom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapterRoom);


    }
}