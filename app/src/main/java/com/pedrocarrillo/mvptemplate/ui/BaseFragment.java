package com.pedrocarrillo.mvptemplate.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.pedrocarrillo.mvptemplate.R;
import com.pedrocarrillo.mvptemplate.ui.common.MvpView;
import com.pedrocarrillo.mvptemplate.ui.common.MvpPresenter;

/**
 * @author pcarrillo
 *         on 09/11/2015 for MVPtemplate.
 */
public class BaseFragment<T extends MvpPresenter> extends Fragment implements MvpView {

    protected T mPresenter;
    protected ProgressDialog mProgressDialog;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) mPresenter.detachView();
    }

    @Override
    public void setProgressIndicator(boolean active) {
        if(active) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(getActivity());
                mProgressDialog.setCancelable(false);
                mProgressDialog.setTitle(null);
                mProgressDialog.setMessage(getString(R.string.s_loading_post_detail));
            } else {
                mProgressDialog.show();
            }
        } else  {
            mProgressDialog.hide();
        }

    }

}
