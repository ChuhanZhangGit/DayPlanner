package com.example.hourlyplanner.taskslot;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.hourlyplanner.R;
import com.google.android.material.appbar.AppBarLayout;


public class SlotsListActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;

    private CalendarView calendarView;

    private boolean isExpanded = false;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        appBarLayout = findViewById(R.id.app_bar_layout);
        calendarView = findViewById(R.id.calendar_view);


        setupPickDateButton();

        SlotsListFragment slotsListFragment = (SlotsListFragment) getSupportFragmentManager().
                findFragmentById(R.id.frag_task_list);

        if (slotsListFragment == null) {
            slotsListFragment = SlotsListFragment.newInstance();
        }

    }

    private void setupPickDateButton() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        LinearLayout pickDateButton = findViewById(R.id.pick_date_button);
        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isExpanded = !isExpanded;
                appBarLayout.setExpanded(isExpanded,true);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
