package com.dry7.a07082016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;
import com.dry7.a07082016.services.DatabaseService;
import com.facebook.stetho.Stetho;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        RealmConfiguration config = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
        setContentView(R.layout.dashboard);
        if (BuildConfig.DEBUG) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                            .build()
            );
        }

        /** Analytics */
        MixpanelAPI mixpanel = MixpanelAPI.getInstance(this, getResources().getString(R.string.mixpanel_token));
        mixpanel.track("Dashboard");

        DatabaseService.load(this);
//            List<Category> categories = RestClient.categoriesListRealm();
//            for (Category category : categories) {
//                Log.d("Coffee1123", category.getName());
//            }

//        Realm realm = Realm.getDefaultInstance();
//
//        realm.beginTransaction();
//
//        RealmCategory categoryRealm = realm.createObject(RealmCategory.class);
//        categoryRealm.setName("Coffee");
//
//        realm.commitTransaction();
//
//        realm.close();
    }
}
