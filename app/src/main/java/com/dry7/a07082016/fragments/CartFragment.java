package com.dry7.a07082016.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dry7.a07082016.R;
import com.dry7.a07082016.databinding.FragmentCartBinding;
import com.dry7.a07082016.models.Category;
import com.dry7.a07082016.models.Product;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


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

        product.setCategory("New Category");

        return view;
    }

}
