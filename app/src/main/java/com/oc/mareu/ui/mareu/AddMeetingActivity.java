package com.oc.mareu.ui.mareu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.oc.mareu.R;

import java.util.Objects;

public class AddMeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        //Back Home arrow
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
}