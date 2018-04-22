package com.hang.johanneskraft.hangboard.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.hang.johanneskraft.hangboard.R;
import com.hang.johanneskraft.hangboard.model.TrainingSetClass;
import com.hang.johanneskraft.hangboard.model.TrainingSetFireBaseObject;
import com.hang.johanneskraft.hangboard.model.TrainingSetSave;
import com.hang.johanneskraft.hangboard.utils.RevealAnimation;

import java.util.List;
import android.widget.ToggleButton;

public class TimerActivity extends AppCompatActivity {

    public final static String REST= "REST";
    public final static String WORK= "HANG";
    public final static String FINISH= "FINSIH";
    public final static String PREPARE= "PREPARE";
    public final static String PAUSE= "PAUSE";

    static final String STATE_NAME = "TrainingSetName";
    static final String STATE_PREPARE = "IntPrepare";
    static final String STATE_HANGTIME = "IntHangTime";
    static final String STATE_REST = "IntRest";
    static final String STATE_REP = "IntRep";
    static final String STATE_SET = "IntSet";
    static final String STATE_PAUSE = "IntPause";
    static  final String STATE_TIME="IntTime";
    static final String STATE_CHRONO="EtatChrono";
    static final String STATE="Etat";

    public final static int LOAD_ACTIVITY_REQUEST = 3;

    private int totTimer;

    private ConstraintLayout mLayout;
    private TrainingSetClass training;
    private TrainingSetSave ts;
    private TrainingSetFireBaseObject fireBaseObject;

    private CountDownTimer timer;
    private long updatedTime;
    private int ID;
    private int LancerTimer=0;
    private String etat="";

    private TextView textViewTime;
    private ImageView imageViewReset;
    private ImageView imageViewStartStop;
    private ProgressBar setsProgressBar;
    private TextView sets_textView;
    private TextView reps_textView;
    private TextView state_textView;
    private TextView nameSet_textView;
    private RevealAnimation mRevealAnimation;
    private ToggleButton toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        initViews();

        Intent intent = this.getIntent();
        mRevealAnimation = new RevealAnimation(mLayout, intent, this);

        ID = getIntent().getIntExtra(SessionsListActivity.LISTVIEW_ACTIVITY_IDENTIFICATION, -1);
        if (ID != -1) {
            loadData(ID);
            uppdateTimerUi();
        }

    }

/*        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(TimerActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.save_dialog, null);
                TextView mTextView = (TextView) mView.findViewById(R.id.dialog_tv_finsihed);
                RatingBar mRatingBar = (RatingBar) mView.findViewById(R.id.dialog_ratingBar);

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }

        });*/



    public void startTimer(View view) {
        if(ID!=-1) {
            cdTimer(updatedTime);
            LancerTimer = 1;
        }
    }

    public void cdTimer(long time){

        timer = new CountDownTimer(time, 10) {

            public void onTick(long millisUntilFinished) {
                updatedTime = millisUntilFinished;
                uppdateTimerUi();
            }

            public void onFinish() {
                updatedTime = 0;
                training.ChangeState(mLayout, getApplicationContext());
                uppdateTimerUi();

                if(training.getTrainingState()!=FINISH) {
                    if(training.getTrainingState()==WORK){
                        updatedTime=training.getHangTimeSeconds();
                    }
                    else if (training.getTrainingState()==PAUSE){
                        updatedTime=training.getPauseSeconds();
                    }
                    else{
                        updatedTime=training.getRestSeconds();
                    }
                    cdTimer(updatedTime*1000);
                }else{
                    uppdateTimerUi();
                    getCurrentSet(ID);
                }
            }
        }.start();
    }

    public void pauseTimer(View view) {

        if(ID!=-1) {
            if (timer != null) {
                timer.cancel();
            }
            LancerTimer = 0;
        }
    }

    private void uppdateTimerUi() {


        int secs = (int) (updatedTime / 1000);
        int mins = secs / 60;
        secs = secs % 60;
        int milliseconds = (int) (updatedTime % 1000);

        textViewTime.setText("" +String.format("%02d", mins)  + ":" + String.format("%02d", secs));

        sets_textView.setText(Integer.toString(training.getSets()) + "/" + Integer.toString(ts.getSets()));

        reps_textView.setText(Integer.toString(training.getReps())+ "/" + Integer.toString(ts.getReps()));
        state_textView.setText(training.getTrainingState());
    }

    public void Reset(View v){
        if(ID!=-1) {
            loadData(ID);
            uppdateTimerUi();
        }
    }

    public void openList(View view){
        Intent intent = new Intent(TimerActivity.this, SessionsListActivity.class);
        startActivity(intent);
    }

    private void getCurrentSet(int i){

//        fireBaseObject = new TrainingSetFireBaseObject(trainingSet.getName(),trainingSet.getHangTimeSeconds(), trainingSet.getSets(),trainingSet.getReps());
/*        AlertDialog.Builder mBuilder = new AlertDialog.Builder(TimerActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.save_dialog, null);
        TextView mTextView = (TextView) mView.findViewById(R.id.dialog_tv_finsihed);
        RatingBar mRatingBar = (RatingBar) mView.findViewById(R.id.dialog_ratingBar);
        Button saveButton = (Button) findViewById(R.id.dialog_btn_save);

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();*/
        SaveDialog dialog = new SaveDialog();
        dialog.show(getFragmentManager(),"saveDialog");

    }

    public void loadData(int ID){
        List<TrainingSetSave> newSets = TrainingSetSave.findWithQuery(TrainingSetSave.class,"SELECT * FROM training_set_save WHERE id_training_set = ?",Integer.toString(ID));
        TrainingSetSave trainingSet = newSets.get(0);
        training = new TrainingSetClass(trainingSet.getName(),trainingSet.getPrepareSeconds(), trainingSet.getSets(), trainingSet.getReps(), trainingSet.getHangTimeSeconds(), trainingSet.getPauseSeconds(), trainingSet.getRestSeconds(), null);
        training.setPauseSeconds(trainingSet.getPauseSeconds());
        training.setRestSeconds(trainingSet.getRestSeconds());

        List<TrainingSetSave> newSets2 = TrainingSetSave.findWithQuery(TrainingSetSave.class, "SELECT * FROM training_set_save WHERE id_training_set = ?", Integer.toString(ID));
        TrainingSetSave trainingSet2 = newSets2.get(0);
        ts = new TrainingSetSave(trainingSet2.getReps(), trainingSet2.getSets());



        nameSet_textView.setText(training.getName().toUpperCase());
        totTimer=trainingSet.getPrepareSeconds();
        updatedTime= totTimer*1000;
        //l.setBackgroundColor(Color.WHITE);
    }

    public void AddSeance(View v){
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent,LOAD_ACTIVITY_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==LOAD_ACTIVITY_REQUEST){
        }
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        String name = savedInstanceState.getString(STATE_NAME);
        int hangTime = savedInstanceState.getInt(STATE_HANGTIME);
        int prepare = savedInstanceState.getInt(STATE_PREPARE);
        int rest = savedInstanceState.getInt(STATE_REST);
        int pause = savedInstanceState.getInt(STATE_PAUSE);
        int set = savedInstanceState.getInt(STATE_SET);
        int rep = savedInstanceState.getInt(STATE_REP);
        updatedTime = savedInstanceState.getLong(STATE_TIME);
        int startChrono = savedInstanceState.getInt(STATE_CHRONO);
        etat = savedInstanceState.getString(STATE);

        training = new TrainingSetClass(name,prepare,set,rep,hangTime,pause,rest,etat);

        nameSet_textView.setText("Name: " + training.getName());

        if(startChrono==1){
            startTimer(findViewById(R.id.activity_main));
        }else{
            pauseTimer(findViewById(R.id.activity_main));
        }
        switch (etat){

            case WORK:
                mLayout.setBackgroundColor(Color.RED);
                break;

            case REST:
                mLayout.setBackgroundColor(Color.GREEN);
                break;

            case PREPARE:
                break;

            case FINISH:
                mLayout.setBackgroundColor(Color.BLUE);
                mLayout.clearAnimation();
                break;
        }
        uppdateTimerUi();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        if(training!=null) {
            savedInstanceState.putString(STATE_NAME, training.getName());
            savedInstanceState.putInt(STATE_HANGTIME, training.getHangTimeSeconds());
            savedInstanceState.putInt(STATE_PREPARE, training.getPrepareSeconds());
            savedInstanceState.putInt(STATE_REST, training.getRestSeconds());
            savedInstanceState.putInt(STATE_REP, training.getReps());
            savedInstanceState.putInt(STATE_SET, training.getSets());
            savedInstanceState.putInt(STATE_PAUSE, training.getPauseSeconds());
            savedInstanceState.putLong(STATE_TIME, updatedTime);
            savedInstanceState.putInt(STATE_CHRONO,LancerTimer);
            savedInstanceState.putString(STATE,training.getTrainingState());
        }
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * method to initialize the views
     */
    private void initViews() {
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        imageViewReset = (ImageView) findViewById(R.id.imageViewReset);
        imageViewStartStop = (ImageView) findViewById(R.id.imageViewStartStop);
        mLayout = (ConstraintLayout) findViewById(R.id.activity_main);
        sets_textView = (TextView) findViewById(R.id.sets_textView);
        reps_textView = (TextView) findViewById(R.id.reps_textView);
        state_textView = (TextView) findViewById(R.id.state_textView);
        nameSet_textView = (TextView) findViewById(R.id.setName_textView);
        toggle = (ToggleButton) findViewById(R.id.button_play_pause_toggle);
    }
}