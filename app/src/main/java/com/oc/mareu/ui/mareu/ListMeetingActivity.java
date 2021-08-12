package com.oc.mareu.ui.mareu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import android.view.Menu;

import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import android.transition.AutoTransition;
import android.transition.TransitionManager;

import android.view.View;

import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;


import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
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
    ListMeetingRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

        setRoomSpinner();

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ressources, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.onActionViewCollapsed();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });
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

    // TODO: 03/08/2021 restart activity after orientation changed
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

//    /*
//Initialize ListView and Spinner, set their adapters and listen to spinner itemSelection events
//*/
//    private void initializeViews() {
//
//        mySpinner = findViewById(R.id.spinner_room);
//        mySpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories));
//
//        myListView.setAdapter((ListAdapter) mPagerAdapter);
//
//        //spinner selection events
//        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long itemID) {
//                if (position >= 0 && position < mySpinner..length) {
//                    getSelectedCategoryData(position);
//                } else {
//                    Toast.makeText(ListMeetingActivity.this, "Selected Category Does not Exist!", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }
//    /*
//Get the selected category's cosmic bodies and bind to ListView
// */
//    private void getSelectedCategoryData(int categoryID) {
//        //arraylist to hold selected cosmic bodies
//        ArrayList<Meeting> cosmicBodies = new ArrayList<>();
//        if(categoryID == 0)
//        {
//            adapter = new ArrayAdapter<Meeting>(this, android.R.layout.simple_list_item_1, new DummyMeetingApiService().getMeetings());
//        }else{
//            //filter by id
//            for (Meeting cosmicBody : mMeeting) {
//                if (cosmicBody.getRoomName() == mMeeting) {
//                    cosmicBodies.add(cosmicBody);
//                }
//            }
//            //instatiate adapter a
//            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cosmicBodies);
//        }
//        //set the adapter to GridView
//        myListView.setAdapter(adapter);
//    }



}