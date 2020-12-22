package com.example.dictionary.DataBase;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.room.Room;

public class App extends Application {

    private DictionaryDB dictionaryDB;
    private DictionaryDao dictionaryDao;
    public static App instance;

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

    public DictionaryDB getDictionaryDB() {
        return dictionaryDB;
    }

    public void setDictionaryDB(DictionaryDB dictionaryDB) {
        this.dictionaryDB = dictionaryDB;
    }

    public DictionaryDao getDictionaryDao() {
        return dictionaryDao;
    }

    public void setDictionaryDao(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

}
