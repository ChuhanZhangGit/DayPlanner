package com.example.hourlyplanner.taskslot;

import com.example.hourlyplanner.data.PlannerDataBase;
import com.example.hourlyplanner.data.SlotDao;
import com.example.hourlyplanner.data.SlotInDay;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;

import java.util.List;

public class TasksPresenter implements SlotsContract.Presenter {

    private final PlannerDataBase dataBase;
    private final SlotsContract.Fragment view;

    public TasksPresenter(PlannerDataBase dataBase, SlotsContract.Fragment view) {
        this.dataBase = dataBase;
        this.view = view;
    }


    private LocalTime getNewLocalTime() {
        return LocalTime.of(2,30,0);
    }

    @Override
    public void loadSlotsInDay() {
        SlotDao slotDao = dataBase.taskDao();

        LocalDate randomDate = LocalDate.of(2019, 1,1);
        String taskDescription = "random task";
        LocalTime start = LocalTime.of( 8,30,0) ;
        LocalTime stop = LocalTime.of( 17 , 0 , 0 ) ;

        LocalTime iterator = start;
        while ( iterator.isBefore( stop ) ) {
            slotDao.insert(new SlotInDay(iterator, taskDescription, randomDate));
            // Set up the next loop.
            iterator = iterator.plusMinutes(30);
        }

        List<SlotInDay> slot = slotDao.getAllTasks();
        view.showSlotsInDay(slot);
    }

    @Override
    public void start() {

    }
}
