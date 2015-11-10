package com.pedrocarrillo.mvptemplate.networking.interactors;

import com.pedrocarrillo.mvptemplate.model.Post;
import com.pedrocarrillo.mvptemplate.networking.interfaces.PostLoadedListener;

import java.util.List;

/**
 * @author pcarrillo
 *         on 10/11/2015 for MVPtemplate.
 */
public interface PostInteractor {

    void fetchPosts(PostLoadedListener listener);

}
