package org.nirbo.smsmanipulator.listeners;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import org.nirbo.smsmanipulator.R;
import org.nirbo.smsmanipulator.fragments.HomeFragment;
import org.nirbo.smsmanipulator.fragments.SettingsFragment;

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String itemLabel = parent.getItemAtPosition(position).toString();
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;

            case 1:
                fragment = new SettingsFragment();
                break;

            case 2:
                mContext.finish();

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fm = mContext.getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.main_container, fragment);
            ft.commit();

            selectDrawerItem(position, parent);
        }
    }

    public void selectDrawerItem(int position, AdapterView<?> parent) {
        parent.setSelection(position);
        setToolbarTitle(mToolbar, mDrawerEntries[position]);
        mNavDrawer.closeDrawers();
    }

    public void setToolbarTitle(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
    }

}