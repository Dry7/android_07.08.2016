package com.dry7.a07082016.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dry7.a07082016.MenuActivity;
import com.dry7.a07082016.R;
import com.dry7.a07082016.adapters.ProductsAdapter;
import com.dry7.a07082016.databinding.FragmentProductsBinding;
import com.dry7.a07082016.models.Category;
import com.dry7.a07082016.models.Product;
import com.dry7.a07082016.mvvp.ViewModel;
import com.dry7.a07082016.mvvp.ViewModelFragment;
import com.dry7.a07082016.viewmodels.ProductsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Subscription;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends ViewModelFragment {
    private ProductsViewModel viewModel;

    @BindView(R.id.productsList)
    RecyclerView productsList;

    /** RecycleView adapter */
    private ProductsAdapter adapter;

    /** Subscriptions */
    private Subscription subscriptionRealm;

    public ProductsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        FragmentProductsBinding binding = FragmentProductsBinding.bind(view);
        binding.setViewModel(viewModel);

        productsList.setHasFixedSize(true);

        productsList.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
//
//        adapter = new ProductsAdapter(this);
//        productsList.setAdapter(adapter);
//
//        try (Realm realmInstance = Realm.getDefaultInstance()) {
//            if (this.subscriptionRealm != null && this.subscriptionRealm.isUnsubscribed()) {
//                this.subscriptionRealm.unsubscribe();
//            }
//            this.subscriptionRealm = realmInstance.where(Product.class).findAllSortedAsync("price").asObservable().subscribe(products -> {
////                adapter.setItems(products);
//                Log.d("Coffee", "subscriptionRealm - " + products.size());
//            });
//        }

        return view;
    }

    @Nullable
    @Override
    protected ViewModel createViewModel(@Nullable ViewModel.State savedViewModelState) {
        viewModel = new ProductsViewModel(getContext(), savedViewModelState);
        return viewModel;
    }

    public static class SetCategoryEvent {
        public final String category;

        public SetCategoryEvent(String category) {
            this.category = category;
        }
    }

    @Subscribe
    public void setCategory(SetCategoryEvent event) {
        Log.d("Coffee", "setCategory - " + event.category);
        viewModel.setCategory(event.category);
    }
}
