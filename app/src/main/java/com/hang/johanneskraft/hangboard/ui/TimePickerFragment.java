package com.hang.johanneskraft.hangboard.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.widget.TimePicker;

import com.hang.johanneskraft.hangboard.utils.DurationPicker;

import java.util.List;

/**
 * Created by Johannes Kraft on 2017-09-19.
 */

public class TimePickerFragment extends DialogFragment
        implements DurationPicker.OnTimeSetListener{



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int minute = c.get(Calendar.MINUTE);
        int seconds = c.get(Calendar.SECOND);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, minute, seconds,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int minute, int seconds) {
        Intent intent = new Intent();
        intent.putExtra("minuse",minute);
        intent.putExtra("seconds", seconds);

    }
}
