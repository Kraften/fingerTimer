package com.hang.johanneskraft.hangboard.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hang.johanneskraft.hangboard.R;
import com.hang.johanneskraft.hangboard.utils.DurationPicker;
import com.hang.johanneskraft.hangboard.utils.RevealAnimation;

import org.w3c.dom.Text;


/**
 * Created by Johannes Kraft on 2017-09-21.
 */

public class addNewActivity extends AppCompatActivity {

    private static TextView tv_hangtime_add;
    private static TextView tv_resttime_add;
    private static TextView tv_pausetime_add;
    private static Button btn_sets_pos;
    private static Button btn_sets_neg;
    private static Button btn_reps_pos;
    private static Button btn_reps_neg;
    private static TextView tv_sets_number;
    private static TextView tv_reps_number;
    private RevealAnimation mRevealAnimation;
    private ConstraintLayout mlayout;
    private TextView tv_add_hangtime;
    private TextView tv_add_restime;
    private TextView tv_add_pausetime;

    private String name="";
    private int hangTimeSeconds;
    private int restSeconds;
    private int pauseSeconds;
    private int sets;
    private int reps;
    private int countReps = 0;
    private int countSets = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        initViews();

        Intent intent = this.getIntent();
        mRevealAnimation = new RevealAnimation(mlayout, intent, this);

        name="";
        hangTimeSeconds=0;
        restSeconds=0;
        pauseSeconds=0;
        sets=0;
        reps=0;

        btn_sets_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringVal;
                countSets++;
                sets = countSets;
                tv_sets_number.setText(String.valueOf(countSets));
            }
        });

        btn_sets_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringVal;
                if(sets >0)
                    countSets--;
                sets = countSets;
                tv_sets_number.setText(String.valueOf(countSets));
            }
        });

        btn_reps_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringVal;
                countReps++;
                reps = countReps;
                tv_reps_number.setText(String.valueOf(countReps));
            }
        });
        btn_reps_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringVal;
                if(reps >0)
                    countReps--;
                reps = countReps;
                tv_reps_number.setText(String.valueOf(countReps));
            }
        });


        tv_add_hangtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimerDialog dialog = new TimerDialog();
                dialog.show(getFragmentManager(),"timerDialog");
            }
        });

//        tv_resttime_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openTimePickerFragmentRest(v);
//            }
//        });
//
//        tv_pausetime_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openTimePickerFragmentPause(v);
//            }
//        });





    }
    private String secondsToString(int pTime) {
        return String.format("%2d", pTime / 60, pTime % 60);
    }


    private void UpdateTextViews(){
        tv_sets_number.setText(secondsToString(sets));
/*        effortView.setText(secondsToString(effort));
        recupView.setText(secondsToString(recup));
        repView.setText(Integer.toString(rep));
        serieView.setText(Integer.toString(serie));
        nameSeanceView.setText(name);*/
    }





/*    public void changeSets(View view){
        Log.d("UTANFLR","ANTAL" +sets);

        if (view.getId() == R.id.btn_sets_Pos){
            sets--;
            Log.d("NEGATIV","ANTAL" +sets);

        } else if (view.getId() == R.id.btn_sets_neg && sets >=1)  {
            sets++;
            Log.d("POSSITIV","ANTAL" +sets);
        }
        UpdateTextViews();
    }*/

    private void initViews() {
        btn_sets_pos = (Button) findViewById(R.id.btn_sets_Pos);
        btn_sets_neg = (Button) findViewById(R.id.btn_sets_neg);
        btn_reps_pos = (Button) findViewById(R.id.btn_resp_pos);
        btn_reps_neg = (Button) findViewById(R.id.btn_resp_neg);
        tv_sets_number = (TextView) findViewById(R.id.tv_sets_number);
        tv_reps_number = (TextView) findViewById(R.id.tv_reps_number);
        mlayout = (ConstraintLayout) findViewById(R.id.add_new_layout);
        tv_add_pausetime = (TextView) findViewById(R.id.tv_add_pausetime);
        tv_add_restime = (TextView) findViewById(R.id.tv_add_resttime);
        tv_add_hangtime = (TextView) findViewById(R.id.tv_add_hangtime);
    }


    public static class numberaPicker extends DialogFragment{
        @Override
        public View onCreateView(
                LayoutInflater inflater,
                ViewGroup container,
                Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            textView.setText(R.string.hello_blank_fragment);
            return textView;
        }
    }




    public void openNumber(View view) {
        final android.app.FragmentManager fm = this.getFragmentManager();
        android.app.DialogFragment newFragment = new numberaPicker();
        newFragment.show(fm, "timePicker");
    }





























    public void openTimePickerFragmentHang(View view) {
        final android.app.FragmentManager fm = this.getFragmentManager();
        android.app.DialogFragment newFragment = new TimePickerFragmentHang();
        newFragment.show(fm, "timePicker");
    }

    public void openTimePickerFragmentRest(View view) {
        final android.app.FragmentManager fm = this.getFragmentManager();
        android.app.DialogFragment newFragment = new TimePickerFragmentRest();
        newFragment.show(fm, "timePicker");
    }

    public void openTimePickerFragmentPause(View view) {
        final android.app.FragmentManager fm = this.getFragmentManager();
        android.app.DialogFragment newFragment = new TimePickerFragmentPause();
        newFragment.show(fm, "timePicker");
    }

    public static class TimePickerFragmentHang extends DialogFragment
            implements DurationPicker.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int minute = c.get(Calendar.HOUR_OF_DAY);
            int seconds = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, minute, seconds,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int minute, int seconds) {
            Log.d("TIMEPICKER ", " " + view.getClass());
            tv_hangtime_add.setText(Integer.toString(minute) + ":" + Integer.toString(seconds));
        }
    }

    public static class TimePickerFragmentRest extends DialogFragment
            implements DurationPicker.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int minute = c.get(Calendar.HOUR_OF_DAY);
            int seconds = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, minute, seconds,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int minute, int seconds) {
            Log.d("TIMEPICKER ", " " + view.getClass());
            tv_resttime_add.setText(Integer.toString(minute) + ":" + Integer.toString(seconds));
        }
    }

    public static class TimePickerFragmentPause extends DialogFragment
            implements DurationPicker.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int minute = c.get(Calendar.HOUR_OF_DAY);
            int seconds = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, minute, seconds,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int minute, int seconds) {
            Log.d("TIMEPICKER ", " " + view.getClass());
            tv_pausetime_add.setText(Integer.toString(minute) + ":" + Integer.toString(seconds));
        }
    }

    @Override
    public void onBackPressed() {
        mRevealAnimation.unRevealActivity();
        super.onBackPressed();
    }
}
