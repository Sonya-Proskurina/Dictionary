package com.example.dictionary.Screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dictionary.DataBase.App;
import com.example.dictionary.DataBase.Dictionary;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Dictionary>> data = App.getInstance().getDictionaryDao().getAllLiveData();

    public LiveData<List<Dictionary>> getData() {
        return data;
    }
}
