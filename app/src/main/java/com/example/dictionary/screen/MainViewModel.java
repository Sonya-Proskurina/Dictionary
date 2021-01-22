package com.example.dictionary.screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dictionary.App;
import com.example.dictionary.model.Dictionary;

import java.util.List;

public class MainViewModel extends ViewModel {

    private LiveData<List<Dictionary>> data = App.getInstance().getDictionaryDao().getAllLiveData();

    public LiveData<List<Dictionary>> getData() {
        return data;
    }
}
