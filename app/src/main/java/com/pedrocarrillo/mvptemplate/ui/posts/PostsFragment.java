package com.pedrocarrillo.mvptemplate.ui.posts;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class PostsFragment extends BaseFragment<PostsContractor.PostsPresenter>  implements PostsContractor.PostsView, PostsAdapter.PostItemListener {

    private RecyclerView rvPosts;
    private PostsAdapter mPostAdapter;

    public static PostsFragment newInstance() {
        return new PostsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rvPosts = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvPosts.setLayoutManager(linearLayoutManager);
        rvPosts.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                mPresenter.loadPosts(true);
            }
        });

//        rvPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                int index = position - 1; // For header view it could be fixed just using the adapter from the adapterView
//                if (mPostAdapter.getItem(index) != null) {
//                    Post post = mPostAdapter.getItem(index);
//                    mPresenter.openPostDetails(post);
//                }
//            }
//        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadPosts(false);
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
        if (mPostAdapter == null) {
            mPostAdapter = new PostsAdapter(posts, this);
            rvPosts.setAdapter(mPostAdapter);
        }
        mPostAdapter.replaceData(posts);
    }

    @Override
    public void showPostDetailUi(Post post) {

    }

    @Override
    public void onPostClick(Post clickedPost) {

    }
}
