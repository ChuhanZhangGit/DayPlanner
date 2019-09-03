package com.example.hourlyplanner.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalTime;

@Entity(tableName = "constantSlots")
public class ConstantSlot {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "timeSlot")
    private LocalTime timeSlot;

    public ConstantSlot(LocalTime timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getTimeSlot() {
        return timeSlot;
    }
}
