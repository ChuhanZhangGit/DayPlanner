package com.example.hourlyplanner.taskslot;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hourlyplanner.data.ConstantSlot;
import com.example.hourlyplanner.data.Days;
import com.example.hourlyplanner.data.PlannerRepository;
import com.example.hourlyplanner.data.SlotInDay;
import com.example.hourlyplanner.data.local.PlannerDataBase;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;

import java.util.List;

public class SlotsListViewModel extends AndroidViewModel {

    private PlannerRepository plannerRepository;

    public SlotsListViewModel(@NonNull Application application) {
        super(application);
        this.plannerRepository = PlannerRepository.getInstance(
                PlannerDataBase.getDataBase(application));
    }

    public LiveData<List<ConstantSlot>> getConstantSlots() {
        return plannerRepository.getAllConstantSlots();
    }

    public LiveData<List<SlotInDay>> getAllSlotsInDay(LocalDate localDate) {
        return plannerRepository.getAllSlotsInDay(localDate);
    }

    public void insertSlotInDay(LocalDate date, LocalTime time, String content) {
        plannerRepository.insertTimeSlot(date, time, content);
    }

    public void insertDay(LocalDate date) {
        plannerRepository.insertDate(date);
    }

    public LiveData<Days> getDayByDate(LocalDate localDate) {
        return plannerRepository.getDayByDate(localDate);
    }

}
