package com.dry7.a07082016.models;

import java.io.Serializable;

/**
 * Category model
 */
public class Category implements Serializable {
    public String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}