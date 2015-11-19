package com.pedrocarrillo.mvptemplate.networking.services;

import com.pedrocarrillo.mvptemplate.model.Post;
import com.pedrocarrillo.mvptemplate.model.PostRealm;

import java.util.List;

/**
 * @author pcarrillo
 *         on 19/11/2015 for MVPtemplate.
 */
public class PostRealmAPIImp implements PostServiceAPI {

    @Override
    public void getPosts(PostServiceCallback<List<Post>> callback, int offset) {
        callback.onLoaded(Post.obtainFromResult(PostRealm.getPostsInDatabase()));
    }

}
