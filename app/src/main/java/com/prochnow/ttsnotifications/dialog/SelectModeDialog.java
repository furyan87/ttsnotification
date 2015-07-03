package com.prochnow.ttsnotifications.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.prochnow.ttsnotifications.NotificationPrefsActivity;
import com.prochnow.ttsnotifications.R;
import com.prochnow.ttsnotifications.model.AppInfo;
import com.prochnow.ttsnotifications.model.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prochnow on 03.07.15.
 */
public class SelectModeDialog extends DialogFragment {

    private String packageName;
    AppInfo info;
    DatabaseHelper helper;

    public SelectModeDialog() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        packageName = getArguments().getString("package");
        helper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        List<AppInfo> apps = new ArrayList<>();
        try {
            apps = helper.getAppInfoRuntimeDao().queryBuilder().where().eq(AppInfo.PACKAGE_NAME, packageName).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (apps.size() > 0) {
            info = apps.get(0);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Select mode of output").setItems(getActivity().getResources().getStringArray(R.array.outputModeName), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                boolean isTemplateMode = info.isTemplateMode();
                switch (which) {
                    case 0:
                        info.setTemplateMode(false);
                        if (isTemplateMode) {
                            Intent intent = new Intent(getActivity(), NotificationPrefsActivity.class);
                            intent.putExtra("package", info.getPackageString());
                            intent.putExtra("template", false);
                            getActivity().startActivity(intent);
                        }

                        break;
                    case 1:
                        info.setTemplateMode(true);
                        if (isTemplateMode == false) {
                            Intent intent = new Intent(getActivity(), NotificationPrefsActivity.class);
                            intent.putExtra("package", info.getPackageString());
                            intent.putExtra("template", true);
                            getActivity().startActivity(intent);
                        }

                        break;
                }

            }
        });
        return builder.create();
    }
}
