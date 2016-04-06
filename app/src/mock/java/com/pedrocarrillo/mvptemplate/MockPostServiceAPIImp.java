package com.pedrocarrillo.mvptemplate;

import com.pedrocarrillo.mvptemplate.model.Post;
import com.pedrocarrillo.mvptemplate.networking.services.PostServiceAPI;
import com.pedrocarrillo.mvptemplate.util.API;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by PedroCarrillo on 4/6/16.
 */
public class MockPostServiceAPIImp implements PostServiceAPI {

    @Override
    public void getPosts(final PostServiceAPI.PostServiceCallback<List<Post>> callback, int offset) {
        List<Post> postList = new ArrayList<>();
        Post post1 = new Post();
        post1.setId("1");
        post1.setBody("mock");
        post1.setTitle("mock title");
        post1.setUserId(1);
        postList.add(post1);
        callback.onLoaded(postList);
    }
}