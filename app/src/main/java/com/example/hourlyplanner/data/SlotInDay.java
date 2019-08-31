package com.example.hourlyplanner.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;


@Entity(tableName = "slotInDay",
        foreignKeys = @ForeignKey(entity = Days.class, parentColumns = "date",
                childColumns = "dateOfTask", onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = "dateOfTask", unique = true, name = "slotDayIndex")})
public class SlotInDay {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "timeInDay")
    private LocalTime slotTime;

    @Nullable
    @ColumnInfo(name = "taskDescription")
    private String taskDescription;

    @NonNull
    @ColumnInfo(name = "dateOfTask")
    private LocalDate slotDate;

    public SlotInDay(@NonNull LocalTime slotTime, @Nullable String taskDescription,
                     LocalDate slotDate) {
        this.slotTime = slotTime;
        this.taskDescription = taskDescription;
        this.slotDate = slotDate;
    }

    @NonNull
    public LocalTime getSlotTime() {
        return slotTime;
    }
    

    @NonNull
    public LocalDate getSlotDate() {
        return slotDate;
    }

    @Nullable
    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(@Nullable String taskDescription) {
        this.taskDescription = taskDescription;
    }


}
