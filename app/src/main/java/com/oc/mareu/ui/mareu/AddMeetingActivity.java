package com.oc.mareu.ui.mareu;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.mareu.R;
import com.oc.mareu.di.DI;
import com.oc.mareu.model.Meeting;
import com.oc.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class AddMeetingActivity extends AppCompatActivity {


    private Spinner colorSpinner;
    private Spinner roomSpinner;
    private TextView meetingDate;
    private TextView meetingHour;
    private EditText meetingCreator;
    private EditText meetingMembers;
    private Button addMeeting;
    private Button addMembers;

    private MeetingApiService mApiService;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        //Back Home arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getMeetingApiService();
        setColorSpinner();
        setRoomSpinner();

        colorSpinner = findViewById(R.id.spinner_color);
        roomSpinner = findViewById(R.id.spinner_room);
        meetingDate = findViewById(R.id.textView_date);
        meetingHour = findViewById(R.id.textView_hour);
        meetingCreator = findViewById(R.id.editTextText_creator);
        meetingMembers = findViewById(R.id.editTextText_member);
        addMembers = findViewById(R.id.button_add_members);

        meetingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddMeetingActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        day, month, year
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyy" + dayOfMonth + "/" + month + "/" +year);

                String date = dayOfMonth + "/" + month + "/" + year;
                meetingDate.setText(date);

            }
        };

        addMeeting = findViewById(R.id.button_add_meeting);
        addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addNewMeeting();

            }
        });
    }

    public void addNewMeeting() {

        colorSpinner = findViewById(R.id.spinner_color);
        roomSpinner = findViewById(R.id.spinner_room);
        meetingDate = findViewById(R.id.textView_date);
        meetingHour = findViewById(R.id.textView_hour);
        meetingCreator = findViewById(R.id.editTextText_creator);
        meetingMembers = findViewById(R.id.editTextText_member);


        Meeting newMeeting = new Meeting(

                colorSpinner.getSelectedItem().toString(),
                roomSpinner.getSelectedItem().toString(),
                meetingDate.getText().toString(),
                meetingHour.getText().toString(),
                meetingCreator.getText().toString(),
                meetingMembers.getText().toString()
                );
        mApiService.createMeeting(newMeeting);
        finish();

    }


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