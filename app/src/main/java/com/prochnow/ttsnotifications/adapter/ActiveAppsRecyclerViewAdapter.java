package com.prochnow.ttsnotifications.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.prochnow.ttsnotifications.R;
import com.prochnow.ttsnotifications.model.AppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prochnow on 24.06.15.
 */
public class ActiveAppsRecyclerViewAdapter extends RecyclerView.Adapter<ActiveAppsRecyclerViewAdapter.ViewHolder> {
    public List<AppInfo> originalList = new ArrayList<>();

    private final String LOG_TAG = ActiveAppsRecyclerViewAdapter.class.getSimpleName();

    public ActiveAppsRecyclerViewAdapter() {

    }

    public void setData(List<AppInfo> list) {
        this.originalList = list;
    }


    // Provide a reference to the views for each data item
    // Complex data filteredList may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        @Bind(R.id.appName) TextView appName;
        @Bind(R.id.appIcon) ImageView appIcon;
        @Bind(R.id.appCheckbox) Switch appCheckbox;


        private final String LOG_TAG = ViewHolder.class.getSimpleName();

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View view) {

            this.appCheckbox.toggle();
        }

    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_activeapps, null);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppInfo info = originalList.get(position);

        holder.appIcon.setImageDrawable(info.getIcon());
        holder.appName.setText(info.getName());
        holder.appCheckbox.setChecked(info.isSelected());
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return originalList.size();
    }

}
