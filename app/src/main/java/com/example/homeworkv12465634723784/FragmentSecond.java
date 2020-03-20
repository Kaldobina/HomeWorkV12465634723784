package com.example.homeworkv12465634723784;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentSecond extends Fragment {

    static final String KEY = "text";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentTextView textView = getActivity().findViewById(R.id.ff_value);
        View view =  inflater.inflate(R.layout.fragment_second, container, false);
        TextView textView = view.findViewById(R.id.ff_value);

        assert getArguments() != null;
        String textFromFirstFrag = getArguments().getString(KEY);
        textView.setText(textFromFirstFrag);
        assert textFromFirstFrag != null;
        if (Integer.parseInt(textFromFirstFrag) % 2 == 0) {
            textView.setTextColor(Color.RED);
        } else textView.setTextColor(Color.BLUE);
        return view;
    }

}
