package com.example.hourlyplanner.data;

import androidx.room.TypeConverter;

import org.threeten.bp.LocalDate;


public class DateConverter {
    @TypeConverter
    public static LocalDate fromTimestamp(String dateString) {
        return dateString == null ? null : LocalDate.parse(dateString);
    }

    @TypeConverter
    public static String dateToTimestamp(LocalDate date) {
        return date == null ? null : date.toString();
    }

}
