package com.hang.johanneskraft.hangboard.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hang.johanneskraft.hangboard.R;

/**
 * Created by Johannes Kraft on 2017-11-06.
 */

public class TimerDialog extends android.app.DialogFragment {


    public TimerDialog() {

    }

    String name = "timerDialog";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.timer_dialog, null);
    }

}



