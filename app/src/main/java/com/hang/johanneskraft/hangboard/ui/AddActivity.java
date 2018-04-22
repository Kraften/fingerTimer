package com.hang.johanneskraft.hangboard.ui;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.google.android.gms.auth.api.Auth;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hang.johanneskraft.hangboard.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.hang.johanneskraft.hangboard.model.TrainingSetSave;
import com.hang.johanneskraft.hangboard.utils.RevealAnimation;
import com.orm.SugarContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private int prepare = 0;
    private int hangtime = 0;
    private int rest = 0;
    private int rep = 0;
    private int set = 0;
    private int pause = 0;
    private String name = "Training set";

    static final String STATE_NAME = "TrainingSetName";
    static final String STATE_PREPARE = "IntPrepare";
    static final String STATE_HANGTIME = "IntHangTime";
    static final String STATE_REST = "IntRest";
    static final String STATE_REP = "IntRep";
    static final String STATE_SET = "IntSet";
    static final String STATE_PAUSE = "IntPause";

    private String formattedDate;

    private SeekBar setsSeekbar;
    private SeekBar repsSeekBar;
    private SeekBar hangTimeSeekBar;
    private SeekBar restSeekBar;
    private SeekBar pauseSeekBar;
    private SeekBar prepareSeekBar;

    private TextView setsTextView;
    private TextView repsTextView;
    private TextView hangsTextView;
    private TextView restTextView;
    private TextView pauseTextView;
    private TextView prepareNumberTextView;
    private TextView prepareTextView;
    private EditText nameEditText;
    private Button button2;
    private Button logOutBtn;

    private GoogleApiClient mGoogleApiClient;
    private FirebaseUser mCurrentUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    private Calendar calender;
    private RevealAnimation mRevealAnimation;

    private LinearLayout lin;

    private int setsSeekBarPos = 4;
    private int repsSeekBarPos = 4;
    private int hangTimeSeekBarPos = 4;
    private int sizeSeekBarPos = 4;
    private int restSeekBarPos = 4;
    private int pauseSeekBarPos = 4;
    public int currentSession = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_add);
        List<TrainingSetSave> list = TrainingSetSave.listAll(TrainingSetSave.class);
        initFireBase();
        initViews();
        seekbarStarup();

        Intent intent = this.getIntent();
        mRevealAnimation = new RevealAnimation(lin, intent, this);

        calender = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        formattedDate = df.format(calender.getTime());

        //FIREBASE LOGOUT FUNCTION
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(AddActivity.this, LoginActivity.class));
                }
            }
        };

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAuth.signOut();
                signOut();
            }
        });

        setsSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setsTextView.setText(""+progress);
                setsSeekBarPos = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        repsSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                repsTextView.setText(""+progress);
                repsSeekBarPos = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        hangTimeSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hangsTextView.setText("" + progress+"s");
                hangTimeSeekBarPos = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        restSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                restTextView.setText("" + progress+"s");
                restSeekBarPos = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        pauseSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pauseTextView.setText("" + progress+"s");
                pauseSeekBarPos = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        prepareSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prepareNumberTextView.setText("" + progress + "s");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onBackPressed()
    {
        mRevealAnimation.unRevealActivity();
        resetSeekBars();
    }

    public void seekbarStarup(){
        setsSeekbar.setProgress(2);
        repsSeekBar.setProgress(2);
        hangTimeSeekBar.setProgress(3);
        restSeekBar.setProgress(2);
        pauseSeekBar.setProgress(10);
        prepareSeekBar.setProgress(3);

    }

    public void resetSeekBars() {
        restSeekBar.setProgress(0);
        pauseSeekBar.setProgress(0);
        repsSeekBar.setProgress(0);
        hangTimeSeekBar.setProgress(0);

    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // ...
                    }
                });
    }

    private void googleSignInOptions(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        googleSignInOptions();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void createSet(View view){

       if (setsSeekbar.getProgress()!=0 && repsSeekBar.getProgress()!=0 && hangTimeSeekBar.getProgress()!=0 && restSeekBar.getProgress()!=0 && pauseSeekBar.getProgress()!=0 && prepareSeekBar.getProgress()!=0){

           String name = nameEditText.getText().toString();
           prepare = prepareSeekBar.getProgress();
           hangtime= hangTimeSeekBar.getProgress();
           pause= pauseSeekBar.getProgress();
           rest= restSeekBar.getProgress();
           set= setsSeekbar.getProgress();
           rep= repsSeekBar.getProgress();
           Log.d("AAAA","HALLÅÅÅ" + setsSeekBarPos );
           TrainingSetSave newSet = new TrainingSetSave(name, prepare, hangtime, rest, pause, set, rep);
           newSet.save();
           mRevealAnimation.unRevealActivity();
           super.finish();
       }else{
           Snackbar mySnackbar = Snackbar.make(lin, "Dont forget to make all the choises", Snackbar.LENGTH_LONG);
           mySnackbar.show();
       }
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        name = savedInstanceState.getString(STATE_NAME);
        hangtime = savedInstanceState.getInt(STATE_HANGTIME);
        prepare = savedInstanceState.getInt(STATE_PREPARE);
        rest = savedInstanceState.getInt(STATE_REST);
        set = savedInstanceState.getInt(STATE_SET);
        rep = savedInstanceState.getInt(STATE_REP);
        pause = savedInstanceState.getInt(STATE_PAUSE);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString(STATE_NAME, name);
        savedInstanceState.putInt(STATE_HANGTIME, hangtime);
        savedInstanceState.putInt(STATE_PREPARE, prepare);
        savedInstanceState.putInt(STATE_REST, rest);
        savedInstanceState.putInt(STATE_REP, rep);
        savedInstanceState.putInt(STATE_SET, set);
        savedInstanceState.putInt(STATE_PAUSE, pause);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    private void initViews() {
        button2 = (Button) findViewById(R.id.button2);
        setsSeekbar = (SeekBar) findViewById(R.id.sets_seekBar);
        setsSeekbar.setMax(10);
        repsSeekBar = (SeekBar) findViewById(R.id.reps_seekBar);
        repsSeekBar.setMax(10);
        hangTimeSeekBar = (SeekBar) findViewById(R.id.hangTime_seekBar);
        hangTimeSeekBar.setMax(60);
        restSeekBar = (SeekBar) findViewById(R.id.rest_seekBar);
        restSeekBar.setMax(60);
        pauseSeekBar = (SeekBar) findViewById(R.id.pause_seekBar);
        pauseSeekBar.setMax(600);
        prepareSeekBar = (SeekBar) findViewById(R.id.prepare_SeekBar);
        prepareSeekBar.setMax(60);
        setsTextView = (TextView) findViewById(R.id.setsNumber_textView);
        repsTextView = (TextView) findViewById(R.id.repsNumber_textView);
        hangsTextView = (TextView) findViewById(R.id.hangTimeNumber_textView);
        restTextView = (TextView) findViewById(R.id.restNumber_textView);
        pauseTextView = (TextView) findViewById(R.id.pauseNumber_textView);
        prepareNumberTextView = (TextView) findViewById(R.id.prepareNumber_textView);
        prepareTextView = (TextView) findViewById(R.id.prepare_textView);
        nameEditText = (EditText) findViewById(R.id.editTextName);
        logOutBtn = (Button) findViewById(R.id.LogOutBtn);
        lin = (LinearLayout) findViewById(R.id.container);
    }

    private void initFireBase(){
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
}