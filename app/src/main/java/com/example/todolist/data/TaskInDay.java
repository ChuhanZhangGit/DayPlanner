package com.example.todolist.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(tableName = "taskInDay",
        foreignKeys = @ForeignKey(entity = Days.class, parentColumns = "date",
                childColumns = "dateOfTask", onDelete = ForeignKey.CASCADE))
public class TaskInDay {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "timeInDay")
    private LocalDateTime taskTime;


    @Nullable
    @ColumnInfo(name = "taskDescription")
    private String taskDescription;

    @ColumnInfo(name = "dateOfTask")
    private LocalDate taskDate;

    public TaskInDay(@NonNull LocalDateTime taskTime, @Nullable String taskDescription,
                     LocalDate taskDate) {
        this.taskTime = taskTime;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
    }

    @NonNull
    public LocalDateTime getTaskTime() {
        return taskTime;
    }

    @Nullable
    public String getTaskDescription() {
        return taskDescription;
    }
}
