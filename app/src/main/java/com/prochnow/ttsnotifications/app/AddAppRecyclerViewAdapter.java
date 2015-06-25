package com.prochnow.ttsnotifications.app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.prochnow.ttsnotifications.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by prochnow on 24.06.15.
 */
public class AddAppRecyclerViewAdapter extends RecyclerView.Adapter<AddAppRecyclerViewAdapter.ViewHolder> {
    public List<AppInfo> applicationData;

    private final String LOG_TAG = AddAppRecyclerViewAdapter.class.getSimpleName();

    public interface OnItemClickListener {
        void onListItemClick(int position);
    }

    OnItemClickListener listener = new OnItemClickListener() {
        @Override
        public void onListItemClick(int position) {
            applicationData.get(position).selected = !applicationData.get(position).selected;
        }
    };

    public AddAppRecyclerViewAdapter(List<AppInfo> list) {
        this.applicationData = list;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        @InjectView(R.id.appName) TextView appName;
        @InjectView(R.id.appIcon) ImageView appIcon;
        @InjectView(R.id.appCheckbox) CheckBox appCheckbox;
        OnItemClickListener click;

        private final String LOG_TAG = ViewHolder.class.getSimpleName();

        public ViewHolder(View v, OnItemClickListener click) {
            super(v);
            ButterKnife.inject(this, v);
            this.click = click;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            click.onListItemClick(getAdapterPosition());
            this.appCheckbox.toggle();
        }

        @OnClick(R.id.appCheckbox)
        void onClick() {
            click.onListItemClick(getAdapterPosition());
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_addapp, null);
        ViewHolder vh = new ViewHolder(v, listener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppInfo info = applicationData.get(position);

        holder.appIcon.setImageDrawable(info.icon);
        holder.appName.setText(info.name);
        holder.appCheckbox.setChecked(info.selected);
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return applicationData.size();
    }

}
