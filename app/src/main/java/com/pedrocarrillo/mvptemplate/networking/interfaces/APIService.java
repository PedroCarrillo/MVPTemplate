package com.pedrocarrillo.mvptemplate.networking.interfaces;

import com.pedrocarrillo.mvptemplate.model.Post;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author pcarrillo
 * @source https://github.com/cpinan/DemoRetrofit2/blob/master/app/src/main/java/com/carlospinan/demoretrofit2/interfaces/APIService.java
 *         on 10/11/2015 for MVPtemplate.
 */
public interface APIService {

    @GET("posts?_limit=20")
    Call<List<Post>> getPosts(@Query("_start") int start);

}
