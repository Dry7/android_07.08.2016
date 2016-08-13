package com.dry7.a07082016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.dry7.a07082016.services.RestClient;
import com.facebook.stetho.Stetho;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import org.json.JSONObject;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import io.fabric.sdk.android.Fabric;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
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
        MixpanelAPI mixpanel = MixpanelAPI.getInstance(this, "983e8ba5034908a9b564e278d2183adb");

        JSONObject props = new JSONObject();
        mixpanel.track("Test event2", props);

        RealmConfiguration config = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);

        RestClient.categoriesListRealm().subscribe(categories -> {
            Log.d("Coffee", "Realm");
            Log.d("Coffee", categories.getClass().toString());
            Log.d("Coffee", categories.toString());
//            for (RealmCategory category : categories) {
//                Log.d("Coffee", category.getName());
//            }
        });

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
