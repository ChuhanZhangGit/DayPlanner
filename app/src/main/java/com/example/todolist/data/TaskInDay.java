package com.example.todolist.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;


@Entity(tableName = "taskInDay",
        foreignKeys = @ForeignKey(entity = Days.class, parentColumns = "date",
                childColumns = "dateOfTask", onDelete = ForeignKey.CASCADE))
public class TaskInDay {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "timeInDay")
    private LocalTime taskTime;


    @Nullable
    @ColumnInfo(name = "taskDescription")
    private String taskDescription;

    @ColumnInfo(name = "dateOfTask")
    private LocalDate taskDate;

    public TaskInDay(@NonNull LocalTime taskTime, @Nullable String taskDescription,
                     LocalDate taskDate) {
        this.taskTime = taskTime;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
    }

    @NonNull
    public LocalTime getTaskTime() {
        return taskTime;
    }

    @Nullable
    public String getTaskDescription() {
        return taskDescription;
    }
}
