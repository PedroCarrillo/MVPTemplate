package com.pedrocarrillo.mvptemplate.data;

import android.support.annotation.NonNull;

import com.pedrocarrillo.mvptemplate.model.Post;

import java.util.List;

/**
 * @author pcarrillo
 *         on 11/11/2015 for MVPtemplate.
 */
public interface PostRepository {

        interface LoadPostsCallback {

        void onPostsLoaded(List<Post> posts);
    }

    void getPosts(@NonNull LoadPostsCallback callback, boolean loadMore, boolean forceUpdate);

}
