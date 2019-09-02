package com.example.hourlyplanner.data.local;

import androidx.room.TypeConverter;

import org.threeten.bp.LocalTime;

public class LocalTimeConverter {
    @TypeConverter
    public static LocalTime fromString (String timeString) {
        return timeString == null ? null : LocalTime.parse(timeString);
    }

    @TypeConverter
    public static String fromTime (LocalTime time) {
        return time == null ? null : time.toString();
    }
}
