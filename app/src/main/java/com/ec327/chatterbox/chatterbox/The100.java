package com.ec327.chatterbox.chatterbox;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class The100 extends Fragment {

    //A button object that configures the show icon button on the myshows list as the specific show this class represents.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View showButton = inflater.inflate(R.layout.show_button_template,container,false);
        showButton.findViewById(R.id.theShowButton).setBackgroundResource(R.drawable.the100);
        showButton.findViewById(R.id.theShowButton).setTag(12);
        return showButton;
    }
}