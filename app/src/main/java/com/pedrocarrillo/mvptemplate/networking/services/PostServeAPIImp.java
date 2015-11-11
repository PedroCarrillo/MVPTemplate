package com.pedrocarrillo.mvptemplate.networking.services;

import com.pedrocarrillo.mvptemplate.model.Post;
import com.pedrocarrillo.mvptemplate.networking.services.PostServiceAPI;
import com.pedrocarrillo.mvptemplate.util.API;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * @author pcarrillo
 *         on 10/11/2015 for MVPtemplate.
 */
public class PostServeAPIImp implements PostServiceAPI {

    @Override
    public void getPosts(final PostServiceCallback<List<Post>> callback, int offset) {
        Call<List<Post>> callListPosts = API.get().getRetrofitService().getPosts(offset);
        callListPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Response<List<Post>> response) {
                callback.onLoaded(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                // TODO Implement generic alert dialog.
            }
        });
    }


}
