package com.example.dictionary.Screen.Screens;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.SortedList;

import com.example.dictionary.DataBase.Dictionary;
import com.example.dictionary.R;
import com.example.dictionary.Screen.Adapter;
import com.example.dictionary.Screen.Fragmants.FragmentTest1;
import com.example.dictionary.Screen.Fragmants.FragmentTest2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.buttonSkip)
    public Button buttonSkip;
    @BindView(R.id.textTest)
    public TextView textView;

    public static SortedList<Dictionary> sortedList;
    public static int num;// индекс слова в списке
    public static int lesson;// подтип задания
    public static boolean buttonOn; //кнопка в режиме проверки= 1, кнопка в режиме следующего слова=0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().hide();

        ButterKnife.bind(this);
        sortedList = Adapter.sortedList;

        newWor();
    }

    private int random(int size) {
        return (int) (Math.random() * (size));
    }

    private void newWor() {
        buttonSkip.setBackgroundResource(R.drawable.button);
        buttonSkip.setText("Пропустить");
        buttonOn = true;
        num = random(sortedList.size() - 1);
        lesson = random(3);
        if (lesson == 0 || lesson == 1) {
            textView.setText("Введите перевод");
            loadFragment(new FragmentTest1());
        } else if (lesson == 2) {
            textView.setText("Введите слово, чтобы прослушать нажмите на картинку");
            loadFragment(new FragmentTest2());
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @OnClick(R.id.buttonExit)
    public void exit() {
        finish();
    }

    @OnClick(R.id.buttonSkip)
    public void skip() {
        newWor();
    }
}