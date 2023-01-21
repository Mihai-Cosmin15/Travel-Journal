package com.mihainour.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.mihainour.finalproject.ui.home.HomeFragment;

import java.util.Date;
import java.util.List;

public class TripHandleActivity extends AppCompatActivity {

    private static final String TAG_ACTIVITY = "TripHandleActivity";

    private TripViewModel tripViewModel;

    private EditText editTextName, editTextDestination, editTextImageUrl;
    private RadioButton[] radioButtonType;
    private Slider sliderPrice;
    private DatePicker datePickerStart, datePickerEnd;
    private RatingBar ratingBar;
    private Button buttonSave;

    private String name;
    private String destination;
    private String type;
    private float price;
    private String startDate;
    private String endDate;
    private float rating;
    private String imageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_handle);

        int trip_id = getIntent().getIntExtra("TRIP_ID", 0);

        if (trip_id != 0) {

            tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
            tripViewModel.getTripById(trip_id).observe(this, new Observer<Trip>() {
                @Override
                public void onChanged(Trip trip) {
                    name = trip.getName();
                    editTextName = findViewById(R.id.editTextTripName);
                    editTextName.setText(name);

                    destination = trip.getDestination();
                    editTextDestination = findViewById(R.id.editTextTripDestination);
                    editTextDestination.setText(destination);

                    type = trip.getType();

                    radioButtonType = new RadioButton[3];
                    radioButtonType[0] = findViewById(R.id.radioButtonType1);
                    radioButtonType[1] = findViewById(R.id.radioButtonType2);
                    radioButtonType[2] = findViewById(R.id.radioButtonType3);
                    int typesNumber = radioButtonType.length;
                    String[] types = new String[typesNumber];
                    for (int i = 0; i < typesNumber; ++i) {
                        types[i] = radioButtonType[i].getText().toString();
                        if (type.equals(types[i])) {
                            radioButtonType[i].setChecked(true);
                            break;
                        }
                    }

                    price = trip.getPrice();
                    sliderPrice = findViewById(R.id.sliderTripPrice);
                    sliderPrice.setValue(price);

                    String[] dateSplit = trip.getStartDate().split("/");
                    int day = Integer.parseInt(dateSplit[0]);
                    int month = Integer.parseInt(dateSplit[1]);
                    int year = Integer.parseInt(dateSplit[2]);

                    datePickerStart = findViewById(R.id.datePickerStart);
                    datePickerStart.updateDate(year, month, day);

                    dateSplit = trip.getEndDate().split("/");
                    day = Integer.parseInt(dateSplit[0]);
                    month = Integer.parseInt(dateSplit[1]);
                    year = Integer.parseInt(dateSplit[2]);

                    datePickerEnd = findViewById(R.id.datePickerEnd);
                    datePickerEnd.updateDate(year, month, day);

                    rating = trip.getRating();
                    ratingBar = findViewById(R.id.ratingBarTrip);
                    ratingBar.setRating(rating);

                    imageurl = trip.getImageUrl();
                    editTextImageUrl = findViewById(R.id.editTextTripImageUrl);
                    editTextImageUrl.setText(imageurl);
                }
            });
        }

        buttonSave = findViewById(R.id.buttonSaveTrip);
        buttonSave.setOnClickListener(view -> {
            editTextName = findViewById(R.id.editTextTripName);
            editTextDestination = findViewById(R.id.editTextTripDestination);
            radioButtonType = new RadioButton[3];
            radioButtonType[0] = findViewById(R.id.radioButtonType1);
            radioButtonType[1] = findViewById(R.id.radioButtonType2);
            radioButtonType[2] = findViewById(R.id.radioButtonType3);
            sliderPrice = findViewById(R.id.sliderTripPrice);
            datePickerStart = findViewById(R.id.datePickerStart);
            datePickerEnd = findViewById(R.id.datePickerEnd);
            ratingBar = findViewById(R.id.ratingBarTrip);
            editTextImageUrl = findViewById(R.id.editTextTripImageUrl);

            boolean isValid = checkMandatoryFields();
            if (isValid) {
                name = editTextName.getText().toString();
                destination = editTextDestination.getText().toString();

                for (RadioButton radioButton : radioButtonType) {
                    if (radioButton.isChecked()) {
                        type = radioButton.getText().toString();
                        break;
                    }
                }

                price = sliderPrice.getValue();
                startDate = datePickerStart.getDayOfMonth() + "/" + datePickerStart.getMonth() + "/" + datePickerStart.getYear();
                endDate = datePickerEnd.getDayOfMonth() + "/" + datePickerEnd.getMonth() + "/" + datePickerEnd.getYear();
                rating = ratingBar.getRating();
                imageurl = editTextImageUrl.getText().toString();

                if (trip_id != 0) {
                    Trip trip = new Trip(trip_id, imageurl, name, destination, type, price, startDate, endDate, rating);
                    tripViewModel.updateTrip(trip);
                } else {
                    Trip trip = new Trip(imageurl, name, destination, type, price, startDate, endDate, rating);
                    tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
                    tripViewModel.insert(trip);
                }

                Intent navigationActivity = new Intent(TripHandleActivity.this, MainActivity.class);
                startActivity(navigationActivity);

                Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkMandatoryFields() {
        if (editTextName.length() == 0 || editTextDestination.length() == 0) {
            if (editTextName.length() == 0)
                editTextName.setError("Name is required");
            if (editTextDestination.length() == 0)
                editTextDestination.setError("Destination is required");
            return false;
        }
        return true;
    }
}