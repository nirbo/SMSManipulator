package org.nirbo.smsmanipulator;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import org.nirbo.smsmanipulator.adapters.SlidingTabsAdapter;

public class MainActivity extends FragmentActivity {

    ActionBar mActionBar;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initActionBar();
        initTabStrip();
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        return true;
//    }

    private void initActionBar() {
        mActionBar = getActionBar();
        mActionBar.setDisplayShowTitleEnabled(true);
    }

    private void initTabStrip() {
        viewPager = (ViewPager) findViewById(R.id.main_viewPager);
        viewPager.setAdapter(new SlidingTabsAdapter(getSupportFragmentManager()));
    }

    public void onQuitClick() {
        this.finish();
    }
}
