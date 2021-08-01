package com.oc.mareu.ui.mareu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.oc.mareu.R;
import com.oc.mareu.di.DI;
import com.oc.mareu.model.Meeting;
import com.oc.mareu.service.MeetingApiService;

import java.util.Objects;

public class AddMeetingActivity extends AppCompatActivity {


    private Spinner colorSpinner;
    private Spinner roomSpinner;
    private EditText meetingRoom;
    private EditText meetingDate;
    private EditText meetingHour;
    private EditText meetingCreator;
    private EditText meetingMembers;
    private Button addMeeting;

    private MeetingApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        //Back Home arrow
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getMeetingApiService();
        //init();

        colorSpinner = findViewById(R.id.spinner_color);
        roomSpinner = findViewById(R.id.spinner_room);
        meetingDate = findViewById(R.id.editText_date);
        meetingHour = findViewById(R.id.editText_hour);
        meetingCreator = findViewById(R.id.editTextText_creator);
        meetingMembers = findViewById(R.id.editTextText_member);

        final String[] spinnerColor = new String[1];
        final String[] spinnerRoom = new String[1];



//        ArrayAdapter adapterColor = new ArrayAdapter(getApplicationContext(),
//                R.layout.support_simple_spinner_dropdown_item, R.id.spinner_color);
//        adapterColor.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//
//        ArrayAdapter adapterRoom = new ArrayAdapter(getApplicationContext(),
//                R.layout.support_simple_spinner_dropdown_item, R.id.spinner_color);
//        adapterRoom.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);


        addMeeting = findViewById(R.id.button_add_meeting);
        addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Meeting newMeeting = new Meeting(

                        spinnerColor[0] = colorSpinner.getSelectedItem().toString(),
                        spinnerRoom[0] = roomSpinner.getSelectedItem().toString(),
                        meetingDate.getText().toString(),
                        meetingHour.getText().toString(),
                        meetingCreator.getText().toString(),
                        meetingMembers.getText().toString()

                );

                mApiService.createMeeting(newMeeting);
                finish();
                //addNewMeeting();
            }
        });

        setColorSpinner();
        setRoomSpinner();

    }

//    public void addNewMeeting() {
//
//        String spinnerColor;
//        String spinnerRoom;
//
//        colorSpinner = findViewById(R.id.spinner_color);
//        roomSpinner = findViewById(R.id.spinner_room);
//        meetingDate = findViewById(R.id.editText_date);
//        meetingHour = findViewById(R.id.editText_hour);
//        meetingCreator = findViewById(R.id.editTextText_creator);
//        meetingMembers = findViewById(R.id.editTextText_member);
//
//
//        Meeting newMeeting = new Meeting(
//
//                spinnerColor = colorSpinner.getSelectedItem().toString(),
//                spinnerRoom = roomSpinner.getSelectedItem().toString(),
//                meetingDate.getEditableText().getText().toString()
//
//        );
//        mApiService.createMeeting(newMeeting);
//        finish();
//
//    }

    public void setColorSpinner() {

        Spinner spinnerColor = (Spinner) findViewById(R.id.spinner_color);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterColor = ArrayAdapter.createFromResource(this,
                R.array.spinner_color, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerColor.setAdapter(adapterColor);

    }

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