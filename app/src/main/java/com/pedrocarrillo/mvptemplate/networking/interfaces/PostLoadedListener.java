package com.pedrocarrillo.mvptemplate.networking.interfaces;

import com.pedrocarrillo.mvptemplate.model.Post;

import java.util.List;

/**
 * @author pcarrillo
 *         on 10/11/2015 for MVPtemplate.
 */
public interface PostLoadedListener {

    void postListLoaded(List<Post> postList);

}
