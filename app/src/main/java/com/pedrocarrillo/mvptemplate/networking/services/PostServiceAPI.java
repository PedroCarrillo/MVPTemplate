package com.pedrocarrillo.mvptemplate.networking.services;

import com.pedrocarrillo.mvptemplate.model.Post;

import java.util.List;

/**
 * @author pcarrillo
 *         on 10/11/2015 for MVPtemplate.
 */
public interface PostServiceAPI {

    interface PostServiceCallback<T> {

        void onLoaded(T posts);

    }

    void getPosts(PostServiceCallback<List<Post>> callback, int offset);

}
