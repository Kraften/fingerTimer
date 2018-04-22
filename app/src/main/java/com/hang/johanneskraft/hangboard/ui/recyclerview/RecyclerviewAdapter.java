package com.hang.johanneskraft.hangboard.ui.recyclerview;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hang.johanneskraft.hangboard.R;
import com.hang.johanneskraft.hangboard.model.TrainingSetSave;
import com.hang.johanneskraft.hangboard.ui.TimerActivity;
import com.hang.johanneskraft.hangboard.ui.addNewActivity;
import com.hang.johanneskraft.hangboard.utils.RevealAnimation;

import java.util.List;


public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.TrainingSetViewHolder> {

    final private ListItemClickListener mOnClickListener;
    private static final String TAG = RecyclerviewAdapter.class.getSimpleName();
    private int previousPosistion;
    private int mNumberItems;
    private Context mContext;
    private List<TrainingSetSave> setsList;
    private RevealAnimation mRevealAnimation;

    private TextView listItemNumberView;
    private TextView tv_Duration;
    private TextView tv_Sets;
    private TextView tv_Reps;
    private TextView tv_Hangtime;

    private Context getContext() {
        return mContext;
    }


    public RecyclerviewAdapter(List<TrainingSetSave> trainingSets, ListItemClickListener listener) {
        mOnClickListener = listener;
        setsList = trainingSets;
        // mContext = context;
    }

    @Override
    public TrainingSetViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.training_set_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        TrainingSetViewHolder viewHolder = new TrainingSetViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrainingSetViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        previousPosistion = position;
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return setsList.size();
    }

    /**
     * Cache of the children views for a list item.
     */
    class TrainingSetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Will display the position in the list, ie 0 through getItemCount() - 1
        public TrainingSetViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_training_set_name);
            tv_Duration = (TextView) itemView.findViewById(R.id.tv_duration);
            tv_Sets = (TextView) itemView.findViewById(R.id.tv_sets);
            tv_Reps = (TextView) itemView.findViewById(R.id.tv_reps);
            tv_Hangtime = (TextView) itemView.findViewById(R.id.tv_hangtime);
        }

        void bind(int listIndex) {
            TrainingSetSave tss = setsList.get(listIndex);
            listItemNumberView.setText(tss.getName());
            tv_Sets.setText(String.valueOf(tss.getSets()));
            tv_Reps.setText(String.valueOf(tss.getReps()));
            tv_Hangtime.setText(String.valueOf(tss.getHangTimeSeconds()));
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


}
