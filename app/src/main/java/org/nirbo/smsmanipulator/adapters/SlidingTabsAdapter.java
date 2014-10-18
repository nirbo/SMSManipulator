package org.nirbo.smsmanipulator.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.nirbo.smsmanipulator.fragments.HomeFragment;
import org.nirbo.smsmanipulator.fragments.SettingsFragment;

public class SlidingTabsAdapter extends FragmentStatePagerAdapter {

    public SlidingTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment currentFragment = null;

        if (i == 0) {
            currentFragment = new HomeFragment();
        }

        if (i == 1) {
            currentFragment = new SettingsFragment();
        }

        return currentFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Home";
        }

        if (position == 1) {
            return "Settings";
        }

        return null;
    }
}
