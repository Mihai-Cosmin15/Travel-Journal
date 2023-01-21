package com.mihainour.finalproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TripDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Trip trip);

    @Query("SELECT * FROM trip")
    LiveData<List<Trip>> getTrips();

    @Query("SELECT * FROM trip WHERE id = :trip_id")
    LiveData<Trip> getTripById(int trip_id);

    @Update
    void updateTrip(Trip trip);
}
