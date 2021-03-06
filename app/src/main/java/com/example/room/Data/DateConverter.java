package com.example.room.Data;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        if (timestamp != null) {
            return new Date(timestamp);
        } else {
            return null;
        }
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        if (date != null) {
            return date.getTime();
        } else {
            return null;
        }
    }
}
