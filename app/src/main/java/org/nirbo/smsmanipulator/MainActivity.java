package org.nirbo.smsmanipulator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

import com.j256.ormlite.android.apptools.OpenHelperManager;

import org.nirbo.smsmanipulator.dao.DBHelper;
import org.nirbo.smsmanipulator.fragments.HomeFragment;
import org.nirbo.smsmanipulator.listeners.NavDrawerOnItemClickListener;
import org.nirbo.smsmanipulator.listeners.NavDrawerToggleListener;

public class MainActivity extends ActionBarActivity {

    public static DBHelper dbHelper;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private String[] mDrawerEntries;
    private NavDrawerOnItemClickListener mNavDrawerItemListener;
    private ActionBarDrawerToggle mDrawerListener;
    private static FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        fm = getFragmentManager();
        dbHelper = getDBHelper();
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

    public static void loadFragment(Fragment fragment, String fragmentTag, boolean addToBackStack) {
        if (fragment != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.fade_in_fragment, R.anim.fade_out_fragment);
            ft.replace(R.id.main_container, fragment, fragmentTag);
            if (addToBackStack) {
                ft.addToBackStack(fragmentTag);
            }
            ft.commit();
        }
    }

    private void initHomeFragment() {
        loadFragment(new HomeFragment(), HomeFragment.FRAGMENT_TAG, false);
    }

    private DBHelper getDBHelper() {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        }

        return dbHelper;
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

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }
}
