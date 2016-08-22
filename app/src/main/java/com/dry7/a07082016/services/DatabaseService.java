package com.dry7.a07082016.services;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import com.dry7.a07082016.R;
import com.dry7.a07082016.models.Category;
import com.dry7.a07082016.models.Product;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Load data from server
 */
public class DatabaseService {
    /** Check load categories service */
    private static boolean isLoadCategories = false;
    private static ScheduledExecutorService scheduledExecutorServiceCategories;

    /** Check load products service */
    private static boolean isLoadProducts = false;
    private static ScheduledExecutorService scheduledExecutorServiceProducts;

    /**
     * Load data from server
     *
     * @param context Context
     */
    public static void load(Context context) {
        DatabaseService.loadCategories(context);
        DatabaseService.loadProducts(context);
    }

    /**
     * Load categories
     *
     * @param context Context
     */
    public static void loadCategories(Context context) {
        /** Check is load categories run */
        if (DatabaseService.isLoadCategories) {
            return;
        } else {
            DatabaseService.isLoadCategories = true;
        }

        DatabaseService.scheduledExecutorServiceCategories = Executors.newScheduledThreadPool(5);

        /** Load categories */
        scheduledExecutorServiceCategories.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.d("Coffee", "DataService.loadCategories");

                Subscription subscription = RestClient.getInstance().categoriesList().observeOn(Schedulers.io()).subscribe(categories -> {
                    try (Realm realmInstance = Realm.getDefaultInstance()){
                        realmInstance.executeTransaction(transaction -> {
                            transaction.delete(Category.class);
                            for (Category category : categories) {
                                transaction.copyToRealmOrUpdate(category);
                            }
                        });
                    }
                },
                error -> {
                    Log.d("Coffee", error.getMessage());
                });
                if (subscription != null && subscription.isUnsubscribed()) {
                    subscription.unsubscribe();
                }
            }
        }, 0, Integer.parseInt(context.getResources().getString(R.string.rest_categories_load_timeout)), TimeUnit.SECONDS);
    }

    /**
     * Load products
     *
     * @param context Context
     */
    public static void loadProducts(Context context) {
        /** Check is load products run */
        if (DatabaseService.isLoadProducts) {
            return;
        } else {
            DatabaseService.isLoadProducts = true;
        }

        DatabaseService.scheduledExecutorServiceProducts = Executors.newScheduledThreadPool(5);

        /** Load categories */
        scheduledExecutorServiceProducts.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.d("Coffee", "DataService.loadProducts");

                Subscription subscription = RestClient.getInstance().productsList().observeOn(Schedulers.io()).subscribe(products -> {
                            try (Realm realmInstance = Realm.getDefaultInstance()){
                                realmInstance.executeTransaction(transaction -> {
                                    transaction.delete(Product.class);
                                    for (Product product : products) {
                                        transaction.copyToRealmOrUpdate(product);
                                    }
                                });
                            }
                        },
                        error -> {
                            Log.d("Coffee", error.getMessage());
                        });
                if (subscription != null && subscription.isUnsubscribed()) {
                    subscription.unsubscribe();
                }
            }
        }, 0, Integer.parseInt(context.getResources().getString(R.string.rest_products_load_timeout)), TimeUnit.SECONDS);
    }
}
