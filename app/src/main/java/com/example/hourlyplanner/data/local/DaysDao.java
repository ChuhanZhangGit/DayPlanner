package com.example.hourlyplanner.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hourlyplanner.data.Days;

import org.threeten.bp.LocalDate;

import java.util.List;

@Dao
public interface  DaysDao {

    @Query("SELECT * FROM days")
    List<Days> getAllDaysInDataBase();

    @Query("SELECT * FROM days WHERE date =:localDate")
    Days getDayByDate(LocalDate localDate);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNewDayToDataBase(Days days);

    @Update
    void update(Days days);

}
