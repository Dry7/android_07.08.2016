package com.dry7.a07082016.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dry7.a07082016.R;
import com.dry7.a07082016.databinding.FragmentCartBinding;
import com.dry7.a07082016.models.Category;
import com.dry7.a07082016.models.Product;
import com.dry7.a07082016.mvvp.ViewModel;
import com.dry7.a07082016.mvvp.ViewModelFragment;
import com.dry7.a07082016.viewmodels.CartListViewModel;
import com.dry7.a07082016.viewmodels.ProductsViewModel;

import butterknife.BindView;
import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends ViewModelFragment {
    private CartListViewModel viewModel;

    @BindView(R.id.cartList)
    RecyclerView cartList;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        Product product = new Product(1, "Product", "Category", 11.11);

        FragmentCartBinding binding = FragmentCartBinding.bind(view);
        binding.setProduct(product);
        binding.setViewModel(viewModel);

        product.setCategory("New Category");

        return view;
    }

    @Nullable
    @Override
    protected ViewModel createViewModel(@Nullable ViewModel.State savedViewModelState) {
        viewModel = new CartListViewModel(getContext(), null);
        return viewModel;
    }

}
