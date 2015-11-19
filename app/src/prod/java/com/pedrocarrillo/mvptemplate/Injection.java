package com.pedrocarrillo.mvptemplate;

import com.pedrocarrillo.mvptemplate.data.PostRepositories;
import com.pedrocarrillo.mvptemplate.data.PostRepository;
import com.pedrocarrillo.mvptemplate.networking.services.PostRealmAPIImp;
import com.pedrocarrillo.mvptemplate.networking.services.PostServeAPIImp;
import com.pedrocarrillo.mvptemplate.util.API;

/**
 * @author pcarrillo
 *         on 11/11/2015 for MVPtemplate.
 */
public class Injection {

    public static PostRepository provideNotesRepository() {
        return PostRepositories.getPostManager(API.isConnected() ? new PostServeAPIImp() : new PostRealmAPIImp());
    }

}
