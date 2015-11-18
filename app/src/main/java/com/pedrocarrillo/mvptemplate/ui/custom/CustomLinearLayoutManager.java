package com.pedrocarrillo.mvptemplate.ui.custom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrocarrillo.mvptemplate.R;

import java.lang.ref.WeakReference;

/**
 * @author pcarrillo
 *         on 13/11/2015 for MVPtemplate.
 */
public class CustomLinearLayoutManager extends LinearLayoutManager {

    public static final String TAG = CustomLinearLayoutManager.class.getSimpleName();

    private boolean isLoading = false;
    private int lastViewPosition = 0;
    private View progressView;
    private WeakReference<Context> mContext;

    public CustomLinearLayoutManager(Context context) {
        super(context);
        this.mContext = new WeakReference<Context>(context);
    }

    @Override
    public View findViewByPosition(int position) {
        return super.findViewByPosition(position);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

    }

    public void addLoading() {
        isLoading = true;
//        if (findLastVisibleItemPosition() == (getItemCount() - 1)) {
//            View lastView = findViewByPosition(getItemCount() - 1);
//            if (lastView != null) {
//                ((ViewGroup) lastView).addView(progressView);
//            }
//        }
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int id = super.scrollVerticallyBy(dy, recycler, state);
        if (id == 0) {
            Log.d(TAG, "findLastVisibleItemPosition() " + findLastVisibleItemPosition() + "findLastVisibleItemPosition()" + getItemCount());
            if (findLastVisibleItemPosition() == (getItemCount() - 1)) {
                View lastView = findViewByPosition(findLastVisibleItemPosition());
//                if (progressView == null) {
//                    LayoutInflater inflater = LayoutInflater.from(mContext.get());
//                    this.progressView = inflater.inflate(R.layout.progress_bar_item, (ViewGroup) lastView, false);
//                }
//                if (isLoading) {
//                    ((ViewGroup) lastView.getParent()).addView(progressView);
//                } else {
//                    ((ViewGroup) lastView).removeView(progressView);
//                }
            }
        }
        return id;
    }

    public void removeLoadingView () {

    }


}
