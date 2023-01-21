package com.mihainour.finalproject.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mihainour.finalproject.R;
import com.mihainour.finalproject.SelectTrip;
import com.mihainour.finalproject.Trip;
import com.mihainour.finalproject.TripAdapter;
import com.mihainour.finalproject.TripHandleActivity;
import com.mihainour.finalproject.TripInfoActivity;
import com.mihainour.finalproject.TripViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements SelectTrip {

    public static final String APP_KEY = "travel_journal_key";
    private static final String TAG_ACTIVITY = "HomeFragment";

    private TripViewModel tripViewModel;

    private List<Trip> tripList;
    private LiveData<List<Trip>> tripListLiveData;

    private FloatingActionButton fab;
    private RecyclerView tripsRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tripList = new ArrayList<>();
        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        tripsRecyclerView = view.findViewById(R.id.recyclerview);

        tripViewModel.getTrips().observe(getViewLifecycleOwner(), new Observer<List<Trip>>() {
            @Override
            public void onChanged(@Nullable List<Trip> trips) {
                tripsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                tripsRecyclerView.setAdapter(new TripAdapter(getContext(), trips, HomeFragment.this));
            }
        });

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.fab) {
                    startActivity(new Intent(getContext(), TripHandleActivity.class));
                }
            }
        });
        return view;
    }

    @Override
    public void onItemClicked(Trip trip) {
        Intent intent = new Intent(getActivity(), TripInfoActivity.class);
        intent.putExtra("TRIP_ID", trip.getId());
        startActivity(intent);
    }

    @Override
    public void onItemLongClicked(Trip trip) {
        Intent intent = new Intent(getActivity(), TripHandleActivity.class);
        intent.putExtra("TRIP_ID", trip.getId());
        startActivity(intent);
    }

    @Override
    public void onFavoriteButtonClicked(Trip trip, boolean isFavorite) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(
                HomeFragment.APP_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEdit = sharedPreferences
                .edit();
        sharedPreferencesEdit.putBoolean(""+trip.getId(), isFavorite);
        sharedPreferencesEdit.apply();
    }
}