package com.example.hourlyplanner.taskslot;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hourlyplanner.R;
import com.example.hourlyplanner.data.PlannerDataBase;

public class SlotsListActivity extends AppCompatActivity {

    private SlotsPresenter slotsPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        SlotsListFragment slotsListFragment = (SlotsListFragment) getSupportFragmentManager().
                findFragmentById(R.id.frag_task_list);

        if (slotsListFragment == null) {
            slotsListFragment = SlotsListFragment.newInstance();
        }

        // Initialize presenter
        slotsPresenter = new SlotsPresenter(PlannerDataBase.getDataBase(getApplicationContext()),
                slotsListFragment);

    }

}
