package com.dry7.a07082016.database.models;

import io.realm.RealmObject;

public class CategoryRealm extends RealmObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}