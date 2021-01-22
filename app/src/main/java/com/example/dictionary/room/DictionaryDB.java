package com.example.dictionary.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dictionary.model.Dictionary;

@Database(entities = {Dictionary.class}, version = 1, exportSchema = false)
public abstract class DictionaryDB extends RoomDatabase {

    public abstract DictionaryDao dictionaryDao();
}
