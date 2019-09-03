package com.example.hourlyplanner.taskslot;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NavigationRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.hourlyplanner.R;
import com.example.hourlyplanner.data.PlannerRepository;
import com.example.hourlyplanner.data.local.PlannerDataBase;



public class SlotsListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        setupToolBar();

        SlotsListFragment slotsListFragment = (SlotsListFragment) getSupportFragmentManager().
                findFragmentById(R.id.frag_task_list);

        if (slotsListFragment == null) {
            slotsListFragment = SlotsListFragment.newInstance();
        }

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
