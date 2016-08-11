package com.dry7.a07082016.models;

import java.io.Serializable;

/**
 * Product model
 */
public class Product implements Serializable
{
    public Integer id;
    public String name;
    public Double price;

    public Product(Integer id, String name, Double price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}