package com.pedrocarrillo.mvptemplate.ui.posts;

import com.pedrocarrillo.mvptemplate.model.Post;
import com.pedrocarrillo.mvptemplate.networking.interactors.PostInteractor;
import com.pedrocarrillo.mvptemplate.networking.interfaces.PostLoadedListener;
import com.pedrocarrillo.mvptemplate.util.API;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * @author pcarrillo
 *         on 10/11/2015 for MVPtemplate.
 */
public class PostInteractorImp implements PostInteractor {

    @Override
    public void fetchPosts(final PostLoadedListener listener) {
        Call<List<Post>> callListPosts = API.get().getRetrofitService().getPosts();
        callListPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Response<List<Post>> response) {
                listener.postListLoaded(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                // TODO Implement generic alert dialog.
            }
        });
    }

}
