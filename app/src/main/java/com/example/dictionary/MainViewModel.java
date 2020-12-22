package com.example.dictionary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {
private LiveData<List<Dictionary>> data = App.getInstance().getDictionaryDao().getAllLiveData();

    public LiveData<List<Dictionary>> getData() {
        return data;
    }
}
