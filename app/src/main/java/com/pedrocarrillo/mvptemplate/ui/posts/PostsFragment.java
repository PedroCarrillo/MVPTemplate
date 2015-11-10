package com.pedrocarrillo.mvptemplate.ui.posts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.pedrocarrillo.mvptemplate.R;
import com.pedrocarrillo.mvptemplate.adapter.PostsAdapter;
import com.pedrocarrillo.mvptemplate.model.Post;
import com.pedrocarrillo.mvptemplate.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pcarrillo
 *         on 10/11/2015 for MVPtemplate.
 */
public class PostsFragment extends BaseFragment<PostsContractor.PostsPresenter>  implements PostsContractor.PostsView {

    private ListView lvPosts;
    private PostsAdapter mPostAdapter;

    public static PostsFragment newInstance() {
        return new PostsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        lvPosts = (ListView)rootView.findViewById(R.id.listView);
        View header = inflater.inflate(R.layout.list_posts, null);
        ((TextView) header.findViewById(R.id.postIdTextView)).setText(R.string.s_post_id);
        ((TextView) header.findViewById(R.id.userIdTextView)).setText(R.string.s_user_id);
        ((TextView) header.findViewById(R.id.titleTextView)).setText(R.string.s_post_title);
        lvPosts.addHeaderView(header, null, false);
        lvPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int index = position - 1; // For header view it could be fixed just using the adapter from the adapterView
                if (mPostAdapter.getItem(index) != null) {
                    Post post = mPostAdapter.getItem(index);
                    mPresenter.openPostDetails(post);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadPosts(false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mPresenter = new PostsPresenter();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showPosts(List<Post> posts) {
        if (mPostAdapter == null) {
            mPostAdapter = new PostsAdapter(getActivity(), new ArrayList<Post>());
            lvPosts.setAdapter(mPostAdapter);
        }
        mPostAdapter.setListPosts(posts);
    }

}
