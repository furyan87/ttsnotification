package com.ttsnotification.prochnow.ttsnotifications;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ttsnotification.prochnow.ttsnotifications.database.SimpleData;

import java.util.List;

/**
 * Created by prochnow on 24.06.15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<SimpleData> mDataset;

    public MyAdapter(List<SimpleData> list) {
        mDataset = list;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);

            mTextView = (TextView) v;

        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {

        // create a new view
        TextView textView = new TextView(parent.getContext());
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(textView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
