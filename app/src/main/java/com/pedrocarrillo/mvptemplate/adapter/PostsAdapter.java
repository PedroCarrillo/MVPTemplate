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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedrocarrillo.mvptemplate.R;
import com.pedrocarrillo.mvptemplate.model.Post;

import java.util.List;

/**
 * @author Carlos Piñan
 * @modifiedBy Pedro Carrillo to fit recycler view and load more
 */

public class PostsAdapter extends LoadMoreBaseAdapter<Post, PostsAdapter.PostViewHolder> {

    private PostItemListener mItemListener;
    private LayoutInflater inflater;

    public PostsAdapter(List<Post> posts, PostItemListener itemListener) {
        setList(posts);
        mItemListener = itemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View postView = inflater.inflate(R.layout.list_posts, parent, false);
        return new PostViewHolder(postView, mItemListener);
    }

    @Override
    public void onBindDataViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_DATA) {
            bindPostViewHolder((PostViewHolder)holder, position);
        }
    }

    private void bindPostViewHolder(PostViewHolder postViewHolder, int position) {
        Post post = data.get(position);
        postViewHolder.userIdTextView.setText(String.valueOf(post.getUserId()));
        postViewHolder.postIdTextView.setText(String.valueOf(post.getId()));
        postViewHolder.titleTextView.setText(post.getTitle());
    }

    public void replaceData(List<Post> posts) {
        data.clear();
        setList(posts);
        notifyDataSetChanged();
    }

    private void setList(List<Post> posts) {
        this.data = posts;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView userIdTextView;
        public TextView postIdTextView;
        public TextView titleTextView;

        private PostItemListener mItemListener;

        public PostViewHolder(View itemView, PostItemListener listener) {
            super(itemView);
            mItemListener = listener;
            postIdTextView = (TextView) itemView.findViewById(R.id.postIdTextView);
            userIdTextView = (TextView) itemView.findViewById(R.id.userIdTextView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Post post = getItem(position);
            mItemListener.onPostClick(post);
        }
    }

    public interface PostItemListener {

        void onPostClick(Post clickedPost);

    }

}
