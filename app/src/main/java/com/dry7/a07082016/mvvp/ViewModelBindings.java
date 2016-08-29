package com.dry7.a07082016.mvvp;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class ViewModelBindings {
    @BindingAdapter("recyclerViewViewModel")
    public static void setRecyclerViewViewModel(RecyclerView recyclerView, RecyclerViewViewModel viewModel) {
        Log.d("Coffee", "ViewModelBindings.setRecyclerViewViewModel");
        viewModel.setupRecyclerView(recyclerView);
    }
}
