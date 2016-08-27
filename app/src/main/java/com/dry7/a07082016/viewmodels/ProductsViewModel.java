package com.dry7.a07082016.viewmodels;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dry7.a07082016.adapters.ProductsAdapter;
import com.dry7.a07082016.models.Product;
import com.dry7.a07082016.mvvp.RecyclerViewViewModel;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Subscription;

public class ProductsViewModel extends RecyclerViewViewModel {
    private final Context context;

    private Subscription subscriptionRealm;

    ProductsAdapter adapter;

    public ProductsViewModel(Context context, @Nullable State savedInstanceState) {
        super(savedInstanceState);

        this.context = context.getApplicationContext();

        adapter = new ProductsAdapter();

        try (Realm realmInstance = Realm.getDefaultInstance()) {
            if (this.subscriptionRealm != null && this.subscriptionRealm.isUnsubscribed()) {
                this.subscriptionRealm.unsubscribe();
            }
            this.subscriptionRealm = realmInstance.where(Product.class).findAllSortedAsync("price").asObservable().subscribe(products -> {
                adapter.setItems(products);
                Log.d("Coffee", "subscriptionRealm - " + products.size());
            });
        }
    }

    @Override
    public ProductsAdapter getAdapter() {
        return adapter;
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this.context);
    }

    @Override
    public RecyclerViewViewModelState getInstanceState() {
        return new ProductsState(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (this.subscriptionRealm != null && this.subscriptionRealm.isUnsubscribed()) {
            this.subscriptionRealm.unsubscribe();
        }
    }

    private static class ProductsState extends RecyclerViewViewModelState {
        private RealmResults<Product> products;

        public ProductsState(ProductsViewModel viewModel) {
            super(viewModel);
            products = viewModel.adapter.getItems();
        }

        public ProductsState(Parcel in) {
            super(in);
//            products = in.createTypedArrayList(Product.CREATOR);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
//            dest.writeTypedList(products);
        }

        public static Creator<ProductsState> CREATOR = new Creator<ProductsState>() {
            @Override
            public ProductsState createFromParcel(Parcel source) {
                return new ProductsState(source);
            }

            @Override
            public ProductsState[] newArray(int size) {
                return new ProductsState[size];
            }
        };
    }
}
