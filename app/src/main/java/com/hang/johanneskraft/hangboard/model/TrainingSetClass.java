package com.hang.johanneskraft.hangboard.model;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hang.johanneskraft.hangboard.R;

import java.io.Closeable;
import java.io.Serializable;

/**
 * Created by Johannes Kraft on 2017-03-10.
 */
import static com.hang.johanneskraft.hangboard.ui.TimerActivity.PREPARE;
import static com.hang.johanneskraft.hangboard.ui.TimerActivity.WORK;
import static com.hang.johanneskraft.hangboard.ui.TimerActivity.FINISH;
import static com.hang.johanneskraft.hangboard.ui.TimerActivity.REST;
import static com.hang.johanneskraft.hangboard.ui.TimerActivity.PAUSE;


public class TrainingSetClass implements Serializable {

    private String name;
    private int prepareSeconds;
    private int hangTimeSeconds;
    private int restSeconds;
    private int pauseSeconds;
    private String trainingState;
    private int repsSets;
    private int sets;
    private int reps;

    public TrainingSetClass() {
    }

    public TrainingSetClass(String name, int ps, int s, int r, int htS, int pause, int rs, String state) {
        this.name = name;
        this.prepareSeconds = ps;
        this.hangTimeSeconds = htS;
        this.restSeconds = rs;
        this.pauseSeconds = pause;
        this.sets = s;
        this.reps = r;
        this.trainingState = state;
        this.repsSets = r;

        if (trainingState == null) {
            if (ps > 0) {
                trainingState = PREPARE;
            } else {
                trainingState = WORK;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrepareSeconds() {
        return prepareSeconds;
    }

    public void setPrepareSeconds(int prepareSeconds) {
        this.prepareSeconds = prepareSeconds;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getRestSeconds() {
        return restSeconds;
    }

    public void setRestSeconds(int restSeconds) {
        this.restSeconds = restSeconds;
    }

    public int getHangTimeSeconds() {
        return hangTimeSeconds;
    }

    public void setHangTimeSeconds(int hangTimeSeconds) {
        this.hangTimeSeconds = hangTimeSeconds;
    }

    public int getPauseSeconds() {
        return restSeconds;
    }

    public void setPauseSeconds(int pauseSeconds) {
        this.restSeconds = pauseSeconds;
    }

    public String getTrainingState() {
        return trainingState;
    }

    public void setTrainingState(String etatTravail) {
        this.trainingState = etatTravail;
    }

    public int getRepsSets() {
        return repsSets;
    }

    public void setRepsSets(int repsSets) {
        this.repsSets = repsSets;
    }

    public void ChangeState(ConstraintLayout l, Context context) {

        if (trainingState == PREPARE) {
            trainingState = WORK;
            l.setBackgroundColor(ContextCompat.getColor(context, R.color.work));
        } else if (trainingState == WORK && sets > 0 && reps > 0) {
            reps--;
            trainingState = REST;
            l.setBackgroundColor(ContextCompat.getColor(context, R.color.rest));
            //l.clearAnimation();
        } else if (trainingState == REST && sets > 0 && reps > 0) {
            trainingState = WORK;
            l.setBackgroundColor(ContextCompat.getColor(context, R.color.work));
        } else if (trainingState == REST && reps == 0) {
            trainingState = PAUSE;
            l.setBackgroundColor(ContextCompat.getColor(context, R.color.pause));
        } else if (trainingState == PAUSE) {
            sets--;
            reps = repsSets;
            trainingState = WORK;
            l.setBackgroundColor(ContextCompat.getColor(context, R.color.work));
        } else {
            trainingState = FINISH;
            l.setBackgroundColor(ContextCompat.getColor(context, R.color.finish));
        }

    }

}
