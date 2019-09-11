package com.example.hourlyplanner.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.hourlyplanner.data.ConstantSlot;

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
