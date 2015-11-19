package com.pedrocarrillo.mvptemplate.util;

import com.pedrocarrillo.mvptemplate.MvpTemplateApp;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Pedro on 9/20/2015.
 */
public class RealmManager {

    private Realm realm;

    private static RealmManager ourInstance = new RealmManager();

    public static RealmManager getInstance() {
        return ourInstance;
    }

    public RealmManager(){
        realm = Realm.getDefaultInstance();
    }

    public Realm getRealmInstance() {
        return realm;
    }

    public <E extends RealmObject> void update(final E object) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(object);
            }
        });
    }

    public <E extends RealmObject> void update(final Iterable<E> object) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(object);
            }
        });
    }

    public <E extends RealmObject> void save(final E object, final Class<E> clazz) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(object);
            }
        });
    }

    public <E extends RealmObject> void save(final Iterable<E> object, final Class<E> clazz) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(object);
            }
        });
    }

    public <E extends RealmObject> void delete(final Iterable<E> objects){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (objects == null) {
                    return;
                }
                for (E object : objects) {
                    // add special conditions here if you have any
                    object.removeFromRealm();
                }
            }
        });
    }

    public <E extends RealmObject> void delete(final E object){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // add special conditions here if you have any
                object.removeFromRealm();
            }
        });
    }

    public <E extends RealmObject> RealmObject findById(Class<E> clazz, String id) {
        return realm.where(clazz).equalTo("id", id).findFirst();
    }

}
