package org.nirbo.smsmanipulator;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.nirbo.smsmanipulator.fragments.HomeFragment;
import org.nirbo.smsmanipulator.listeners.NavDrawerOnItemClickListener;
import org.nirbo.smsmanipulator.listeners.NavDrawerToggleListener;

public class MainActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private String[] mDrawerEntries;
    private NavDrawerOnItemClickListener mNavDrawerItemListener;
    private ActionBarDrawerToggle mDrawerListener;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        fm = getFragmentManager();
        hideStatusBar();
        initToolbar();
        initNavDrawer();
        initHomeFragment();
    }

    private void hideStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.home_title);
        }
    }

    private void initNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerListView = (ListView) findViewById(R.id.drawer_list);
        mDrawerEntries = getResources().getStringArray(R.array.navDrawerTitles);

        mDrawerListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, mDrawerEntries));
        mNavDrawerItemListener = new NavDrawerOnItemClickListener(this, mDrawerEntries, mToolbar);
        mDrawerListView.setOnItemClickListener(mNavDrawerItemListener);

        mDrawerListener = new NavDrawerToggleListener(this, mDrawerLayout, mToolbar, R.string.open_drawer, R.string.close_drawer);
        mDrawerLayout.setDrawerListener(mDrawerListener);
    }

    private void initHomeFragment() {
        getFragmentManager().beginTransaction()
                .add(R.id.main_container, new HomeFragment(), HomeFragment.FRAGMENT_TAG)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerListener.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerListener.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerListener.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (! mDrawerLayout.isDrawerOpen(mDrawerListView)) {
                mDrawerLayout.openDrawer(mDrawerListView);
            } else {
                mDrawerLayout.closeDrawer(mDrawerListView);
            }
        }

        return true;
    }

}
