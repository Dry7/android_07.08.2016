package com.dry7.a07082016.mvvp;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dry7.a07082016.models.Product;

import java.util.ArrayList;

public abstract class RecyclerViewAdapter<ITEM_T, VIEW_MODEL_T extends ItemViewModel<ITEM_T>> extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder<ITEM_T, VIEW_MODEL_T>> {
    protected ArrayList<ITEM_T>items;

    public RecyclerViewAdapter() {
        items = new ArrayList<>();
    }

    public RecyclerViewAdapter(ArrayList<ITEM_T> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public static class ItemViewHolder<T, VT extends ItemViewModel<T>> extends RecyclerView.ViewHolder {
        protected final VT viewModel;
        private final ViewDataBinding binding;

        public ItemViewHolder(View itemView, ViewDataBinding binding, VT viewModel) {
            super(itemView);
            this.binding = binding;
            this.viewModel = viewModel;
        }

        public void setItem(T item) {
            Log.d("Coffee", "RecyclerViewAdapter.ItemViewHolder.setItem");
            viewModel.setItem(item);
            binding.executePendingBindings();
        }
    }
}
