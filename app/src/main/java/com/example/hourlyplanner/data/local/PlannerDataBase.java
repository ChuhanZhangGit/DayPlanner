package com.example.hourlyplanner.data.local;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hourlyplanner.data.Days;
import com.example.hourlyplanner.data.SlotInDay;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;

import java.util.List;


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
                            PlannerDataBase.class, DB_NAME).addCallback(RoomCallBack).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback RoomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDBAsync(INSTANCE).execute();

        }
    };

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {

        private DaysDao daysDao;
        private SlotDao slotDao;

        public PopulateDBAsync(PlannerDataBase dataBase) {
            daysDao = dataBase.daysDao();
            slotDao = dataBase.slotsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            daysDao.deleteAllDate();

            LocalDate someDate = LocalDate.now();
            String taskDescription = "random task";
            LocalTime start = LocalTime.of( 8,30,0) ;
            LocalTime stop = LocalTime.of( 17 , 0 , 0 ) ;

            daysDao.insertDay(new Days(someDate));

            LocalTime iterator = start;
            while ( iterator.isBefore( stop ) ) {
                slotDao.insert(new SlotInDay(iterator, taskDescription, someDate));
                // Set up the next loop.
                iterator = iterator.plusMinutes(30);
            }

            return null;
        }
    }


}
