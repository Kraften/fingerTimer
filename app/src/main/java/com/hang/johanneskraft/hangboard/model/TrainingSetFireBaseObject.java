package com.hang.johanneskraft.hangboard.model;

import java.io.Serializable;

/**
 * Created by Johannes Kraft on 2017-09-11.
 */

public class TrainingSetFireBaseObject implements Serializable {

    private String name;
    private String description;
    private int hangTimeSeconds;
    private int sets;
    private int reps;
    private int size;

    public TrainingSetFireBaseObject(){

    }
    public TrainingSetFireBaseObject(String name, int hangTimeSeconds, int sets, int reps){

        this.name = name;
        this.hangTimeSeconds=hangTimeSeconds;
        this.sets=sets;
        this.reps=reps;
    }



    public TrainingSetFireBaseObject(String name, String descriptionn , int hangTimeSeconds, int sets, int reps, int size) {
        this.name = name;
        this.description = description;
        this.hangTimeSeconds = hangTimeSeconds;
        this.sets = sets;
        this.reps = reps;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHangTimeSeconds() {
        return hangTimeSeconds;
    }

    public void setHangTimeSeconds(int hangTimeSeconds) {
        this.hangTimeSeconds = hangTimeSeconds;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }



}
