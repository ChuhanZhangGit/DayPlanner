package com.example.todolist.task;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todolist.R;
import com.example.todolist.data.TaskInDay;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TasksListFragment extends Fragment {

    private TaskListAdapter myListAdpter;

    private EditText itemEntry;
    private Button addButton;
    private ListView taskListView;

    private ArrayList<TaskInDay> tasks;
    private ArrayAdapter<TaskInDay> taskInDayArrayAdapter;


    private Context context;

    String[] taskTimeArray = {"Octopus","Pig","Sheep","Rabbit","Snake","Spider" };

    String[] taskContentArray = {
            "8 tentacled monster",
            "Delicious in rolls",
            "Great for jumpers",
            "Nice in a stew",
            "Great for shoes",
            "Scary."};


    public TasksListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        taskInDayArrayAdapter = new TaskListAdapter(context, taskTimeArray, taskContentArray);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tasks_list, container, false);
        ListView listView = root.findViewById(R.id.task_list_view);

        listView.setAdapter(taskInDayArrayAdapter);

        return root;
    }

    private class TaskListAdapter extends ArrayAdapter {

        //to reference the Activity
        private final Context context;

        private final String[] taskTimeArray;

        private final String[] taskContentArray;

        public TaskListAdapter(Context context, String[] taskTimeArray, String[] taskContentArray) {

            super(context, R.layout.tasks_row, taskContentArray);
            this.context = context;
            this.taskTimeArray = taskTimeArray;
            this.taskContentArray = taskContentArray;
        }

        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View rowView = inflater.inflate(R.layout.tasks_row, null, true);
            TextView taskTimeView = rowView.findViewById(R.id.task_time);
            TextView taskContentView = rowView.findViewById(R.id.task_content);

            taskTimeView.setText(taskTimeArray[position]);
            taskContentView.setText(taskContentArray[position]);

            return rowView;

        }


    }
}


