package com.dry7.a07082016.viewmodels;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dry7.a07082016.adapters.ProductsAdapter;
import com.dry7.a07082016.models.Category;
import com.dry7.a07082016.models.Product;
import com.dry7.a07082016.mvvp.RecyclerViewViewModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.Subscription;

public class ProductsViewModel extends RecyclerViewViewModel {
    private final Context context;

    private Subscription subscriptionRealm;

    ProductsAdapter adapter;

    public ProductsViewModel(Context context, @Nullable State savedInstanceState) {
        super(savedInstanceState);

        this.context = context.getApplicationContext();

        Log.d("Coffee", "ProductsViewModel.construct");

        adapter = new ProductsAdapter();
        Log.d("Coffee", "ProductsViewModel.construct - " + adapter.getItemCount());
        setItems(null);
    }

    private void setItems(String category) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            if (this.subscriptionRealm != null && this.subscriptionRealm.isUnsubscribed()) {
                this.subscriptionRealm.unsubscribe();
            }
            RealmQuery<Product> query = realmInstance.where(Product.class);
            if ((category != null) && (!category.equals(""))) {
                query.equalTo("category", category);
            }
            this.subscriptionRealm = query.findAllSortedAsync("price").asObservable().subscribe(products -> {

                RealmList<Product> newProducts = new RealmList<Product>();
                if (products != null) {
                    newProducts.addAll(products);
                }
                adapter.setItems(newProducts);
            });
        }
    }

    @Override
    public ProductsAdapter getAdapter() {
        Log.d("Coffee", "ProductsViewModel.getAdapter - " + this.adapter.getItemCount());
        return this.adapter;
    }

    @Override
    protected GridLayoutManager createLayoutManager() {
        return new GridLayoutManager(this.context, 3);
    }

    private ArrayList<Product> getProducts() {
        Log.d("Coffee", "ProductsViewModel.getProducts");
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "1", "1", 1.0));
        products.add(new Product(2, "2", "2", 2.0));
        products.add(new Product(3, "3", "3", 3.0));

        return products;
    }

    public void setCategory(String string) {
        setItems(string);
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
        private List<Product> products;

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
