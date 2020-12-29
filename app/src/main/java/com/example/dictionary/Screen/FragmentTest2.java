package com.example.dictionary.Screen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionary.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Locale;

public class FragmentTest2 extends Fragment implements View.OnClickListener {

    static Button buttonChecking;
    static Button buttonSkip;
    MaterialEditText editText;
    ImageView imageView;
    TextToSpeech textToSpeech;

    public FragmentTest2() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test2,
                container, false);

        buttonSkip = getActivity().findViewById(R.id.buttonSkip);
        buttonChecking = view.findViewById(R.id.buttonSing);
        editText = view.findViewById(R.id.Answer2);
        imageView = view.findViewById(R.id.image);

        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
//                    if (result==TextToSpeech.LANG_MISSING_DATA
//                    || result==TextToSpeech.LANG_NOT_SUPPORTED){
//                    }
                }
            }
        });

        imageView.setOnClickListener(this);
        buttonChecking.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSing) {
            if (editText.getText().toString().equalsIgnoreCase(TestActivity.sortedList.get(TestActivity.num).engWord)) {
                editText.setText("");
                resume();
            }
        }
        if (v.getId() == R.id.image) {
            textToSpeech.speak(TestActivity.sortedList.get(TestActivity.num).engWord,TextToSpeech.QUEUE_FLUSH,null);
        }

    }

    static void resume() {
        buttonSkip.setBackgroundResource(R.drawable.button_green);
        buttonSkip.setText("Продолжить");
        TestActivity.buttonOn = false;
    }
}