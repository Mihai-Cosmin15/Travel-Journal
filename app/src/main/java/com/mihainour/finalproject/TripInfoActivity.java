package com.mihainour.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class TripInfoActivity extends AppCompatActivity {

    private static final String TAG_ACTIVITY = "TripInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_info);

        int trip_id = getIntent().getIntExtra("TRIP_ID", 0);
        Log.e(TAG_ACTIVITY, "" + trip_id);
    }
}