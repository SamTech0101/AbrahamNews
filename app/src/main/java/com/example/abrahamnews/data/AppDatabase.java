package com.example.abrahamnews.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = News.class,version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LocalDataSource getLocalDataSource();
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance==null){
            instance= Room.databaseBuilder(context,AppDatabase.class,"RanndomNews_db").allowMainThreadQueries().build();

        }
        return instance;
    }
}
