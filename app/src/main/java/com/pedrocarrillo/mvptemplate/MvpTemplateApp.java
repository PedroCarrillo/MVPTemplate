package com.pedrocarrillo.mvptemplate;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author pcarrillo
 *         on 19/11/2015 for MVPtemplate.
 */
public class MvpTemplateApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static Context getContext() {
        return mContext;
    }


}
