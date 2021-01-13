package com.example.dictionary.Screen.Fragmants;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.dictionary.R;
import com.example.dictionary.Screen.Screens.TestActivity;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentTest2 extends Fragment {

    @BindView(R.id.Answer2)
    MaterialEditText editText;
    public Button buttonSkip;
    private TextToSpeech textToSpeech;

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
        ButterKnife.bind(this, view);

        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });

        return view;
    }

    @OnClick(R.id.buttonSing)
    public void sing() {
        if (editText.getText().toString().equalsIgnoreCase(TestActivity.sortedList.get(TestActivity.num).engWord)) {
            editText.setText("");
            buttonSkip.setBackgroundResource(R.drawable.button_green);
            buttonSkip.setText("Продолжить");
            TestActivity.buttonOn = false;
        }
    }

    @OnClick(R.id.image)
    public void image() {
        textToSpeech.speak(TestActivity.sortedList.get(TestActivity.num).engWord, TextToSpeech.QUEUE_FLUSH, null);
    }
}