package com.pedrocarrillo.mvptemplate.ui.posts;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;

import com.pedrocarrillo.mvptemplate.model.Post;
import com.pedrocarrillo.mvptemplate.networking.interactors.PostInteractor;
import com.pedrocarrillo.mvptemplate.networking.interfaces.PostLoadedListener;

import java.util.List;

/**
 * @author pcarrillo
 *         on 09/11/2015 for MVPtemplate.
 */
public class PostsPresenter implements PostsContractor.PostsPresenter<PostsContractor.PostsView>, PostLoadedListener {

    private PostsContractor.PostsView mPostsView;
    private PostInteractor postInteractor;

    public PostsPresenter(){
        postInteractor = new PostInteractorImp();
    }

    @Override
    public void loadPosts(boolean forceUpdate) {
        mPostsView.setProgressIndicator(true);
        postInteractor.fetchPosts(this);
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

    @Override
    public void postListLoaded(List<Post> postList) {
        if (mPostsView != null) {
            mPostsView.showPosts(postList);
            mPostsView.setProgressIndicator(false);
        }
    }

}
