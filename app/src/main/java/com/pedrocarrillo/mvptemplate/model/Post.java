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

package com.pedrocarrillo.mvptemplate.model;

import com.pedrocarrillo.mvptemplate.util.RealmManager;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * @author Carlos Piñan
 */
public class Post {

    private String id;

    private int userId;
    private String title;
    private String body;

    public Post() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PostRealm saveToRealm() {
        PostRealm postRealm = new PostRealm();
        postRealm.setId(id);
        postRealm.setBody(body);
        postRealm.setTitle(title);
        postRealm.setUserId(userId);
        return postRealm;
    }

    public static void savePostsToRealm(List<Post> posts) {
        List<PostRealm> postRealms = new ArrayList<>();
        for (Post post : posts) {
            postRealms.add(post.saveToRealm());
        }
        RealmManager.getInstance().save(postRealms, PostRealm.class);
    }

    public static List<Post> obtainFromResult(List<PostRealm> postRealms) {
        List<Post> postList = new ArrayList<>();
        for (PostRealm postRealm : postRealms) {
            Post post = new Post();
            post.setId(postRealm.getId());
            post.setBody(postRealm.getBody());
            post.setTitle(postRealm.getTitle());
            post.setUserId(postRealm.getUserId());
            postList.add(post);
        }
        return postList;
    }
}
