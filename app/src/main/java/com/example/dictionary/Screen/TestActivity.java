package com.example.dictionary.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.SortedList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dictionary.DataBase.Dictionary;
import com.example.dictionary.R;
import com.rengwuxian.materialedittext.MaterialEditText;

public class TestActivity extends AppCompatActivity {
    SortedList<Dictionary> sortedList;
    Button button;
    TextView word;
    MaterialEditText editText;
    private TextToSpeech TTS;

    int num;// индекс слова в списке
    int lesson;// подтип задания
    boolean buttonOn; //кнопка в режиме проверки= 1, кнопка в режиме следующего слова=0
    Dictionary myWord; //проверяемая пара слов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().hide();

        word = findViewById(R.id.Question);
        button = findViewById(R.id.buttonQuestion);
        sortedList = Adapter.sortedList;
        editText = findViewById(R.id.Answer);
        buttonOn = true;

        TTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });
        newWor();

    }

    public void extTest(View view) {
        finish();
    }

    int random(int size) {
        return (int) (Math.random() * (size));
    }

    public void butTest2(View view) {
        if (buttonOn) {
            if (lesson == 0) {
                if (editText.getText().toString().equalsIgnoreCase(sortedList.get(num).engWord)) {
                    button.setBackgroundResource(R.drawable.button_green);
                    button.setText("Продолжить");
                    buttonOn = false;
                }
            } else {
                if (editText.getText().toString().equalsIgnoreCase(sortedList.get(num).rusWord)) {
                    button.setBackgroundResource(R.drawable.button_green);
                    button.setText("Продолжить");
                    buttonOn = false;
                }
            }
        } else {
            newWor();
        }
    }

    public void butSkip(View view) {
        newWor();

    }

    void newWor() {
        button.setBackgroundResource(R.drawable.button);
        button.setText("Проверить");
        buttonOn = true;
        editText.setText("");
        num = random(sortedList.size() - 1);
        lesson = random(2);
        if (lesson == 0)
            word.setText(sortedList.get(num).rusWord);
        else
            word.setText(sortedList.get(num).engWord);
    }
}