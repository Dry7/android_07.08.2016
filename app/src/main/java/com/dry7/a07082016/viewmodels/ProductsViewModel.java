package com.dry7.a07082016.viewmodels;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dry7.a07082016.adapters.ProductsAdapter;
import com.dry7.a07082016.models.Product;
import com.dry7.a07082016.mvvp.RecyclerViewViewModel;

import io.realm.Realm;
import io.realm.RealmResults;

public class ProductsViewModel extends RecyclerViewViewModel {
    private final Context context;

    ProductsAdapter adapter;

    public ProductsViewModel(Context context, @Nullable State savedInstanceState) {
        super(savedInstanceState);

        this.context = context.getApplicationContext();

        RealmResults<Product> products;

        if (savedInstanceState instanceof ProductsState) {
   //         products = ((ProductsState)savedInstanceState).products;
        } else {
        }
        products = getProducts();

        adapter = new ProductsAdapter();
        adapter.setItems(products);
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

    private RealmResults<Product> getProducts() {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            return realmInstance.where(Product.class).findAllSortedAsync("price");
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
