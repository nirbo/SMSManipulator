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
        View settingsRow = convertView;
        ListViewHolder viewHolder = null;

        if (settingsRow == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            settingsRow = inflater.inflate(R.layout.settings_list_row, parent, false);
            viewHolder = new ListViewHolder(settingsRow);
            settingsRow.setTag(viewHolder);
        } else {
            viewHolder = (ListViewHolder) settingsRow.getTag();
        }

        viewHolder.settingName.setText(settingNames[position]);
        viewHolder.settingDescription.setText(settingDescriptions[position]);

        return settingsRow;
    }


    
    private class ListViewHolder {

        TextView settingName;
        TextView settingDescription;

        protected ListViewHolder(View view) {
            settingName = (TextView) view.findViewById(R.id.setting_name);
            settingDescription = (TextView) view.findViewById(R.id.setting_description);
        }
    }
}
