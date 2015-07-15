package com.prochnow.ttsnotifications.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.prochnow.ttsnotifications.R;
import com.prochnow.ttsnotifications.model.AppInfo;
import com.prochnow.ttsnotifications.model.DatabaseHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prochnow on 24.06.15.
 */
public class OptionsRecyclerViewAdapter extends RecyclerView.Adapter<OptionsRecyclerViewAdapter.ViewHolder> {
    private final String LOG_TAG = OptionsRecyclerViewAdapter.class.getSimpleName();
    private AppInfo app;
    private String[] optionsText;
    private Context context;
    private DatabaseHelper databaseHelper;


    public OptionsRecyclerViewAdapter(Context context, AppInfo app) {
        optionsText = context.getResources().getStringArray(R.array.notificationOutput);
        this.context = context;
        this.app = app;
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper;
    }


    // Provide a reference to the views for each data item
    // Complex data filteredList may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @Bind(R.id.optionText) TextView optionName;
        @Bind(R.id.optionSwitch) CheckBox optionCheckBox;

        Context context;

        private final String LOG_TAG = ViewHolder.class.getSimpleName();

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }


    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_notification_option, null);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.optionName.setText(optionsText[position]);
        switch (position) {
            case 0:
                holder.optionCheckBox.setChecked(app.getNotification().isPackageNameOutput());
                holder.optionCheckBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        app.getNotification().setPackageNameOutput(!app.getNotification().isPackageNameOutput());
                        getHelper().getNotificationTypeRuntimeDao().update(app.getNotification());

                    }
                });
                break;
            case 1:
                holder.optionCheckBox.setChecked(app.getNotification().isNotificationTitleOutput());
                holder.optionCheckBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        app.getNotification().setNotificationTitleOutput(!app.getNotification().isNotificationTitleOutput());
                        getHelper().getNotificationTypeRuntimeDao().update(app.getNotification());

                    }
                });
                break;
            case 2:
                holder.optionCheckBox.setChecked(app.getNotification().isNotificationTextOutput());
                holder.optionCheckBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        app.getNotification().setNotificationTextOutput(!app.getNotification().isNotificationTextOutput());
                        getHelper().getNotificationTypeRuntimeDao().update(app.getNotification());

                    }
                });
                break;
            case 3:
                holder.optionCheckBox.setChecked(app.getNotification().isNotificationSubtextOutput());
                holder.optionCheckBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        app.getNotification().setNotificationSubtextOutput(!app.getNotification().isNotificationSubtextOutput());
                        getHelper().getNotificationTypeRuntimeDao().update(app.getNotification());

                    }
                });
                break;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return optionsText.length;
    }

}
