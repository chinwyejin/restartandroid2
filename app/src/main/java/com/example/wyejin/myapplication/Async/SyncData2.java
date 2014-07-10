package com.example.wyejin.myapplication.Async;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.wyejin.myapplication.R;

/**
 * Created by Jin on 7/7/2014.
 */
public class SyncData2 extends AsyncTask<String, Void, String> {

    private Activity m_context;
    private MenuItem m_item;

    public SyncData2 (Activity context, MenuItem item){
        this.m_context=context;
        this.m_item=item;
    }

    @Override
    protected void onPreExecute() {
        // set the progress bar view
        m_item.setActionView(R.layout.action_refresh2);

        m_item.expandActionView();
    }

    @Override
    protected String doInBackground(String... params) {
        // not making real request in this demo
        // for now we use a timer to wait for sometime
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        m_item.collapseActionView();
        // remove the progress bar view
        m_item.setActionView(null);
    }
}
