package com.prochnow.ttsnotifications.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    Context context;

    private final String LOG_TAG = ActiveAppsRecyclerViewAdapter.class.getSimpleName();

    public ActiveAppsRecyclerViewAdapter() {

    }

    public void setData(List<AppInfo> list) {
        this.originalList = list;
    }


    // Provide a reference to the views for each data item
    // Complex data filteredList may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @Bind(R.id.appName) TextView appName;
        @Bind(R.id.appIcon) ImageView appIcon;
        @Bind(R.id.appCheckbox) Switch appCheckbox;
        @Bind(R.id.activityModeButton) Button modeButton;

        Context context;


        private final String LOG_TAG = ViewHolder.class.getSimpleName();

        public ViewHolder(View v) {
            super(v);
            this.context = context;
            ButterKnife.bind(this, v);
        }

    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_activeapps, null);
        ViewHolder vh = new ViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AppInfo info = originalList.get(position);

        holder.appIcon.setImageDrawable(info.getIcon());
        holder.appName.setText(info.getName());
        holder.appCheckbox.setChecked(info.isSelected());
        holder.modeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final CharSequence[] testArray = {"Notification Text", "Custom Text"};

                builder.setTitle("Select mode of output").setItems(testArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(LOG_TAG, "onClick pressed" + testArray[which]);
                        Log.d(LOG_TAG, "onClick id" + position);
                        Log.d(LOG_TAG, "onClick " + originalList.get(position).getName());
                    }
                });
                builder.create().show();
            }
        });

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return originalList.size();
    }

}
