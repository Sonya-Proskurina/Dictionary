package com.example.dictionary.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dictionary.DataBase.App;
import com.example.dictionary.DataBase.Dictionary;
import com.example.dictionary.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        final Adapter adapter = new Adapter();
        recyclerView.setAdapter(adapter);
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getData().observe(this, new Observer<List<Dictionary>>() {
            @Override
            public void onChanged(List<Dictionary> dictionaries) {
                adapter.setItems(dictionaries);
            }
        });
    }

    public void but(View view) {
        EditionActivity.start(MainActivity.this, null);
    }

//    public void UIThread(Activity activity) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//    }

    public static void updateThread(final Dictionary dictionary) {
        new Thread() {
            @Override
            public void run() {
                App.getInstance().getDictionaryDao().update(dictionary);
            }
        }.start();
    }
    public static void insertThread(final Dictionary dictionary) {
        new Thread() {
            @Override
            public void run() {
                App.getInstance().getDictionaryDao().insert(dictionary);
            }
        }.start();
    }
    public static void deleteThread(final Dictionary dictionary) {
        new Thread() {
            @Override
            public void run() {
                App.getInstance().getDictionaryDao().delete(dictionary);
            }
        }.start();
    }

    public void butTest(View view) {
        Intent intent = new Intent(this,TestActivity.class);
        startActivity(intent);
    }
}
