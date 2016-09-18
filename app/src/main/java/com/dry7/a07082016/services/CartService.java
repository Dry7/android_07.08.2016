package com.dry7.a07082016.services;

import android.support.v4.app.INotificationSideChannel;
import android.util.Log;

import com.dry7.a07082016.models.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import io.realm.RealmObject;
import rx.subjects.BehaviorSubject;

public class CartService {
    private static HashMap<Product, Integer> products = new HashMap<Product, Integer>();

    /**
     * Add product to cart
     *
     * @param product Product
     * @return boolean
     */
    public static boolean add(Product product) {
        return CartService.add(product, 1);
    }

    /**
     * Add product to cart
     *
     * @param product Product
     * @param amount Amount
     * @return boolean
     */
    public static boolean add(Product product, int amount) {
        for (Map.Entry<Product, Integer> row : CartService.products.entrySet()) {
            if (row.getKey().getId().equals(product.getId())) {
                row.setValue(row.getValue() + amount);
                CartService.print();
                return true;
            }
        }
        CartService.products.put(product, amount);

        CartService.print();

        return true;
    }

    /**
     * Check product in cart
     *
     * @param product Product
     * @return boolean
     */
    public static boolean inCart(Product product) {
        return RealmObject.isValid(product) && CartService.products.containsKey(product);
    }

    public static void print() {
        Log.d("Coffee", "---asdasdad---");
        for (Map.Entry<Product, Integer> row : CartService.products.entrySet()) {
            Log.d("Coffee", row.getKey().getName() + " - " + row.getValue().toString());
        }
    }
}
