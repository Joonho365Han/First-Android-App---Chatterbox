package com.ec327.chatterbox.chatterbox;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OnceUponATime extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View showButton = inflater.inflate(R.layout.show_button_template,container,false);
        showButton.findViewById(R.id.theShowButton).setBackgroundResource(R.drawable.once);
        showButton.findViewById(R.id.theShowButton).setTag(10);
        return showButton;
    }
}