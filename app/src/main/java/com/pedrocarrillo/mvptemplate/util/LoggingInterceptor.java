package com.pedrocarrillo.mvptemplate.util;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import okio.Buffer;

/**
 * @author pcarrillo
 * @source https://gist.github.com/erickok/e371a9e0b9e702ed441d
 *         on 10/11/2015 for MVPtemplate.
 */
public class LoggingInterceptor implements Interceptor {

    private static final String LOG_TAG = "OkHttp";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.d(LOG_TAG, String.format("--> Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));

        Buffer requestBuffer = new Buffer();
        if (request.body() != null) {
            request.body().writeTo(requestBuffer);
            Log.d(LOG_TAG, requestBuffer.readUtf8());
        }

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.d(LOG_TAG, String.format("<-- Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        MediaType contentType = response.body().contentType();
        String content = response.body().string();
        Log.d(LOG_TAG, content);

        ResponseBody wrappedBody = ResponseBody.create(contentType, content);
        return response.newBuilder().body(wrappedBody).build();
    }

}