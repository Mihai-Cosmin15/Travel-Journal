package com.mihainour.finalproject;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TripRepository {
    private TripDao tripDao;
    private LiveData<List<Trip>> trips;

    TripRepository(Application application) {
        TripRoomDatabase tripRoomDatabase = TripRoomDatabase.getDatabase(application);
        tripDao = tripRoomDatabase.tripDao();
        trips = tripDao.getTrips();
    }

    void insert(Trip trip) {
        TripRoomDatabase.databaseWriterExecutor.execute(() -> {
            tripDao.insert(trip);
        });
    }

    LiveData<List<Trip>> getTrips() {
        return trips;
    }

    LiveData<Trip> getTripById(int trip_id) {
        return tripDao.getTripById(trip_id);
    }

    void updateTrip(Trip trip) {
        TripRoomDatabase.databaseWriterExecutor.execute(() -> {
            tripDao.updateTrip(trip);
        });
    }
}
