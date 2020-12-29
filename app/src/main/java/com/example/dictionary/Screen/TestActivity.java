package com.example.dictionary.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.SortedList;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dictionary.DataBase.Dictionary;
import com.example.dictionary.R;
import com.rengwuxian.materialedittext.MaterialEditText;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    static SortedList<Dictionary> sortedList;
    //    Button buttonChecking;
    Button buttonExit;
    static Button buttonSkip;
    ImageView imageView;
    TextView textView;
    //    TextView word;
//    MaterialEditText editText;
    private TextToSpeech TTS;

    static int num;// индекс слова в списке
    static int lesson;// подтип задания
    static boolean buttonOn; //кнопка в режиме проверки= 1, кнопка в режиме следующего слова=0
    Dictionary myWord; //проверяемая пара слов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().hide();

//        word = findViewById(R.id.Question);
//        buttonChecking = findViewById(R.id.buttonQuestion);
        buttonExit = findViewById(R.id.buttonExit);
        buttonSkip = findViewById(R.id.buttonSkip);
        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.textTest);
//        buttonChecking.setOnClickListener(this);
        buttonExit.setOnClickListener(this);
        buttonSkip.setOnClickListener(this);
//        imageView.setOnClickListener(this);
        sortedList = Adapter.sortedList;
//        editText = findViewById(R.id.Answer);
        buttonOn = true;

//        TTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//
//            }
//        });
        newWor();

    }


    int random(int size) {
        return (int) (Math.random() * (size));
    }

    void newWor() {
        buttonSkip.setBackgroundResource(R.drawable.button);
        buttonSkip.setText("Пропустить");
        buttonOn = true;
//        editText.setText("");
        num = random(sortedList.size() - 1);
        lesson = random(3);
//        if (lesson == 0)
//            word.setText(sortedList.get(num).rusWord);
//        else
//            word.setText(sortedList.get(num).engWord);
        if (lesson == 0 || lesson == 1) {
            textView.setText("Введите перевод");
            loadFragment(new FragmentTest1());
        } else if (lesson == 2) {
            textView.setText("Введите слово, чтобы прослушать нажмите на картинку");
            loadFragment(new FragmentTest2());
        }
    }

    void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonExit) {
            finish();
        }
        if (v.getId() == R.id.buttonSkip) {
//            if (buttonOn) {
                newWor();
//            }
//            else {
//               resume();
//            }

        }
//        if (v.getId()==R.id.buttonQuestion){
//            if (buttonOn) {
//                if (lesson == 0) {
//                    if (editText.getText().toString().equalsIgnoreCase(sortedList.get(num).engWord)) {
//                        buttonChecking.setBackgroundResource(R.drawable.button_green);
//                        buttonChecking.setText("Продолжить");
//                        buttonOn = false;
//                    }
//                } else {
//                    if (editText.getText().toString().equalsIgnoreCase(sortedList.get(num).rusWord)) {
//                        buttonChecking.setBackgroundResource(R.drawable.button_green);
//                        buttonChecking.setText("Продолжить");
//                        buttonOn = false;
//                    }
//                }
//            } else {
//                newWor();
//            }
//        }
//        if (v.getId()==R.id.image){
//
//        }
    }

}