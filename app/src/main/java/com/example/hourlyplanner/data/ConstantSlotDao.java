package com.example.hourlyplanner.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;

import java.util.List;

@Dao
public interface ConstantSlotDao {
    @Query("SELECT * FROM constantSlots")
    LiveData<List<ConstantSlot>> getAllConstantSlot();


    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(ConstantSlot slot);


    @Query("DELETE FROM constantSlots")
    void deleteAll();
}
