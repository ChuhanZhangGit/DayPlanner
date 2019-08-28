package com.example.todolist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter {

    //to reference the Activity
    private final Activity context;

    private final String[] taskTimeArray;

    private final String[] taskContentArray;

    public CustomArrayAdapter(Activity context, String[] taskTimeArray, String[] taskContentArray) {

        super(context, R.layout.tasks_row, taskContentArray);
        this.context = context;
        this.taskTimeArray = taskTimeArray;
        this.taskContentArray = taskContentArray;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.tasks_row, null, true);
        TextView taskTimeView = rowView.findViewById(R.id.task_time);
        TextView taskContentView = rowView.findViewById(R.id.task_content);

        taskTimeView.setText(taskTimeArray[position]);
        taskContentView.setText(taskContentArray[position]);

        return rowView;

    }

    ;

}
