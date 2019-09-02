package com.example.hourlyplanner.taskslot;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hourlyplanner.R;
import com.example.hourlyplanner.data.PlannerRepository;
import com.example.hourlyplanner.data.local.PlannerDataBase;

public class SlotsListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        SlotsListFragment slotsListFragment = (SlotsListFragment) getSupportFragmentManager().
                findFragmentById(R.id.frag_task_list);

        if (slotsListFragment == null) {
            slotsListFragment = SlotsListFragment.newInstance();
        }



        // Initialize presenter, this is where dependency injection should be used.
//        slotsPresenter = new SlotsPresenter(PlannerRepository.getInstance(
//                PlannerDataBase.getDataBase(getApplicationContext())),
//                slotsListFragment);

    }

}
