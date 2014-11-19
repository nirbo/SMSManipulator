package org.nirbo.smsmanipulator.listeners;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.nirbo.smsmanipulator.MainActivity;
import org.nirbo.smsmanipulator.R;
import org.nirbo.smsmanipulator.fragments.HomeFragment;
import org.nirbo.smsmanipulator.fragments.SettingsFragment;

public class NavDrawerOnItemClickListener implements AdapterView.OnItemClickListener {

    private Activity mContext;
    private String[] mDrawerEntries;
    private Toolbar mToolbar;
    private DrawerLayout mNavDrawer;
    private FragmentManager fm;

    public NavDrawerOnItemClickListener(Activity context, String[] drawerEntries, Toolbar toolbar) {
        this.mContext = context;
        this.mDrawerEntries = drawerEntries;
        this.mToolbar = toolbar;

        mNavDrawer = (DrawerLayout) context.findViewById(R.id.drawer_layout);
        fm = mContext.getFragmentManager();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectDrawerItem(position, parent, view);
    }

    public void selectDrawerItem(int position, AdapterView<?> parent, View item) {
        Fragment fragment = null;
        String fragmentTag = null;
        boolean addToBackStack = false;
        ListView drawerList = (ListView) parent.findViewById(R.id.drawer_list);

        switch (position) {
            case 0:
                fragment = new HomeFragment();
                fragmentTag = HomeFragment.FRAGMENT_TAG;
                break;

            case 1:
                fragment = new SettingsFragment();
                fragmentTag = SettingsFragment.FRAGMENT_TAG;
                break;

            case 2:
                mContext.finish();

            default:
                break;
        }

        MainActivity.loadFragment(fragment, fragmentTag, addToBackStack);

        item.setSelected(true);
        drawerList.setSelection(position);
        drawerList.setItemChecked(position, true);
        mToolbar.setTitle(mDrawerEntries[position]);
        mNavDrawer.closeDrawer(mContext.findViewById(R.id.drawer_list));
    }


}
