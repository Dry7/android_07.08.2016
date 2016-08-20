package com.dry7.a07082016.models;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Category model
 */
@RealmClass
public class Category implements RealmModel {
    @PrimaryKey
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}