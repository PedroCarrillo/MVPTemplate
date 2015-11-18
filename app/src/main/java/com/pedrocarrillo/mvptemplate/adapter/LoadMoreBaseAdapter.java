package com.pedrocarrillo.mvptemplate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.pedrocarrillo.mvptemplate.R;

import java.util.AbstractCollection;
import java.util.List;

/**
 * @author pcarrillo
 *         on 12/11/2015 for MVPtemplate.
 */
abstract class LoadMoreBaseAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected static final int VIEW_TYPE_LOAD = 1;
    protected static final int VIEW_TYPE_DATA = 2;

    private boolean hasLoadingFooter = false;

    protected List<T> data;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOAD) {
            View progressView =  LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar_item, parent, false);
            return new ProgressViewHolder(progressView);
        } else {
            return onCreateDataViewHolder(parent, viewType);
        }
    }

    public abstract RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_LOAD ) {
            onBindProgressView(holder, position);
        } else {
            onBindDataViewHolder(holder, position);
        }
    }

    public abstract void onBindDataViewHolder(RecyclerView.ViewHolder holder, int position);

    protected void onBindProgressView(RecyclerView.ViewHolder holder, int position){
        ((ProgressViewHolder)holder).progressBar.setIndeterminate(true);
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position) != null ? VIEW_TYPE_DATA : VIEW_TYPE_LOAD;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }

    public T getItem(int index) {
        if (data != null && data.get(index) != null) {
            return data.get(index);
        } else {
            throw new IllegalArgumentException("Item with index " + index + " doesn't exist, dataSet is " + data);
        }
    }

    public boolean isLoading() {
        return hasLoadingFooter;
    }

    public void setLoading(boolean loading) {
        if (loading) {
            hasLoadingFooter = true;
            if (!data.contains(null)) {
                data.add(null);
                notifyItemInserted(data.size() - 1);
            }
        } else {
            hasLoadingFooter = false;
            data.remove(null);
            notifyItemRemoved(data.size() - 1);
        }
        notifyDataSetChanged();
    }
}
