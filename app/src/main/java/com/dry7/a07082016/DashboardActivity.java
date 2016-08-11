package com.dry7.a07082016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
//        if (BuildConfig.DEBUG) {
//            Stetho.initializeWithDefaults(this);
//        }

//        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
//        Realm.setDefaultConfiguration(config);
//
//        Realm realm = Realm.getDefaultInstance();
//
//        realm.beginTransaction();
//
//        CategoryRealm categoryRealm = realm.createObject(CategoryRealm.class);
////        categoryRealm.setName("Coffee");
//
////        realm.commitTransaction();
//
//        realm.close();
    }
}
