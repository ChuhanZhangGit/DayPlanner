package com.example.hourlyplanner.taskslot;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.hourlyplanner.R;
import com.google.android.material.appbar.AppBarLayout;

import org.threeten.bp.LocalDate;

public class SlotsListActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;

    private CalendarView calendarView;

    private boolean isExpanded = false;

    private LocalDate currentDate;

    private TextView selectedDateTextView;

    private SlotsListFragment slotListFragment;

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
        selectedDateTextView = findViewById(R.id.toolbar_date_display);
        currentDate = LocalDate.now();

        slotListFragment = (SlotsListFragment) getSupportFragmentManager().findFragmentById(R.id.frag_task_list);

        setToolbarDate();
        setupPickDateButton();
        setupCalendarListener();

        SlotsListFragment slotsListFragment = (SlotsListFragment) getSupportFragmentManager().
                findFragmentById(R.id.frag_task_list);

        if (slotsListFragment == null) {
            slotsListFragment = SlotsListFragment.newInstance();
        }
    }

    private void setupCalendarListener() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                 currentDate = LocalDate.of(i, i1+1, i2);
                 setToolbarDate();
                slotListFragment.setViewDate(currentDate);
            }
        });
    }

    private void setToolbarDate() {
        this.selectedDateTextView.setText(currentDate.toString());
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
