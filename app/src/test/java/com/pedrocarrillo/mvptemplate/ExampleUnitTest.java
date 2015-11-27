package com.pedrocarrillo.mvptemplate;

import com.pedrocarrillo.mvptemplate.data.PostRepository;
import com.pedrocarrillo.mvptemplate.model.Post;
import com.pedrocarrillo.mvptemplate.ui.posts.PostsContractor;
import com.pedrocarrillo.mvptemplate.ui.posts.PostsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Mock
    private PostRepository mPostRepository;

    @Mock
    private PostsContractor.PostsView mPostsView;

    @Captor
    private ArgumentCaptor<PostRepository.LoadPostsCallback> mLoadPostsCallbackCaptor;

    private PostsPresenter mPostsPresenter;

    private static List<Post> mPostList = new ArrayList<>();

    @Before
    public void setupPostsPresenter() {
        MockitoAnnotations.initMocks(this);
        mPostsPresenter = new PostsPresenter(mPostRepository);
        mPostsPresenter.attachView(mPostsView);

        mPostList.add(new Post());
        mPostList.add(new Post());
        mPostList.add(new Post());
    }

    @Test
    public void loadPostsFromRepositoryAndLoadIntoView() {
        mPostsPresenter.loadPosts(true, false);

        verify(mPostRepository).getPosts(mLoadPostsCallbackCaptor.capture(), eq(true), eq(false));
        mLoadPostsCallbackCaptor.getValue().onPostsLoaded(mPostList);

        verify(mPostsView).showPostsLoading(false);
        verify(mPostsView).showPosts(mPostList);z

    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void clickOnItem_ShowsPostDetailUI() {

    }
}