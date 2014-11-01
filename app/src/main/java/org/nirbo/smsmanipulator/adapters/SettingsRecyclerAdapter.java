package org.nirbo.smsmanipulator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.nirbo.smsmanipulator.R;
import org.nirbo.smsmanipulator.UI.SettingsFragmentRow;

import java.util.List;

public class SettingsRecyclerAdapter extends RecyclerView.Adapter<SettingsRecyclerAdapter.SettingsViewHolder> {

    private List<SettingsFragmentRow> mSettingsRows;

    public SettingsRecyclerAdapter(List<SettingsFragmentRow> settingsRows) {
        this.mSettingsRows = settingsRows;
    }

    @Override
    public SettingsViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.settings_recycler_card, parent, false);

        return new SettingsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SettingsViewHolder settingsViewHolder, int position) {
        SettingsFragmentRow row = mSettingsRows.get(position);
        settingsViewHolder.mSettingName.setText(row.getSettingName());
        settingsViewHolder.mSettingDescription.setText(row.getSettingDescription());
    }

    @Override
    public int getItemCount() {
        return mSettingsRows.size();
    }

    // ViewHolder Class
    public class SettingsViewHolder extends RecyclerView.ViewHolder {

        protected TextView mSettingName;
        protected TextView mSettingDescription;

        public SettingsViewHolder(View itemView) {
            super(itemView);
            mSettingName = (TextView) itemView.findViewById(R.id.setting_name);
            mSettingDescription = (TextView) itemView.findViewById(R.id.setting_description);
        }
    }

}
