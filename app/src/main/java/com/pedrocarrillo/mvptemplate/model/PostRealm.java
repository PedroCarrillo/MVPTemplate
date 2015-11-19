package com.pedrocarrillo.mvptemplate.model;

import com.pedrocarrillo.mvptemplate.util.RealmManager;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author pcarrillo
 *         on 19/11/2015 for MVPtemplate.
 */
public class PostRealm extends RealmObject {

    @PrimaryKey
    private String id;

    private int userId;
    private String title;
    private String body;

    public PostRealm() {

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

    public static List<PostRealm> getPostsInDatabase(){
        return RealmManager.getInstance().getRealmInstance().where(PostRealm.class)
                .findAll();
    }

}
