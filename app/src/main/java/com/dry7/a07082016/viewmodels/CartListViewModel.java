package com.dry7.a07082016.viewmodels;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.dry7.a07082016.adapters.CartListAdapter;
import com.dry7.a07082016.adapters.ProductsAdapter;
import com.dry7.a07082016.models.CartItem;
import com.dry7.a07082016.models.Product;
import com.dry7.a07082016.mvvp.RecyclerViewViewModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import rx.Subscription;

public class CartListViewModel extends RecyclerViewViewModel {
    private final Context context;

    private Subscription subscriptionRealm;

    CartListAdapter adapter;

    public CartListViewModel(Context context, @Nullable State savedInstanceState) {
        super(savedInstanceState);

        this.context = context.getApplicationContext();

        Log.d("Coffee", "CartListViewModel.construct");

        adapter = new CartListAdapter();
        Log.d("Coffee", "CartListViewModel.construct - " + adapter.getItemCount());
        setItems(null);
    }

    private void setItems(String category) {
        Log.d("Coffee", "CartListViewModel.setItems");
        adapter.setItems(this.getProducts());
    }

    @Override
    public CartListAdapter getAdapter() {
        Log.d("Coffee", "CartListAdapter.getAdapter - " + this.adapter.getItemCount());
        return this.adapter;
    }

    @Override
    protected GridLayoutManager createLayoutManager() {
        return new GridLayoutManager(this.context, 3);
    }

    private ArrayList<CartItem> getProducts() {
        Log.d("Coffee", "CartListViewModel.getProducts");
        ArrayList<CartItem> products = new ArrayList<>();

        products.add(new CartItem(new Product(1, "1", "1", 1.0), 1));
        products.add(new CartItem(new Product(2, "2", "2", 2.0), 1));
        products.add(new CartItem(new Product(3, "3", "3", 3.0), 1));

        return products;
    }

    public void setCategory(String string) {
        setItems(string);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
