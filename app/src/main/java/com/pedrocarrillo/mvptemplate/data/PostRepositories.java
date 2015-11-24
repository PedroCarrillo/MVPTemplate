package com.pedrocarrillo.mvptemplate.data;

import android.support.annotation.NonNull;

import com.pedrocarrillo.mvptemplate.networking.services.PostRealmAPIImp;
import com.pedrocarrillo.mvptemplate.networking.services.PostServiceAPIImp;
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
            repository = new PostManager(postServiceAPI);
        }
        return repository;
    }

    public synchronized static void changeOffLineRepository(boolean online) {
        ((PostManager) repository).switchAPILayer(online ? new PostServiceAPIImp() : new PostRealmAPIImp());
    }

}

