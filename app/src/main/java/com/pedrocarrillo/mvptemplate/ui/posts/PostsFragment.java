package com.pedrocarrillo.mvptemplate.ui.posts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrocarrillo.mvptemplate.R;
import com.pedrocarrillo.mvptemplate.adapter.PostsAdapter;
import com.pedrocarrillo.mvptemplate.Injection;
import com.pedrocarrillo.mvptemplate.model.Post;
import com.pedrocarrillo.mvptemplate.ui.BaseFragment;
import com.pedrocarrillo.mvptemplate.ui.custom.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pcarrillo
 *         on 10/11/2015 for MVPtemplate.
 */
public class PostsFragment extends BaseFragment<PostsContractor.PostsPresenter> implements PostsContractor.PostsView, PostsAdapter.PostItemListener {

    private RecyclerView rvPosts;
    private SwipeRefreshLayout swlMain;
    private PostsAdapter mPostAdapter;
    private EndlessRecyclerOnScrollListener mEndlessRecyclerOnScrollListener;

    public static PostsFragment newInstance() {
        return new PostsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rvPosts = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        rvPosts = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        swlMain = (SwipeRefreshLayout)rootView.findViewById(R.id.srl_main);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvPosts.setHasFixedSize(true);
        rvPosts.setLayoutManager(linearLayoutManager);
        mEndlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (!mPostAdapter.isLoading()) {
                    mPresenter.loadPosts(true, false);
                }
            }
        };
        rvPosts.addOnScrollListener(mEndlessRecyclerOnScrollListener);
        swlMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mEndlessRecyclerOnScrollListener.reset();
                mPresenter.loadPosts(false, true);
            }
        });
        return rootView;
    }

    @Override
    public void showPostsLoading(boolean loading) {
       if (mPostAdapter != null) mPostAdapter.setLoading(loading);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadPosts(false, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mPresenter = new PostsPresenter(Injection.provideNotesRepository());
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showPosts(List<Post> posts) {
        swlMain.setRefreshing(false);
        if (mPostAdapter == null) {
            mPostAdapter = new PostsAdapter(new ArrayList<>(posts), this);
            rvPosts.setAdapter(mPostAdapter);
        }
        mPostAdapter.setLoading(false);
        mPostAdapter.replaceData(new ArrayList<Post>(posts));
    }

    @Override
    public void showPostDetailUi(Post post) {

    }

    @Override
    public void onPostClick(Post clickedPost) {
        mPresenter.openPostDetails(clickedPost);
    }

}
