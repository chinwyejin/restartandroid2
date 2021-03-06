package com.example.wyejin.myapplication.async;

import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem;
import com.example.wyejin.myapplication.R;
import com.example.wyejin.myapplication.object.Post;
import com.example.wyejin.myapplication.object.Posts;
import com.example.wyejin.myapplication.service.WpService;

import java.util.List;

import retrofit.RestAdapter;

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
        //m_item.setActionView(R.layout.action_progressbar);

        //m_item.expandActionView();
        MenuItemCompat.setActionView(m_item, R.layout.action_progressbar);
        MenuItemCompat.expandActionView(m_item);
    }

    @Override
    protected String doInBackground(String... params) {
        // not making real request in this demo
        // for now we use a timer to wait for sometime
//        try {
//            Thread.sleep(3000);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        RunTask();
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        //m_item.collapseActionView();
        // remove the progress bar view
        //m_item.setActionView(null);
        MenuItemCompat.collapseActionView(m_item);
        MenuItemCompat.setActionView(m_item,null);

    }

    private void RunTask(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://chinwyejin.com")
                .build();

        restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        WpService wpService = restAdapter.create(WpService.class);
        Posts posts = wpService.listPosts(20, "post");
        for(Post post : posts.list){
        System.out.println(post.name);
        }
    }
}
