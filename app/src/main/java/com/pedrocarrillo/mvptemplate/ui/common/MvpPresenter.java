package com.pedrocarrillo.mvptemplate.ui.common;

/**
 * @author pcarrillo
 *         on 09/11/2015 for MVPtemplate.
 */

public interface MvpPresenter<V> {

    void attachView(V view);
    void detachView();

}