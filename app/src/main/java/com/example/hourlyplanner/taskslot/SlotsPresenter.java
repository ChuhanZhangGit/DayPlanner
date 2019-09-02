package com.example.hourlyplanner.taskslot;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.example.hourlyplanner.data.PlannerRepository;
import com.example.hourlyplanner.data.SlotInDay;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;

import java.util.List;

public class SlotsPresenter implements SlotsContract.Presenter {

    private final PlannerRepository plannerRepo;
    private final SlotsListFragment view;


    public SlotsPresenter(PlannerRepository plannerRepo, SlotsListFragment view) {
        this.plannerRepo = plannerRepo;
        this.view = view;

        view.setPresenter(this);
    }



    @Override
    public void loadSlotsInDay() {


        LocalDate someDate = LocalDate.of(2019, 1,1);
        String taskDescription = "random task";
        LocalTime start = LocalTime.of( 8,30,0) ;
        LocalTime stop = LocalTime.of( 17 , 0 , 0 ) ;

        plannerRepo.insertDate(someDate);

        LocalTime iterator = start;
        while ( iterator.isBefore( stop ) ) {
            plannerRepo.insertTimeSlot(new SlotInDay(iterator, taskDescription, someDate));
            // Set up the next loop.
            iterator = iterator.plusMinutes(30);
        }

        plannerRepo.getAllSlotsInDay(someDate).observe(view, new Observer<List<SlotInDay>>() {
            @Override
            public void onChanged(List<SlotInDay> slot) {
                view.showSlotsInDay(slot);
            }
        });
        Log.i("presenter", "showslot");
    }

    @Override
    public void start() {

    }
}
