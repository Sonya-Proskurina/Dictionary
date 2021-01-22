package com.example.dictionary.screen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.App;
import com.example.dictionary.R;
import com.example.dictionary.model.Dictionary;
import com.example.dictionary.screen.MainViewModel;
import com.example.dictionary.screen.adapter.Adapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        final Adapter adapter = new Adapter();
        recyclerView.setAdapter(adapter);
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getData().observe(this, adapter::setItems);
    }

    @OnClick(R.id.button)
    public void but(View view) {
        EditionActivity.start(MainActivity.this, null);
    }

    @OnClick(R.id.buttonTest)
    public void butTest(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    public static void insertThread(final Dictionary dictionary) {
        new Thread() {
            @Override
            public void run() {
                App.getInstance().getDictionaryDao().insert(dictionary);
            }
        }.start();
    }

    public static void updateThread(final Dictionary dictionary) {
        new Thread() {
            @Override
            public void run() {
                App.getInstance().getDictionaryDao().update(dictionary);
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
}
