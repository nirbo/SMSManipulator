package org.nirbo.smsmanipulator.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.nirbo.smsmanipulator.R;
import org.nirbo.smsmanipulator.UI.SettingsFragmentRow;
import org.nirbo.smsmanipulator.model.Target;

import java.util.List;

public class TargetPickerRecyclerAdapter
        extends RecyclerView.Adapter<TargetPickerRecyclerAdapter.TargetsViewHolder> {

    private Activity mContext;
    private List<Target> mTargets;

    public TargetPickerRecyclerAdapter(List<Target> targets, Activity context) {
        this.mContext = context;
        this.mTargets = targets;
    }

    @Override
    public TargetsViewHolder onCreateViewHolder(ViewGroup parent, final int position) {
        View targetItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.target_picker_card, parent, false);

        TargetsViewHolder viewHolder = new TargetsViewHolder(targetItem,
                new TargetsViewHolder.OnItemClickListener() {

                    @Override
                    public void OnItemClick(View item, int position) {
                        Toast.makeText(mContext, "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
                    }
                });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TargetsViewHolder targetsViewHolder, int position) {
        Target target = mTargets.get(position);
        targetsViewHolder.mTargetName.setText(target.getSettingName());
        targetsViewHolder.mTargetNumber.setText(target.getSettingDescription());
    }

    @Override
    public int getItemCount() {
        return mTargets.size();
    }

    // ViewHolder Class
    public static class TargetsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        protected TextView mTargetName;
        protected TextView mTargetNumber;
        protected OnItemClickListener mItemClickListener;

        public TargetsViewHolder(View itemView, OnItemClickListener itemClickListener) {
            super(itemView);
            mTargetName = (TextView) itemView.findViewById(R.id.setting_name);
            mTargetNumber = (TextView) itemView.findViewById(R.id.setting_description);
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
