package com.dry7.a07082016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dry7.a07082016.database.models.CategoryRealm;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        if (BuildConfig.DEBUG) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                            .build()
            );
        }

        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        CategoryRealm categoryRealm = realm.createObject(CategoryRealm.class);
        categoryRealm.setName("Coffee");

        realm.commitTransaction();

        realm.close();
    }
}
