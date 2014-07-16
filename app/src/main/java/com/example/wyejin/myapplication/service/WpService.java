package com.example.wyejin.myapplication.service;

import com.example.wyejin.myapplication.object.Post;
import com.example.wyejin.myapplication.object.Posts;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by wye.jin on 16/7/2014.
 *
 * http://square.github.io/retrofit/
 */
public interface WpService {
    @GET("/wp_api/v1/posts")
    Posts listPosts(@Query("per_page") int perPage, @Query("post_type") String postType );
}

