package com.example.hourlyplanner.data.local;

import androidx.room.TypeConverter;

import org.threeten.bp.LocalDate;


public class DateConverter {
    @TypeConverter
    public static LocalDate fromString (String dateString) {
        return dateString == null ? null : LocalDate.parse(dateString);
    }

    @TypeConverter
    public static String fromDate(LocalDate date) {
        return date == null ? null : date.toString();
    }

}
