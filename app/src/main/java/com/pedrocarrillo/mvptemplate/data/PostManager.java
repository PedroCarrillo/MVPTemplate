package com.pedrocarrillo.mvptemplate.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pedrocarrillo.mvptemplate.model.Post;
import com.pedrocarrillo.mvptemplate.networking.services.PostServiceAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pcarrillo
 *         on 11/11/2015 for MVPtemplate.
 */
public class PostManager implements PostRepository {

    private PostServiceAPI mPostServiceApi;
    private List<Post> mPosts = new ArrayList<>();

    public PostManager(@NonNull PostServiceAPI postServiceAPI) {
        mPostServiceApi = postServiceAPI;
    }

    @Override
    public void getPosts(@NonNull final LoadPostsCallback callback, boolean loadMore, boolean forceUpdate) {
        if (forceUpdate) {
            mPosts.clear();
        }
        if (!loadMore && !mPosts.isEmpty()) {
            callback.onPostsLoaded(mPosts);
        } else {
            mPostServiceApi.getPosts(new PostServiceAPI.PostServiceCallback<List<Post>>() {
                @Override
                public void onLoaded(List<Post> posts) {
                    mPosts.addAll(new ArrayList<>(posts));
                    callback.onPostsLoaded(mPosts);
                }
            }, mPosts.size());
        }
    }

    public void switchAPILayer(@NonNull PostServiceAPI postServiceAPI) {
        this.mPostServiceApi = postServiceAPI;
    }
}
