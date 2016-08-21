package com.dry7.a07082016.services;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import com.dry7.a07082016.R;
import com.dry7.a07082016.models.Category;

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

    private static ScheduledExecutorService scheduledExecutorService;

    /**
     * Load data from server
     *
     * @param context Context
     */
    public static void load(Context context) {
        DatabaseService.loadCategories(context);
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

        DatabaseService.scheduledExecutorService = Executors.newScheduledThreadPool(5);

        /** Load categories */
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.d("Coffee", "DataService.load");

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
}
