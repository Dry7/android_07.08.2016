package com.dry7.a07082016;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.dry7.a07082016.models.Product;
import com.dry7.a07082016.mvvp.ViewModel;
import com.dry7.a07082016.mvvp.ViewModelActivity;
import com.dry7.a07082016.services.DatabaseService;
import com.dry7.a07082016.viewmodels.DashboardViewModel;
import com.dry7.a07082016.viewmodels.ProductsViewModel;
import com.facebook.stetho.Stetho;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

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


        Subscriber<? super String> sub2;
        Observable<String> myObservable = Observable.create(sub -> {
                        // "Emit" any data to the subscriber
                        sub.onNext("a");
                        sub.onNext("b");
                        sub.onNext("c");
                        // Trigger the completion of the event
//                        sub.onCompleted();
                    });

        myObservable.subscribe(el -> Log.d("rxJava", el));
    }

    @Nullable
    @Override
    protected ViewModel createViewModel(@Nullable ViewModel.State savedViewModelState) {
        viewModel = new DashboardViewModel(savedViewModelState);
        return viewModel;
    }
}
