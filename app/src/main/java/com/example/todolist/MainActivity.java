package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private EditText itemEntry;
    private Button addButton;
    private ListView itemList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemEntry = findViewById(R.id.item_edit_text);
        addButton = findViewById(R.id.add_button);
        itemList = findViewById(R.id.item_list);

        items = FileHelper.readData(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        itemList.setAdapter(adapter);

        itemList.setOnItemClickListener(this);
        addButton.setOnClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        items.remove(i);
        adapter.notifyDataSetChanged();
        FileHelper.writeEntry(items, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_button:
                String itemEntered = itemEntry.getText().toString();
                adapter.add(itemEntered);
                itemEntry.setText("");
                FileHelper.writeEntry(items, MainActivity.this);

                Toast.makeText(MainActivity.this, "Item added", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
        }
    }
}
