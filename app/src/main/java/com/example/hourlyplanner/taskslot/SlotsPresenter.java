package com.example.hourlyplanner.taskslot;

import android.util.Log;

import com.example.hourlyplanner.data.Days;
import com.example.hourlyplanner.data.DaysDao;
import com.example.hourlyplanner.data.PlannerDataBase;
import com.example.hourlyplanner.data.SlotDao;
import com.example.hourlyplanner.data.SlotInDay;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;

import java.util.List;

public class SlotsPresenter implements SlotsContract.Presenter {

    private final PlannerDataBase dataBase;
    private final SlotsContract.Fragment view;

    public SlotsPresenter(PlannerDataBase dataBase, SlotsContract.Fragment view) {
        this.dataBase = dataBase;
        this.view = view;

        view.setPresenter(this);
    }


    @Override
    public void loadSlotsInDay() {
        SlotDao slotDao = dataBase.taskDao();
        DaysDao daysDao = dataBase.daysDao();


        LocalDate someDate = LocalDate.of(2019, 1,1);
        String taskDescription = "random task";
        LocalTime start = LocalTime.of( 8,30,0) ;
        LocalTime stop = LocalTime.of( 17 , 0 , 0 ) ;


        daysDao.addNewDayToDataBase(new Days(someDate));


        LocalTime iterator = start;
        while ( iterator.isBefore( stop ) ) {
            slotDao.insert(new SlotInDay(iterator, taskDescription, someDate));
            // Set up the next loop.
            iterator = iterator.plusMinutes(30);
        }

        List<SlotInDay> slot = slotDao.getAllTasks();
        view.showSlotsInDay(slot);
        Log.i("presenter", "showslot");
    }

    @Override
    public void start() {

    }
}
