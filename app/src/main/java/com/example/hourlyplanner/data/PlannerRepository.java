package com.example.hourlyplanner.data;

import android.os.AsyncTask;

import com.example.hourlyplanner.data.local.DaysDao;
import com.example.hourlyplanner.data.local.PlannerDataBase;
import com.example.hourlyplanner.data.local.SlotDao;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;

import java.util.List;

public class PlannerRepository {

    private static  PlannerRepository INSTANCE = null;

    private final PlannerDataBase localDataBase;

    private final DaysDao daysDao;

    private final SlotDao slotDao;

    public PlannerRepository(PlannerDataBase localDataBase) {
        this.localDataBase = localDataBase;
        daysDao = localDataBase.daysDao();
        slotDao = localDataBase.slotsDao();
    }

    public void insertDate(LocalDate localDate) {
        daysDao.addNewDayToDataBase(new Days(localDate));
    }

    public void insertTimeSlot(LocalTime localTime, LocalDate localDate) {
        SlotInDay slotInDay = new SlotInDay(localTime, null, localDate);
        insertTimeSlot(slotInDay);
    }

    public void insertTimeSlot(final SlotInDay slotInDay) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                slotDao.insert(slotInDay);
                return null;
            }

        }.execute();
    }

    public void updateTimeSlot(final SlotInDay slotInDay) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                slotDao.updateTaskInDay(slotInDay);
                return null;
            }
        }.execute();
    }

    public List<SlotInDay> getAllSlotsInDay(final LocalDate localDate) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                slotDao.getSlotsInDay(localDate);
                return null;
            }
        }
    }
}
