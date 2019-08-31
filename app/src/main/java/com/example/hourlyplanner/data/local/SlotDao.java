package com.example.hourlyplanner.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hourlyplanner.data.SlotInDay;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;

import java.util.List;

@Dao
public interface SlotDao {

    @Query("SELECT * FROM slotInDay")
    List<SlotInDay> getAllTasks();


    @Query("SELECT * FROM slotInDay WHERE dateOfTask =:date")
    List<SlotInDay> getSlotsInDay(LocalDate date);

    @Query("SELECT * FROM slotInDay WHERE timeInDay = :dateTime AND dateOfTask =:slotDate LIMIT 1")
    SlotInDay getTaskAtTime(LocalTime dateTime, LocalDate slotDate);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SlotInDay slot);

    @Update
    void updateTaskInDay(SlotInDay task);

    @Query("DELETE FROM slotInDay WHERE timeInDay =:localTime")
    void deleteTaskAtTime(LocalTime localTime);

    @Query("DELETE FROM slotInDay")
    void deleteAll();

}

