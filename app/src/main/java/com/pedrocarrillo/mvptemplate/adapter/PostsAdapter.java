/**
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015 Carlos Piñan
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.pedrocarrillo.mvptemplate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pedrocarrillo.mvptemplate.R;
import com.pedrocarrillo.mvptemplate.model.Post;

import java.util.List;

/**
 * @author Carlos Piñan
 */

public class PostsAdapter extends BaseAdapter {

    private List<Post> listPosts;
    private LayoutInflater inflater;

    public PostsAdapter(Context context, List<Post> listPosts) {
        this.listPosts = listPosts;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return getListPosts().size();
    }

    @Override
    public Post getItem(int position) {
        return getListPosts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        final ViewHolder holder;
        final Post post = getItem(position);
        if (view == null || view.getTag() == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_posts, container, false);
            holder.postIdTextView = (TextView) view.findViewById(R.id.postIdTextView);
            holder.userIdTextView = (TextView) view.findViewById(R.id.userIdTextView);
            holder.titleTextView = (TextView) view.findViewById(R.id.titleTextView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.userIdTextView.setText(String.valueOf(post.getUserId()));
        holder.postIdTextView.setText(String.valueOf(post.getId()));
        holder.titleTextView.setText(post.getTitle());
        return view;
    }

    public List<Post> getListPosts() {
        return listPosts;
    }

    public void setListPosts(List<Post> listPosts) {
        this.listPosts = listPosts;
        this.notifyDataSetChanged();
    }

    private static class ViewHolder {
        public TextView userIdTextView;
        public TextView postIdTextView;
        public TextView titleTextView;
    }
}
