package com.example.dictionary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DictionaryDao {

    @Query("SELECT * FROM Dictionary")
    List<Dictionary> getAll();

    @Query("SELECT * FROM Dictionary")
    LiveData<List<Dictionary>> getAllLiveData();

    @Query("SELECT * FROM Dictionary WHERE id IN (:noteIds)")
    List<Dictionary> loadAllByIds(int[] noteIds);

    @Query("SELECT * FROM Dictionary WHERE id = :id LIMIT 1")
    Dictionary findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Dictionary note);

    @Update
    void update(Dictionary note);

    @Delete
    void delete(Dictionary note);


}
