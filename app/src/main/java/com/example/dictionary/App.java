package com.example.dictionary;

import android.app.Application;

import androidx.room.Room;

import com.example.dictionary.room.DictionaryDB;
import com.example.dictionary.room.DictionaryDao;

import lombok.Getter;

@Getter
public class App extends Application {

    public static App instance;

    private DictionaryDB dictionaryDB;
    private DictionaryDao dictionaryDao;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        dictionaryDB = Room.databaseBuilder(getApplicationContext(),
                DictionaryDB.class, "db")
//                .allowMainThreadQueries()
                .build();

        dictionaryDao = dictionaryDB.dictionaryDao();
    }
}
