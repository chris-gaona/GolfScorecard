package com.chrisgaona.golfscorecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by chrisgaona on 10/19/17.
 */

public class ListAdapter extends BaseAdapter {

    private final Context mContent;
    private final Hole[] mHoles;

    public ListAdapter (Context context, Hole[] holes) {
        mContent = context;
        mHoles = holes;
    }

    @Override
    public int getCount() {
        return mHoles.length;
    }

    @Override
    public Object getItem(int i) {
        return mHoles[i];
    }

    @Override
    public long getItemId(int i) {
        return 0; // not implementing
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;

        // if view is null, we need to create it
        if (view == null) {
            view = LayoutInflater.from(mContent).inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.holeLabel = (TextView) view.findViewById(R.id.holeLabel);
            holder.strokeCount = (TextView) view.findViewById(R.id.strokeCount);
            holder.removeStrokeButton = (Button) view.findViewById(R.id.removeStrokeButton);
            holder.addStrokeButton = (Button) view.findViewById(R.id.addStrokeButton);

            view.setTag(holder);

        // else view has already been created and just needs to be updated
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // set text for TextView items
        holder.holeLabel.setText(mHoles[i].getLabel());
        holder.strokeCount.setText(mHoles[i].getStrokeCount() + "");

        // set click listener for 2 buttons
        holder.removeStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int updatedStrokeCount = mHoles[i].getStrokeCount() - 1;
                if (updatedStrokeCount < 0) updatedStrokeCount = 0;
                mHoles[i].setStrokeCount(updatedStrokeCount);
                holder.strokeCount.setText(updatedStrokeCount + "");
            }
        });

        holder.addStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int updatedStrokeCount = mHoles[i].getStrokeCount() + 1;
                mHoles[i].setStrokeCount(updatedStrokeCount);
                holder.strokeCount.setText(updatedStrokeCount + "");
            }
        });


        return view;
    }

    private static class ViewHolder {
        TextView holeLabel;
        TextView strokeCount;
        Button removeStrokeButton;
        Button addStrokeButton;
    }
}
