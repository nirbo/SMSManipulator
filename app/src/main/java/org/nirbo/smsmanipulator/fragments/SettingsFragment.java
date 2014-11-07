package org.nirbo.smsmanipulator.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import org.nirbo.smsmanipulator.R;
import org.nirbo.smsmanipulator.UI.SettingsFragmentRow;
import org.nirbo.smsmanipulator.adapters.SettingsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String FRAGMENT_TAG = "settings";

    Activity mContext;
    RecyclerView list;
    String[] mSettingNames;
    String[] mSettingDescriptions;
    List<SettingsFragmentRow> itemsList;


    public SettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        mContext = getActivity();
        View settingsList = inflater.inflate(R.layout.settings_fragment, parent, false);
        list = (RecyclerView) settingsList.findViewById(R.id.settings_recycler);

        Resources res = getResources();
        mSettingNames = res.getStringArray(R.array.settingNames);
        mSettingDescriptions = res.getStringArray(R.array.settingDescriptions);

        initRecyclerView();

        return settingsList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        String row = adapter.getItemAtPosition(position).toString();
        Toast.makeText(mContext, row, Toast.LENGTH_LONG).show();
    }

    private void initRecyclerView() {
        list.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(llm);

        itemsList = initViewItems(mSettingNames, mSettingDescriptions);
        SettingsRecyclerAdapter adapter = new SettingsRecyclerAdapter(itemsList, mContext);
        list.setAdapter(adapter);
    }

    private List<SettingsFragmentRow> initViewItems(String[] names, String[] descriptions) {
        List<SettingsFragmentRow> items = new ArrayList<SettingsFragmentRow>();

        for (int position = 0; position < names.length; position++) {
            items.add(new SettingsFragmentRow(names[position], descriptions[position]));
        }

        return items;
    }

}
