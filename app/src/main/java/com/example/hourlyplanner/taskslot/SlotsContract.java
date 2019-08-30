package com.example.hourlyplanner.taskslot;

import com.example.hourlyplanner.BaseFragment;
import com.example.hourlyplanner.BasePresenter;
import com.example.hourlyplanner.data.SlotInDay;

import java.util.List;

public interface SlotsContract {

    interface Fragment extends BaseFragment<Presenter> {
        void showSlotsInDay(List<SlotInDay> tasks);

    }

    interface Presenter extends BasePresenter {
        void loadSlotsInDay();
    }

}
