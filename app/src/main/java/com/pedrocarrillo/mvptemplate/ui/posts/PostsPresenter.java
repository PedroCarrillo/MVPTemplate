package com.pedrocarrillo.mvptemplate.ui.posts;

import android.support.annotation.NonNull;

import com.pedrocarrillo.mvptemplate.data.PostRepository;
import com.pedrocarrillo.mvptemplate.model.Post;
import com.pedrocarrillo.mvptemplate.networking.services.PostServeAPIImp;
import com.pedrocarrillo.mvptemplate.networking.services.PostServiceAPI;

import java.util.List;

/**
 * @author pcarrillo
 *         on 09/11/2015 for MVPtemplate.
 */
public class PostsPresenter implements PostsContractor.PostsPresenter<PostsContractor.PostsView> {

    private PostsContractor.PostsView mPostsView;
    private PostRepository mPostsRepository;

    public PostsPresenter(@NonNull PostRepository postRepository){
        mPostsRepository = postRepository;
    }

    @Override
    public void loadPosts(boolean loadMore, boolean forceUpdate) {
        mPostsView.showPostsLoading(true);
        mPostsRepository.getPosts(new PostRepository.LoadPostsCallback() {
            @Override
            public void onPostsLoaded(List<Post> posts) {
                mPostsView.showPosts(posts);
            }
        }, loadMore, forceUpdate);
    }

    @Override
    public void openPostDetails(@NonNull Post postClicked) {

    }

    @Override
    public void attachView(PostsContractor.PostsView mPostsView) {
        this.mPostsView = mPostsView;
    }

    @Override
    public void detachView() {
        mPostsView = null;
    }

}
