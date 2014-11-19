package org.nirbo.smsmanipulator.adapters;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.nirbo.smsmanipulator.MainActivity;
import org.nirbo.smsmanipulator.R;
import org.nirbo.smsmanipulator.UI.SettingsFragmentRow;
import org.nirbo.smsmanipulator.fragments.TargetsFragment;

import java.util.List;

public class SettingsRecyclerAdapter extends RecyclerView.Adapter<SettingsRecyclerAdapter.SettingsViewHolder> {

    private Activity mContext;
    private List<SettingsFragmentRow> mSettingsRows;

    public SettingsRecyclerAdapter(List<SettingsFragmentRow> settingsRows, Activity context) {
        this.mContext = context;
        this.mSettingsRows = settingsRows;
    }

    @Override
    public SettingsViewHolder onCreateViewHolder(ViewGroup parent, final int position) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.settings_recycler_card, parent, false);

        SettingsViewHolder viewHolder = new SettingsViewHolder(itemView,
                new SettingsViewHolder.OnItemClickListener() {

            Fragment fragment = null;
            String fragmentTag = null;
            boolean addToBackStack = false;

            @Override
            public void OnItemClick(View item, int position) {
                switch (position) {
                    case 0:
                        fragment = new TargetsFragment();
                        fragmentTag = TargetsFragment.FRAGMENT_TAG;
                        addToBackStack = true;

                    case 1:
                        break;

                    default:
                        break;
                }

                MainActivity.loadFragment(fragment, fragmentTag, addToBackStack);
                Toast.makeText(mContext, "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        return viewHolder;
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
    public static class SettingsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        protected TextView mSettingName;
        protected TextView mSettingDescription;
        protected OnItemClickListener mItemClickListener;

        public SettingsViewHolder(View itemView, OnItemClickListener itemClickListener) {
            super(itemView);
            mSettingName = (TextView) itemView.findViewById(R.id.setting_name);
            mSettingDescription = (TextView) itemView.findViewById(R.id.setting_description);
            mItemClickListener = itemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.OnItemClick(view, getPosition());
        }

        public static interface OnItemClickListener {
            public void OnItemClick(View item, int position);
        }
    }

}
