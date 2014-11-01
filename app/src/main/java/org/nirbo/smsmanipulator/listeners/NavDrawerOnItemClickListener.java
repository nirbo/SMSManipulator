package org.nirbo.smsmanipulator.listeners;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import org.nirbo.smsmanipulator.MainActivity;
import org.nirbo.smsmanipulator.R;
import org.nirbo.smsmanipulator.fragments.HomeFragment;
import org.nirbo.smsmanipulator.fragments.SettingsFragment;

public class NavDrawerOnItemClickListener implements AdapterView.OnItemClickListener,
        FragmentManager.OnBackStackChangedListener {

    private Activity mContext;
    private String[] mDrawerEntries;
    private Toolbar mToolbar;
    private DrawerLayout mNavDrawer;
    private FragmentManager fm;
    private FragmentTransaction ft;

    public NavDrawerOnItemClickListener(Activity context, String[] drawerEntries, Toolbar toolbar) {
        this.mContext = context;
        this.mDrawerEntries = drawerEntries;
        this.mToolbar = toolbar;

        mNavDrawer = (DrawerLayout) context.findViewById(R.id.drawer_layout);
        fm = mContext.getFragmentManager();
        fm.addOnBackStackChangedListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment = null;
        String fragmentTag = null;

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

        if (fragment != null) {
            ft = fm.beginTransaction();
            replaceFragment(fragment, fragmentTag);
            selectDrawerItem(position, parent);
        }
    }

    public void selectDrawerItem(int position, AdapterView<?> parent) {
        parent.setSelection(position);
        setToolbarTitle(mToolbar, mDrawerEntries[position]);
        mNavDrawer.closeDrawers();
    }

    private void replaceFragment(Fragment fragment, String tag) {
        String fragmentName = fragment.getClass().getName();
        boolean fragmentPopped = fm.popBackStackImmediate(fragmentName, 0);

        if (! fragmentPopped) {
            ft.replace(R.id.main_container, fragment, tag);
            ft.addToBackStack(fragmentName);
            ft.commit();
        }
    }

    public void setToolbarTitle(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void onBackStackChanged() {
        int backStackCount = fm.getBackStackEntryCount();

        Log.d("NIR", "Count: " + backStackCount);
    }

}
