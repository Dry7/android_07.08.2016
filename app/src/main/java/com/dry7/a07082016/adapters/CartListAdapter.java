package com.dry7.a07082016.adapters;

import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dry7.a07082016.R;
import com.dry7.a07082016.databinding.CartItemBinding;
import com.dry7.a07082016.databinding.ProductItemBinding;
import com.dry7.a07082016.models.CartItem;
import com.dry7.a07082016.models.Product;
import com.dry7.a07082016.mvvp.ItemViewModel;
import com.dry7.a07082016.mvvp.RecyclerViewAdapter;
import com.dry7.a07082016.viewmodels.CartItemViewModel;
import com.dry7.a07082016.viewmodels.ProductViewModel;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartListAdapter extends RecyclerViewAdapter<CartItem, CartItemViewModel> {

    private List<CartItem> items;

    public CartListAdapter() {
    }

    public CartListAdapter(List<CartItem> items) {
        this.items = items;
    }

    class CartItemViewHolder<T, VT extends ItemViewModel<T>> extends ItemViewHolder<CartItem, CartItemViewModel> {
        public TextView textView;
        public TextView price;

        public CartItemViewHolder(View itemView, ViewDataBinding binding, CartItemViewModel viewModel) {
            super(itemView, binding, viewModel);
            Log.d("Coffee", "ProductViewHolder");
            ButterKnife.bind(this, itemView);
        }
    }

    public void onBindViewHolder(ItemViewHolder<CartItem, CartItemViewModel> holder, int position) {
        if (position < this.items.size()) {
            holder.setItem(this.items.get(position));
        }
    }

    /**
     * Set products
     *
     * @param items products
     */
    public void setItems(List<CartItem> items) {
        this.items = items;
        Log.d("Coffee", "ProductsAdapter.setItems - " + items.size());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public List<CartItem> getItems() {
        return this.items;
    }

    @Override
    public CartItemViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        CartItemViewModel viewModel = new CartItemViewModel();
        CartItemBinding binding = CartItemBinding.bind(itemView);
        binding.setItem(viewModel);

        return new CartItemViewHolder(itemView, binding, viewModel);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder<CartItem, CartItemViewModel> holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }
}