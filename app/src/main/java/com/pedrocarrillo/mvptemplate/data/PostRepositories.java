package com.pedrocarrillo.mvptemplate.data;

import android.support.annotation.NonNull;

import com.pedrocarrillo.mvptemplate.networking.services.PostRealmAPIImp;
import com.pedrocarrillo.mvptemplate.networking.services.PostServeAPIImp;
import com.pedrocarrillo.mvptemplate.networking.services.PostServiceAPI;
import com.pedrocarrillo.mvptemplate.util.API;

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
            repository = new InMemoryPostsManager(postServiceAPI);
        }
        return repository;
    }

    public synchronized static void changeOffLineRepository(boolean online) {
        ((InMemoryPostsManager) repository).switchAPILayer(online ? new PostServeAPIImp() : new PostRealmAPIImp());
    }

}

