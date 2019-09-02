package com.example.hourlyplanner.taskslot;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hourlyplanner.R;
import com.example.hourlyplanner.data.SlotInDay;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SlotsListFragment extends Fragment  {

    private EditText itemEntry;
    private Button addButton;
    private ListView taskListView;

    private SlotListAdapter slotInDayAdapter;

    private SlotsListViewModel slotsViewModel;

    private Context context;

    String[] taskTimeArray = {"Octopus", "Pig", "Sheep", "Rabbit", "Snake", "Spider"};

    String[] taskContentArray = {
            "8 tentacled monster",
            "Delicious in rolls",
            "Great for jumpers",
            "Nice in a stew",
            "Great for shoes",
            "Scary."};


    public SlotsListFragment() {
        // Required empty public constructor
    }

    public static SlotsListFragment newInstance() {
        return new SlotsListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        slotInDayAdapter = new SlotListAdapter(context, new ArrayList<SlotInDay>());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        slotsViewModel = ViewModelProviders.of(this).get(SlotsListViewModel.class);

        LocalDate someDate = LocalDate.now();
        slotsViewModel.getAllSlotsInDay(someDate).observe(this, new Observer<List<SlotInDay>>() {
            @Override
            public void onChanged(List<SlotInDay> slotInDays) {
                slotInDayAdapter.updateList(slotInDays);
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tasks_list, container, false);
        ListView listView = root.findViewById(R.id.task_list_view);

        listView.setAdapter(slotInDayAdapter);

        return root;
    }

    private void updateListView() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private class SlotListAdapter extends BaseAdapter {

        //to reference the Activity
        private final Context slotContext;
        private List<SlotInDay> slotList;

        public SlotListAdapter(Context context, List<SlotInDay> slotList){
            this.slotContext = context;
            this.slotList = slotList;
        }

        public void updateList(List<SlotInDay> slots) {
            slotList = slots;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return slotList.size();
        }

        @Override
        public SlotInDay getItem(int i) {
            return slotList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(slotContext);
            View rowView = inflater.inflate(R.layout.slot_row, null, true);
            TextView taskTimeView = rowView.findViewById(R.id.slot_time);
            TextView taskContentView = rowView.findViewById(R.id.task_content);

            SlotInDay slot = slotList.get(position);

            taskTimeView.setText(slot.getSlotTime().toString());
            taskContentView.setText(slot.getTaskDescription());
            return rowView;

        }

    }
}


