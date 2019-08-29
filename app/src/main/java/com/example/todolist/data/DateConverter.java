package com.example.todolist.data;

import androidx.room.TypeConverter;

import java.time.LocalDate;

public class DateConverter {
    private LocalDate localDate =  LocalDate.of(1,2,2);
    @TypeConverter
    public static LocalDate fromTimestamp(Long value) {
        return value == null ? null : new LocalDate(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(LocalDate date) {
        return date == null ? null : date.getTime();
    }
}
}
