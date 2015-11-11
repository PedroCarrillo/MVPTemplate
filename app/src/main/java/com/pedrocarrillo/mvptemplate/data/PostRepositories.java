package com.pedrocarrillo.mvptemplate.data;

import android.support.annotation.NonNull;

import com.pedrocarrillo.mvptemplate.networking.services.PostServiceAPI;

/**
 * @author pcarrillo
 *         on 11/11/2015 for MVPtemplate.
 */
public class PostRepositories {

    private static PostRepository repository = null;

    private PostRepositories() {
    }

    public synchronized static PostRepository getPostManager(@NonNull PostServiceAPI postServiceAPI) {
        if (repository == null) {
            repository = new PostsManager(postServiceAPI);
        }
        return repository;
    }


}
