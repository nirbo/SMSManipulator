package org.nirbo.smsmanipulator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.nirbo.smsmanipulator.R;

public class SettingsListAdapter extends ArrayAdapter<String> {

    Context context;
    String[] settingNames;
    String[] settingDescriptions;

    public SettingsListAdapter(Context context, String[] settingNames, String[] settingDescriptions) {
        super(context, R.layout.settings_list_row, R.id.setting_name, settingNames);

        this.context = context;
        this.settingNames = settingNames;
        this.settingDescriptions = settingDescriptions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View settingsRow = inflater.inflate(R.layout.settings_list_row, parent, false);
        TextView settingName = (TextView) settingsRow.findViewById(R.id.setting_name);
        TextView settingDescription = (TextView) settingsRow.findViewById(R.id.setting_description);

        settingName.setText(settingNames[position]);
        settingDescription.setText(settingDescriptions[position]);

        return settingsRow;
    }
}
