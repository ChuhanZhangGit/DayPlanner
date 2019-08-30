package com.example.hourlyplanner.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.threeten.bp.LocalTime;

import java.util.List;

@Dao
public interface SlotDao {

    @Query("SELECT * FROM slotInDay")
    List<SlotInDay> getAllTasks();

    @Query("SELECT * FROM slotInDay WHERE timeInDay = :dateTime LIMIT 1")
    SlotInDay getTaskAtTime(LocalTime dateTime);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SlotInDay task);

    @Update
    void updateTaskInDay(SlotInDay task);

    @Query("DELETE FROM slotInDay WHERE timeInDay =:localTime")
    void deleteTaskAtTime(LocalTime localTime);

    @Query("DELETE FROM slotInDay")
    void deleteAll();

}

