package com.example.dictionary.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Dictionary.class}, version = 1, exportSchema = false)
public abstract class DictionaryDB extends RoomDatabase {
    public abstract DictionaryDao dictionaryDao();


}
