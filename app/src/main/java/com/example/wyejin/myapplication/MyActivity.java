package com.example.wyejin.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.WindowManager;
import android.widget.Toast;


import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.example.wyejin.myapplication.async.SyncData;
import com.example.wyejin.myapplication.async.SyncData2;
import com.example.wyejin.myapplication.frag.PlaceholderFragment;

import com.androidplot.xy.XYSeries;

import java.util.Arrays;

public class MyActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    //http://www.tutorialspoint.com/android/android_fragments.htm
    //http://www.androidhive.info/2013/11/android-working-with-action-bar/

    private XYPlot plot;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private MenuItem refreshMenuItem; // Refresh menu item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                // fun little snippet that prevents users from taking screenshots
                // on ICS+ devices :-)
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                        WindowManager.LayoutParams.FLAG_SECURE);

                setContentView(R.layout.test_xy_plot);

                // initialize our XYPlot reference:
                plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

                // Create a couple arrays of y-values to plot:
                Number[] series1Numbers = {1, 8, 5, 2, 7, 4};
                Number[] series2Numbers = {4, 6, 3, 8, 2, 10};

                // Turn the above arrays into XYSeries':
                XYSeries series1 = new SimpleXYSeries(
                        Arrays.asList(series1Numbers),          // SimpleXYSeries takes a List so turn our array into a List
                        SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
                        "Series1");                             // Set the display title of the series

                // same as above
                XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");

                // Create a formatter to use for drawing a series using LineAndPointRenderer
                // and configure it from xml:
                LineAndPointFormatter series1Format = new LineAndPointFormatter();
                series1Format.setPointLabelFormatter(new PointLabelFormatter());
                series1Format.configure(getApplicationContext(),
                        R.xml.line_point_formatter_with_plf1);

                // add a new series' to the xyplot:
                plot.addSeries(series1, series1Format);

                // same as above:
                LineAndPointFormatter series2Format = new LineAndPointFormatter();
                series2Format.setPointLabelFormatter(new PointLabelFormatter());
                series2Format.configure(getApplicationContext(),
                        R.xml.line_point_formatter_with_plf2);
                plot.addSeries(series2, series2Format);

                // reduce the number of range labels
                plot.setTicksPerRangeLabel(3);
                plot.getGraphWidget().setDomainLabelOrientation(-45);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.my, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_refresh:
                Toast.makeText(this, "Refresh Clicked", Toast.LENGTH_SHORT).show();
                // refresh
                refreshMenuItem = item;
                // load the data from server
                new SyncData(refreshMenuItem).execute();
                return true;
            case R.id.action_refresh2:
                Toast.makeText(this, "Refresh 2 Clicked", Toast.LENGTH_SHORT).show();
                refreshMenuItem = item;
                new SyncData2(this, item).execute();
            default:
                return super.onOptionsItemSelected(item);
        }

    }



}
