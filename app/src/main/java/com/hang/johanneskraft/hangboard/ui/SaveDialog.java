package com.hang.johanneskraft.hangboard.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.hang.johanneskraft.hangboard.R;

/**
 * Created by Johannes Kraft on 2017-09-12.
 */

public class SaveDialog extends android.app.DialogFragment{

    public SaveDialog(){

    }

    String name = "saveDialog";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.save_dialog, null);
    }

}
