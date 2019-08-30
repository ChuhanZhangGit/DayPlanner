package com.example.hourlyplanner.taskslot;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hourlyplanner.R;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {

    private EditText itemEntry;
    private Button addButton;
    private ListView taskListView;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;


    String[] taskTimeArray = {"Octopus","Pig","Sheep","Rabbit","Snake","Spider" };

    String[] taskContentArray = {
            "8 tentacled monster",
            "Delicious in rolls",
            "Great for jumpers",
            "Nice in a stew",
            "Great for shoes",
            "Scary."
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tasks);
//
//        itemEntry = findViewById(R.id.item_edit_text);
//        addButton = findViewById(R.id.add_button);
//        itemList = findViewById(R.id.item_list);
//
//        items = FileHelper.readData(this);
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
//        itemList.setAdapter(adapter);
//
//        itemList.setOnItemClickListener(this);
//        addButton.setOnClickListener(this);
//    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tasks);
//
//        itemEntry = findViewById(R.id.item_edit_text);
//        addButton = findViewById(R.id.add_button);
//        taskListView = findViewById(R.id.task_list_view);
//        arrayAdapter = new CustomArrayAdapter(this, taskTimeArray, taskContentArray);
//
//        taskListView.setAdapter(arrayAdapter);
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        items.remove(i);
//        adapter.notifyDataSetChanged();
//        FileHelper.writeEntry(items, this);
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.add_button:
//                String itemEntered = itemEntry.getText().toString();
//                adapter.add(itemEntered);
//                itemEntry.setText("");
//                FileHelper.writeEntry(items, TasksActivity.this);
//
//                Toast.makeText(TasksActivity.this, "Item added", Toast.LENGTH_SHORT).show();
//                adapter.notifyDataSetChanged();
//        }
//    }
}
