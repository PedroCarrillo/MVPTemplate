package com.pedrocarrillo.mvptemplate.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.pedrocarrillo.mvptemplate.data.PostRepositories;

/**
 * @author pcarrillo
 *         on 19/11/2015 for MVPtemplate.
 */
public class NetworkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getExtras() != null) {
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
            PostRepositories.changeOffLineRepository(ni != null && ni.isConnectedOrConnecting());
        }
    }

}
