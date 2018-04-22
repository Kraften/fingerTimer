package com.hang.johanneskraft.hangboard.ui;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hang.johanneskraft.hangboard.R;
import com.hang.johanneskraft.hangboard.model.TrainingSetSave;
import com.hang.johanneskraft.hangboard.ui.recyclerview.RecyclerviewAdapter;
import com.hang.johanneskraft.hangboard.utils.RevealAnimation;
import com.hang.johanneskraft.hangboard.utils.SimpleDividerItemDecoration;

import java.util.List;

/**
 * Created by Johannes Kraft on 2017-09-01.
 */


public class SessionsListActivity extends AppCompatActivity implements RecyclerviewAdapter.ListItemClickListener {

    private static final String TAG = "com.hang.johanneskraft.hangboard.ui.SessionsListActivity";
    public static final int DB_UPDATE_REQUEST = 1;
    public static final String LISTVIEW_ACTIVITY_IDENTIFICATION = "ID";
    private List<TrainingSetSave> trainingSetSaveList;
    private RecyclerviewAdapter mAdapter;
    private RecyclerView mTrainingSetList;
    private FloatingActionButton fab;
    private ConstraintLayout listView;
    private LinearLayout addView;
    private Context c;
    private SimpleDividerItemDecoration divider;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessions_list_activity);
        initViews();
        initListView();
        //TrainingSetSave.deleteAll(TrainingSetSave.class);
        Log.d("SessionsListActivity", "onCreate(Bundle) called");
        final FragmentManager fm = this.getFragmentManager();
        divider = new SimpleDividerItemDecoration(ContextCompat.getDrawable(mTrainingSetList.getContext(), R.drawable.line_divider));
        mTrainingSetList.addItemDecoration(divider);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRevealActivity(v);
            }
        });
    }

    private void startRevealActivity(View v) {
        //calculates the center of the View v you are passing
        int revealX = (int) (v.getX() + v.getWidth() / 2);
        int revealY = (int) (v.getY() + v.getHeight() / 2);

        //create an intent, that launches the second activity and pass the x and y coordinates
        Intent intent = new Intent(this, addNewActivity.class);
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_Y, revealY);

        //just start the activity as an shared transition, but set the options bundle to null
        ActivityCompat.startActivity(this, intent, null);

        //to prevent strange behaviours override the pending transitions
        overridePendingTransition(0, 0);
    }

    private void startRevealActivityTimer(View v) {
        //calculates the center of the View v you are passing
        int revealX = (int) (v.getX() + v.getWidth() / 2);
        int revealY = (int) (v.getY() + v.getHeight() / 2);

        //create an intent, that launches the second activity and pass the x and y coordinates
        Intent intent = new Intent(this, TimerActivity.class);
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_Y, revealY);

        //just start the activity as an shared transition, but set the options bundle to null
        ActivityCompat.startActivity(this, intent, null);

        //to prevent strange behaviours override the pending transitions
        overridePendingTransition(0, 0);
    }


    private void initListView() {
        //mTrainingSetList.setHasFixedSize(true);
        trainingSetSaveList = TrainingSetSave.listAll(TrainingSetSave.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mTrainingSetList.setLayoutManager(layoutManager);

        mAdapter = new RecyclerviewAdapter(trainingSetSaveList, this);
        mTrainingSetList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mAdapter.notifyItemInserted(0);
    }

    private void initViews() {
        mTrainingSetList = (RecyclerView) findViewById(R.id.rv_trainingSets);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ConstraintLayout) findViewById(R.id.listView);
        addView = (LinearLayout) findViewById(R.id.container);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(SessionsListActivity.this, TimerActivity.class);
        int objId = trainingSetSaveList.get(clickedItemIndex).getIdTrainingSet();
        intent.putExtra(LISTVIEW_ACTIVITY_IDENTIFICATION, objId);
        startActivity(intent);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, "onPostResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        initListView();
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
