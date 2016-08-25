package com.dry7.a07082016;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.crashlytics.android.Crashlytics;
import com.dry7.a07082016.mvvp.ViewModel;
import com.dry7.a07082016.mvvp.ViewModelActivity;
import com.dry7.a07082016.services.DatabaseService;
import com.dry7.a07082016.viewmodels.DashboardViewModel;
import com.facebook.stetho.Stetho;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DashboardActivity extends ViewModelActivity {

    private DashboardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Start Crashlytics */
        Fabric.with(this, new Crashlytics());

        /** Start Realm */
        RealmConfiguration config = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);

        setContentView(R.layout.dashboard);

        /** Start Stetho debug */
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

        /** Load data from server */
        DatabaseService.load(this);
    }

    @Nullable
    @Override
    protected ViewModel createViewModel(@Nullable ViewModel.State savedViewModelState) {
        viewModel = new DashboardViewModel(savedViewModelState);
        return viewModel;
    }
}
