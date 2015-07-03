package com.prochnow.ttsnotifications.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.prochnow.ttsnotifications.R;
import com.prochnow.ttsnotifications.dialog.SelectModeDialog;
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
    private FragmentActivity activity;

    public ActiveAppsRecyclerViewAdapter(FragmentActivity activity) {

        this.activity = activity;
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
        @Bind(R.id.modeButton) Button modeButton;
        @Bind(R.id.modeText) TextView modeText;
        @Bind(R.id.prefButton) Button prefButton;
        @Bind(R.id.prefText) TextView prefText;

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
        final AppInfo info = originalList.get(position);

        holder.appIcon.setImageDrawable(info.getIcon());
        holder.appName.setText(info.getName());
        holder.appCheckbox.setChecked(info.isSelected());

        if (info.isTemplateMode()) {
            holder.modeText.setText(context.getResources().getStringArray(R.array.outputModeName)[1]);
        } else {
            holder.modeText.setText(context.getResources().getStringArray(R.array.outputModeName)[0]);
        }

        {
            holder.modeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SelectModeDialog dialog = new SelectModeDialog();
                    Bundle bundle = new Bundle();
                    bundle.putString("package", info.getPackageString());
                    dialog.setArguments(bundle);
                    dialog.show(activity.getSupportFragmentManager(), "");
                }
            });
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return originalList.size();
    }

}
