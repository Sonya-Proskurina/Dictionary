package com.example.dictionary.Screen.Screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dictionary.DataBase.Dictionary;
import com.example.dictionary.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditionActivity extends AppCompatActivity {

    private static final String WORD = "Edition.WORD";

    Dictionary dictionary;
    @BindView(R.id.engE)
    EditText editTextEng;
    @BindView(R.id.rusE)
    EditText editTextRus;

    public static void start(Activity activity, Dictionary dictionary) {
        Intent intent = new Intent(activity, EditionActivity.class);
        if (dictionary != null)
            intent.putExtra(WORD, dictionary);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);
        getSupportActionBar().hide();

        ButterKnife.bind(this);

        if (getIntent().hasExtra(WORD)) {
            dictionary = getIntent().getParcelableExtra(WORD);
            editTextEng.setText(dictionary.engWord);
            editTextRus.setText(dictionary.rusWord);
        } else {
            dictionary = new Dictionary();
        }
    }

    @OnClick(R.id.buttonSave)
    public void save(View view) {
        if (editTextRus.getText().length() > 0 && editTextEng.getText().length() > 0) {

            dictionary.engWord = editTextEng.getText().toString();
            dictionary.rusWord = editTextRus.getText().toString();
            if (getIntent().hasExtra(WORD)) {
                MainActivity.updateThread(dictionary);
            } else {
                MainActivity.insertThread(dictionary);
            }
            finish();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Введите слово и его перевод!",
                    Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @OnClick(R.id.buttonEnd)
    public void end(View view) {
        finish();
    }
}
