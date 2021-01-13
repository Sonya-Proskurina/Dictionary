package com.example.dictionary.Screen.Fragmants;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.dictionary.R;
import com.example.dictionary.Screen.Screens.TestActivity;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentTest1 extends Fragment {

    @BindView(R.id.Question)
    TextView word;
    @BindView(R.id.Answer)
    MaterialEditText editText;
    public Button buttonSkip;

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

        ButterKnife.bind(this, view);
        buttonSkip = getActivity().findViewById(R.id.buttonSkip);

        if (TestActivity.lesson == 0) {
            word.setText(TestActivity.sortedList.get(TestActivity.num).rusWord);
        } else {
            word.setText(TestActivity.sortedList.get(TestActivity.num).engWord);
        }

        return view;
    }

    @OnClick(R.id.buttonQuestion)
    public void checking() {
        if (TestActivity.lesson == 0) {
            if (editText.getText().toString().equalsIgnoreCase(TestActivity.sortedList.get(TestActivity.num).engWord)) {
                resume();
            }
        } else {
            if (editText.getText().toString().equalsIgnoreCase(TestActivity.sortedList.get(TestActivity.num).rusWord)) {
                resume();
            }
        }
    }

   private void resume() {
        editText.setText("");
        buttonSkip.setBackgroundResource(R.drawable.button_green);
        buttonSkip.setText("Продолжить");
        TestActivity.buttonOn = false;
    }
}
