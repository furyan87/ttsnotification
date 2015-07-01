package com.prochnow.ttsnotifications.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.prochnow.ttsnotifications.R;
import com.prochnow.ttsnotifications.model.AppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by prochnow on 24.06.15.
 */
public class AppListRecyclerViewAdapter extends RecyclerView.Adapter<AppListRecyclerViewAdapter.ViewHolder> implements Filterable {
    public List<AppInfo> originalList = new ArrayList<>();
    public List<AppInfo> filteredList = new ArrayList<>();

    private final String LOG_TAG = AppListRecyclerViewAdapter.class.getSimpleName();

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults results = new FilterResults();
                List<AppInfo> filteredResults = new ArrayList<>();

                if (charSequence.length() == 0) {
                    filteredResults.addAll(originalList);
                } else {
                    final String filterPattern = charSequence.toString().toLowerCase().trim();

                    for (AppInfo appInfo : originalList) {
                        if (appInfo.getName().toLowerCase().contains(filterPattern)) {
                            filteredResults.add(appInfo);
                        }
                    }
                }
                results.values = filteredResults;
                results.count = filteredResults.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList.clear();
                filteredList.addAll((ArrayList<AppInfo>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public interface OnItemClickListener {
        void onListItemClick(int position);
    }

    OnItemClickListener listener = new OnItemClickListener() {
        @Override
        public void onListItemClick(int position) {
            filteredList.get(position).toggleSelection();
        }
    };

    public AppListRecyclerViewAdapter() {

    }

    public void setData(List<AppInfo> list) {
        this.originalList = list;
        this.filteredList.addAll(originalList);
    }


    // Provide a reference to the views for each data item
    // Complex data filteredList may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        @Bind(R.id.appName) TextView appName;
        @Bind(R.id.appIcon) ImageView appIcon;
        @Bind(R.id.appCheckbox) CheckBox appCheckbox;
        OnItemClickListener click;

        private final String LOG_TAG = ViewHolder.class.getSimpleName();

        public ViewHolder(View v, OnItemClickListener click) {
            super(v);
            ButterKnife.bind(this, v);
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_addapp, null);
        ViewHolder vh = new ViewHolder(v, listener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppInfo info = filteredList.get(position);

        holder.appIcon.setImageDrawable(info.getIcon());
        holder.appName.setText(info.getName());
        holder.appCheckbox.setChecked(info.isSelected());
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return filteredList.size();
    }

}
