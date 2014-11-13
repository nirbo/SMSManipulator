package org.nirbo.smsmanipulator.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.nirbo.smsmanipulator.R;
import org.nirbo.smsmanipulator.adapters.TargetPickerRecyclerAdapter;

public class TargetsFragment extends Fragment {

    public static final String FRAGMENT_TAG = "targetsList";

    Activity mContext;
    RecyclerView list;

    public TargetsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        mContext = getActivity();
        View targetsList = inflater.inflate(R.layout.target_picker_fragment, parent, false);
        list = (RecyclerView) targetsList.findViewById(R.id.target_picker_recycler);

        initRecyclerView();

        return targetsList;
    }

    private void initRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(llm);

        TargetPickerRecyclerAdapter adapter = new TargetPickerRecyclerAdapter(mContext);
        list.setAdapter(adapter);
    }

}
