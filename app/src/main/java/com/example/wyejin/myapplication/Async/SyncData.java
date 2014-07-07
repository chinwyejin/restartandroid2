package com.example.wyejin.myapplication.Async;

import android.os.AsyncTask;
import android.view.MenuItem;
import com.example.wyejin.myapplication.R;

/**
 * Created by Jin on 7/7/2014.
 */
public class SyncData extends AsyncTask<String, Void, String> {

    private MenuItem m_item = null;

    public SyncData (MenuItem item){
        this.m_item=item;
    }

    @Override
    protected void onPreExecute() {
        // set the progress bar view
        m_item.setActionView(R.layout.action_progressbar);

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
