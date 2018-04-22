package com.hang.johanneskraft.hangboard.model;

import com.orm.SugarRecord;

import java.util.List;


/**
 * Created by Johannes Kraft on 2017-08-22.
 */

public class TrainingSetSave extends SugarRecord {

        private int idTrainingSet;
        private String name="";
        private int prepareSeconds;
        private int hangTimeSeconds;
        private int restSeconds;
        private int pauseSeconds;
        private int sets;
        private int reps;

//IMPLEMENTER SENARE

    public TrainingSetSave() {

    }
/*        private int weight;
        private boolean weighted;
        private float review;
        private String date;
        private long duration;
        private int size;
        private String comment;

*/
        public TrainingSetSave(int reps, int sets){
            this.reps = reps;
            this.sets = sets;
        }

        public TrainingSetSave(String name, int prepareSeconds, int hangTimeSeconds, int restSeconds, int pauseSeconds, int sets, int reps) {
                this.setIdTrainingSet(checkId());
                this.name = name;
                this.prepareSeconds = prepareSeconds;
                this.hangTimeSeconds = hangTimeSeconds;
                this.restSeconds = restSeconds;
                this.pauseSeconds = pauseSeconds;
                this.sets = sets;
                this.reps = reps;
        }

        public int getIdTrainingSet() {
                return idTrainingSet;
        }

        public void setIdTrainingSet(int idTrainingSet) {
                this.idTrainingSet = idTrainingSet;
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

        public int getHangTimeSeconds() {
                return hangTimeSeconds;
        }

        public void setHangTimeSeconds(int hangTimeSeconds) {
                this.hangTimeSeconds = hangTimeSeconds;
        }

        public int getRestSeconds() {
                return restSeconds;
        }

        public void setRestSeconds(int restSeconds) {
                this.restSeconds = restSeconds;
        }

        public int getPauseSeconds() {
                return pauseSeconds;
        }

        public void setPauseSeconds(int pauseSeconds) {
                this.pauseSeconds = pauseSeconds;
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

    public long sumDuration(){
        long sumDuration=0;
        List<TrainingSetSave> authors = TrainingSetSave.find(TrainingSetSave.class, "full_name = ?", "Nathan");
        List<TrainingSetSave> mySc = TrainingSetSave.findWithQuery(TrainingSetSave.class, "Select * from TrainingSetSave where name = ?", "satya");;
        TrainingSetSave ts = mySc.get(mySc.size()-1);

        return sumDuration;
    }

        public int checkId(){
                int id=0;
                List<TrainingSetSave> mySc = SugarRecord.listAll(TrainingSetSave.class);

                if(mySc.size()==0){
                        id=0;
                }
                else{
                        TrainingSetSave tS = mySc.get(mySc.size()-1);
                        id = tS.getIdTrainingSet();

                }

                return id+1;

        }

        



}
