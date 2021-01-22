package com.example.dictionary.screen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dictionary.R;
import com.example.dictionary.model.Dictionary;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditionActivity extends AppCompatActivity {

    private static final String WORD = "Edition.WORD";

    private Dictionary dictionary;

    @BindView(R.id.engE)
    EditText editTextEng;
    @BindView(R.id.rusE)
    EditText editTextRus;

    // TODO EXPLAIN WHAT IT DOES
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

        ButterKnife.bind(this);

        if (getIntent().hasExtra(WORD)) {
            dictionary = getIntent().getParcelableExtra(WORD);
            editTextEng.setText(dictionary.getEngWord());
            editTextRus.setText(dictionary.getRusWord());
        } else {
            dictionary = new Dictionary();
        }
    }

    @OnClick(R.id.buttonSave)
    public void save(View view) {
        if (editTextRus.getText().length() > 0 && editTextEng.getText().length() > 0) {

            dictionary.setEngWord(editTextEng.getText().toString());
            dictionary.setRusWord(editTextRus.getText().toString());
            if (getIntent().hasExtra(WORD)) {
                MainActivity.updateThread(dictionary);
            } else {
                MainActivity.insertThread(dictionary);
            }
            finish();
        } else {
            Toast toast = Toast.makeText(this, "Введите слово и его перевод!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @OnClick(R.id.buttonEnd)
    public void end(View view) {
        finish();
    }
}
