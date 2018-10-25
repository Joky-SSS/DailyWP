package com.jokyxray.dailywp.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.jokyxray.dailywp.model.DailyImage;


@Database(entities = {DailyImage.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String dbName = "dailywp";
    private static AppDatabase INSTANCE;
    private static final Object sLock = new Object();
    public abstract DailyImageDao dailyDao();

    public static AppDatabase get(Context context){
        synchronized (sLock){
            if (INSTANCE == null) INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, dbName).build();
            return INSTANCE;
        }
    }
}
