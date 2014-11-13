package org.nirbo.smsmanipulator.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import org.nirbo.smsmanipulator.MainActivity;
import org.nirbo.smsmanipulator.R;
import org.nirbo.smsmanipulator.dao.DBHelper;
import org.nirbo.smsmanipulator.model.Target;

import java.util.List;

public class TargetPickerRecyclerAdapter
        extends RecyclerView.Adapter<TargetPickerRecyclerAdapter.TargetsViewHolder> {

    private DBHelper dbHelper;
    private Activity mContext;
    private List<Target> mTargets;
    private RuntimeExceptionDao targetDao;

    public TargetPickerRecyclerAdapter(Activity context) {
        this.dbHelper = MainActivity.dbHelper;
        this.mContext = context;

        targetDao = null;
        targetDao = dbHelper.getTargetExceptionDao();

        Target test = new Target();
        test.setContactName("TEST 1");
        test.setContactNumber("12345678");
        targetDao.create(test);

        this.mTargets = targetDao.queryForAll();
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
        targetsViewHolder.mTargetName.setText(target.getContactName());
        targetsViewHolder.mTargetNumber.setText(target.getContactNumber());
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
            mTargetName = (TextView) itemView.findViewById(R.id.target_picker_name);
            mTargetNumber = (TextView) itemView.findViewById(R.id.target_picker_number);
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
