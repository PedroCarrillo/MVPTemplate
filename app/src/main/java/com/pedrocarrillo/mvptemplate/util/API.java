package com.pedrocarrillo.mvptemplate.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.pedrocarrillo.mvptemplate.MvpTemplateApp;
import com.pedrocarrillo.mvptemplate.networking.interfaces.APIService;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * @author pcarrillo
 * @source https://github.com/cpinan/DemoRetrofit2/blob/master/app/src/main/java/com/carlospinan/demoretrofit2/helpers/API.java
 *         on 10/11/2015 for MVPtemplate.
 */
public class API {

    public static final String ENDPOINT_URL = "http://jsonplaceholder.typicode.com/";

    private static API instance;

    private APIService service;

    private API() {
        /* IGNORED */
    }

    public static API get() {
        if (instance == null) {
            instance = new API();
        }
        return instance;
    }

    public APIService getRetrofitService() {
        if (service == null) {
            OkHttpClient client = new OkHttpClient();
            client.interceptors().add(new LoggingInterceptor());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            service = retrofit.create(APIService.class);
        }
        return service;
    }

    public static boolean isConnected() {
        ConnectivityManager conMgr = (ConnectivityManager) MvpTemplateApp.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        if (!i.isAvailable())
            return false;
        return true;
    }

}
