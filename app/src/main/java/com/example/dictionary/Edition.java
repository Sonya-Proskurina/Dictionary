package com.example.dictionary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Edition extends AppCompatActivity {

    private static final String WORD = "Edition.WORD";


    Dictionary dictionary;
    Toolbar toolbar;
    EditText editTextEng;
    EditText editTextRus;
    Button button;

    public static void start(Activity activity, Dictionary dictionary) {
        Intent intent = new Intent(activity, Edition.class);
        if (dictionary!= null)
            intent.putExtra(WORD, dictionary);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);

//        toolbar =findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        setTitle("Редактирование словаря");

        editTextEng= findViewById(R.id.engE);
        editTextRus= findViewById(R.id.rusE);
        button= findViewById(R.id.buttonSave);

        if (getIntent().hasExtra(WORD)){
            dictionary =getIntent().getParcelableExtra(WORD);
            editTextEng.setText(dictionary.engWord);
            editTextRus.setText(dictionary.rusWord);
        }
        else{
         dictionary=   new Dictionary();
        }


    }

    public void save(View view) {
        if (editTextRus.getText().length()>0&&editTextEng.getText().length()>0){

           dictionary.engWord=editTextEng.getText().toString();
           dictionary.rusWord=editTextRus.getText().toString();
           if (getIntent().hasExtra(WORD)){
               App.getInstance().getDictionaryDao().update(dictionary);
           }else {
              App.getInstance().getDictionaryDao().insert(dictionary);
           }
           finish();
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Введите слово и его перевод!",
                    Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void end(View view) {
        finish();
    }
}
