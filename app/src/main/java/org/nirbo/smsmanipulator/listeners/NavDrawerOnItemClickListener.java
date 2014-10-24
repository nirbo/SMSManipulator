package org.nirbo.smsmanipulator.listeners;

import android.app.Activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import org.nirbo.smsmanipulator.R;

public class NavDrawerOnItemClickListener implements AdapterView.OnItemClickListener{

    private Activity mContext;
    private String[] mDrawerEntries;
    private Toolbar mToolbar;
    private DrawerLayout mNavDrawer;

    public NavDrawerOnItemClickListener(Activity context, String[] drawerEntries, Toolbar toolbar) {
        this.mContext = context;
        this.mDrawerEntries = drawerEntries;
        this.mToolbar = toolbar;

        mNavDrawer = (DrawerLayout) context.findViewById(R.id.drawer_layout);
    }

    @Override
    public void onItemClick(AdapterView<?> parentView, View view, int position, long id) {
        String itemLabel = parentView.getItemAtPosition(position).toString();

        if (! "Quit".equals(itemLabel)) {
            selectDrawerItem(position);
        } else {
            mContext.finish();
        }


    }

    public void selectDrawerItem(int position) {
        setToolbarTitle(mToolbar, mDrawerEntries[position]);
        mNavDrawer.closeDrawers();
    }

    public void setToolbarTitle(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
    }

}
