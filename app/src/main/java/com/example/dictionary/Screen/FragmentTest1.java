package com.example.dictionary.Screen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dictionary.R;
import com.rengwuxian.materialedittext.MaterialEditText;

public class FragmentTest1 extends Fragment implements View.OnClickListener {
   static Button buttonChecking;
    TextView word;
    MaterialEditText editText;
    static Button buttonSkip;
    public FragmentTest1() {

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
        View view = inflater.inflate(R.layout.fragment_test1,
                container, false);
        word = view.findViewById(R.id.Question);
        buttonChecking = view.findViewById(R.id.buttonQuestion);
        buttonChecking.setOnClickListener(this);
        buttonSkip=getActivity().findViewById(R.id.buttonSkip);
        editText = view.findViewById(R.id.Answer);
        if (TestActivity.lesson == 0)

            word.setText(TestActivity.sortedList.get(TestActivity.num).rusWord);
        else
            word.setText(TestActivity.sortedList.get(TestActivity.num).engWord);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.buttonQuestion){
                if (TestActivity.lesson == 0) {
                    if (editText.getText().toString().equalsIgnoreCase(TestActivity.sortedList.get(TestActivity.num).engWord)) {
                        editText.setText("");
                        resume();
                    }
                } else {
                    if (editText.getText().toString().equalsIgnoreCase(TestActivity.sortedList.get(TestActivity.num).rusWord)) {
                        editText.setText("");
                        resume();
                    }
                }
            }
        }
    static void resume(){
        buttonSkip.setBackgroundResource(R.drawable.button_green);
        buttonSkip.setText("Продолжить");
        TestActivity.buttonOn = false;
    }
    }
