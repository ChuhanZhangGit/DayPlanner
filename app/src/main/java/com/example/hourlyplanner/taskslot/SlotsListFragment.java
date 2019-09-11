package com.example.hourlyplanner.taskslot;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProviders;

import com.example.hourlyplanner.R;
import com.example.hourlyplanner.data.ConstantSlot;
import com.example.hourlyplanner.data.SlotInDay;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SlotsListFragment extends Fragment {

    private SlotListAdapter slotInDayAdapter;

    private SlotsListViewModel slotsViewModel;

    private MutableLiveData<LocalDate> dateLiveData;

    private ListView slotsListView;


    private Context context;


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
        slotInDayAdapter = new SlotListAdapter(context, new ArrayList<SlotInDay>(),
                new ArrayList<ConstantSlot>());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        slotsViewModel = ViewModelProviders.of(this).get(SlotsListViewModel.class);


        // Switch map that reacts on changes of trigger (Local date) and apply the function to the
        // given value of the trigger and set the result live data
        dateLiveData = new MutableLiveData<>();

        // Set the trigger point to current date
        dateLiveData.setValue(LocalDate.now());

        LiveData<List<SlotInDay>> slotListLiveData = Transformations.switchMap(dateLiveData,
                new Function<LocalDate, LiveData<List<SlotInDay>>>() {
                    @Override
                    public LiveData<List<SlotInDay>> apply(LocalDate date) {
                        return slotsViewModel.getAllSlotsInDay(date);
                    }
                });

        slotListLiveData.observe(this, new Observer<List<SlotInDay>>() {
            @Override
            public void onChanged(List<SlotInDay> slotInDays) {
                slotInDayAdapter.updateSlotList(slotInDays);
            }
        });

        slotsViewModel.getConstantSlots().observe(this, new Observer<List<ConstantSlot>>() {
            @Override
            public void onChanged(List<ConstantSlot> constantSlots) {
                slotInDayAdapter.updateConstantSlots(constantSlots);
            }
        });
    }

    public void setViewDate(LocalDate date) {
        dateLiveData.setValue(date);
//        writeSlotChangesToDB();
    }

    private void writeSlotChangesToDB() {
        int childrenCount = slotsListView.getChildCount();
        for (int i = 0; i < childrenCount; i++) {
            View slot = slotsListView.getChildAt(i);
            String slotContent = ((EditText) slotsListView.findViewById(R.id.task_content))
                    .getText().toString();
            if (slotContent.length() != 0) {
                // Insert day first into DB because foreign key constrain between day and slot
                slotsViewModel.insertDay(dateLiveData.getValue());
                List<ConstantSlot> constantSlots = slotInDayAdapter.getConstantSlots();
                slotsViewModel.insertSlotInDay(dateLiveData.getValue(),
                        constantSlots.get(i).getTimeSlot(), slotContent);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tasks_list, container, false);
        slotsListView = root.findViewById(R.id.task_list_view);

        slotsListView.setAdapter(slotInDayAdapter);

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
        private List<ConstantSlot> constantSlots;

        public SlotListAdapter(Context context, List<SlotInDay> slotList,
                               List<ConstantSlot> constantSlots) {
            this.slotContext = context;
            this.slotList = slotList;
            this.constantSlots = constantSlots;
        }

        public void updateSlotList(List<SlotInDay> slots) {
            slotList = slots;
            notifyDataSetChanged();
        }

        public void updateConstantSlots(List<ConstantSlot> constantSlots) {
            this.constantSlots = constantSlots;
            notifyDataSetChanged();
        }

        public List<ConstantSlot> getConstantSlots() {
            return constantSlots;
        }

        @Override
        public int getCount() {
            return constantSlots.size();
        }

        @Override
        public ConstantSlot getItem(int i) {
            return constantSlots.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(slotContext);
            View rowView = inflater.inflate(R.layout.slot_row, null, true);
            TextView slotTimeView = rowView.findViewById(R.id.slot_time);
            EditText slotContentView = rowView.findViewById(R.id.task_content);
            ConstantSlot constantSlot = constantSlots.get(position);
            slotTimeView.setText(constantSlot.getTimeSlot().toString());

            if (slotList.size() == 0) {
                slotContentView.setText("");
            } else {
                SlotInDay slot = slotList.get(position);
                slotContentView.setText(slot.getTaskDescription());
            }
            return rowView;

        }

    }
}


