package com.example.todolist.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.threeten.bp.LocalTime;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM taskInDay")
    List<TaskInDay> getAllTasks();

    @Query("SELECT * FROM taskInDay WHERE timeInDay = :dateTime LIMIT 1")
    TaskInDay getTaskAtTime(LocalTime dateTime);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TaskInDay task);

    @Update
    void updateTaskInDay(TaskInDay task);

    @Query("DELETE FROM taskInDay WHERE ")
    void deleteTaskAtTime(LocalTime localTime);

    @Query("DELETE FROM taskInDay")
    void deleteAll();

}

