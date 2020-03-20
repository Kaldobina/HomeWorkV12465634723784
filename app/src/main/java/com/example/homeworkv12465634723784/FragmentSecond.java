package com.example.homeworkv12465634723784;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputBinding;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FragmentSecond extends Fragment {

    public static final String KEY = "text";
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentTextView textView = getActivity().findViewById(R.id.ff_value);
        View view =  inflater.inflate(R.layout.fragment_second, container, false);
        textView = view.findViewById(R.id.ff_value);

        String textFromFirstFrag = getArguments().getString(KEY);
        textView.setText(textFromFirstFrag);
        assert textFromFirstFrag != null;
        if (Integer.parseInt(textFromFirstFrag) % 2 == 0) {
            textView.setTextColor(Color.RED);
        } else textView.setTextColor(Color.BLUE);
        return view;
    }

}
