package com.mihainour.finalproject;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Trip.class}, version = 1)
public abstract class TripRoomDatabase extends RoomDatabase {

    public abstract TripDao tripDao();

    private static TripRoomDatabase INSTANCE;

    static TripRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TripRoomDatabase.class,
                            "trip_database")
                    .build();
        }
        return INSTANCE;
    }

    static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(4);
}
