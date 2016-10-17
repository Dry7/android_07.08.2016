package com.dry7.a07082016.models;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Product model
 */
public class CartItem
{
    public Product product;

    public Integer amount;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public CartItem() {}

    public CartItem(Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }
}