package com.example.hourlyplanner.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.hourlyplanner.data.Days;
import com.example.hourlyplanner.data.SlotInDay;


@Database(entities = {Days.class, SlotInDay.class}, version = 1)
@TypeConverters({DateConverter.class, LocalTimeConverter.class})

public abstract class PlannerDataBase extends RoomDatabase {
    private static volatile PlannerDataBase INSTANCE;

    public abstract DaysDao daysDao();

    public abstract SlotDao slotsDao();

    private static final String DB_NAME = "planner.db";

    public static PlannerDataBase getDataBase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlannerDataBase.class) {
                if (INSTANCE == null) {

                    // Allow DB access on main thread should be avoided because it may
                    // cause UI to slow down when access DB.
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PlannerDataBase.class, DB_NAME).build();
                }
            }
        }
        return INSTANCE;
    }

}
