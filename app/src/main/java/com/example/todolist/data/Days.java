package com.example.todolist.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDate;


@Entity(tableName = "days")
public class Days {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "date")
    private final LocalDate date;

    @ColumnInfo(name = "numTaskCompleted")
    @Nullable
    private int numCompleted;

    public Days(@NonNull LocalDate date) {
        this.date = date;
    }

    @NonNull
    public LocalDate getDate() {
        return date;
    }

    public int getTasksCompleted() {
        return numCompleted;
    }
}
