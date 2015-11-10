package com.pedrocarrillo.mvptemplate.ui.posts;

import android.support.annotation.NonNull;

import com.pedrocarrillo.mvptemplate.model.Post;
import com.pedrocarrillo.mvptemplate.ui.common.MvpView;
import com.pedrocarrillo.mvptemplate.ui.common.MvpPresenter;

import java.util.List;

/**
 * @author pcarrillo
 *         on 09/11/2015 for MVPtemplate.
 */
public class PostsContractor {

    interface PostsView extends MvpView {

        void showPosts(List<Post> posts);

        void showPostDetailUi(Post post);

    }

    interface PostsPresenter<View> extends MvpPresenter<View> {

        void loadPosts(boolean forceUpdate);

        void openPostDetails(@NonNull Post postClicked);

    }

}
