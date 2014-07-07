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
        //refresh();
        runRefresh();
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
        stopRefresh();
        //completeRefresh();
    }

    protected void stopRefresh() {
        if (m_item != null) {
            m_item.setActionView(null);
        }
    }

    protected void runRefresh() {
        if (m_item != null) {
            m_item.setActionView(R.layout.action_refresh2);
        }
    }


    public void refresh() {
     /* Attach a rotating ImageView to the refresh item as an ActionView */
        LayoutInflater inflater = (LayoutInflater)  m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ImageView iv = (ImageView) inflater.inflate(R.layout.action_refresh2, null);

        Animation rotation = AnimationUtils.loadAnimation(m_context, R.anim.anim_refresh);
        rotation.setRepeatCount(Animation.INFINITE);
        iv.startAnimation(rotation);

        m_item.setActionView(iv);

        //TODO trigger loading
    }

    public void completeRefresh() {
        m_item.getActionView().clearAnimation();
        m_item.setActionView(null);
    }
}
