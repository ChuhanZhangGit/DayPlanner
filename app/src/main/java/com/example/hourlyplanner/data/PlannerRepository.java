package com.example.hourlyplanner.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.hourlyplanner.data.local.ConstantSlotDao;
import com.example.hourlyplanner.data.local.PlannerDataBase;
import com.example.hourlyplanner.data.local.SlotDao;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;

import java.util.List;

public class PlannerRepository {

    private static PlannerRepository INSTANCE = null;

    private final PlannerDataBase localDataBase;


    private final SlotDao slotDao;

    private final ConstantSlotDao constantSlotDao;

    private PlannerRepository(PlannerDataBase localDataBase) {
        this.localDataBase = localDataBase;
        slotDao = localDataBase.slotsDao();
        constantSlotDao = localDataBase.constantSlotDao();
    }

    public static PlannerRepository getInstance(PlannerDataBase localDataBase) {
        if (INSTANCE == null) {
            INSTANCE = new PlannerRepository(localDataBase);
        }
        return INSTANCE;
    }


    public void insertTimeSlot(LocalDate localDate, LocalTime localTime, String taskDescription) {
        SlotInDay slotInDay = new SlotInDay(localTime, taskDescription, localDate);
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

//    public void updateTimeSlot(final SlotInDay slotInDay) {
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                slotDao.updateTaskInDay(slotInDay);
//                return null;
//            }
//        }.execute();
//    }

    public LiveData<List<ConstantSlot>> getAllConstantSlots() {
        return constantSlotDao.getAllConstantSlot();
    }


    public LiveData<List<SlotInDay>> getAllSlotsInDay(final LocalDate localDate) {
        return slotDao.getSlotsInDay(localDate);
    }

}
