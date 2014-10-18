package org.nirbo.smsmanipulator.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.nirbo.smsmanipulator.R;
import org.nirbo.smsmanipulator.adapters.SettingsListAdapter;

public class SettingsFragment extends Fragment {

    Context context;
    String[] settingNames;
    String[] settingDescriptions;
    ListView listView;

    public SettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View settingsList = inflater.inflate(R.layout.settings_fragment, container, false);
        context = getActivity();
        Resources res = getResources();
        settingNames = res.getStringArray(R.array.settingNames);
        settingDescriptions = res.getStringArray(R.array.settingDescriptions);

        listView = (ListView) settingsList.findViewById(R.id.settings_listview);
        SettingsListAdapter listAdapter = new SettingsListAdapter(context, settingNames, settingDescriptions);
        listView.setAdapter(listAdapter);

        return settingsList;
    }



}
