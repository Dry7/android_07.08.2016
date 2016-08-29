package com.dry7.a07082016.adapters;

import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dry7.a07082016.R;
import com.dry7.a07082016.databinding.ProductItemBinding;
import com.dry7.a07082016.fragments.ProductsFragment;
import com.dry7.a07082016.models.Product;
import com.dry7.a07082016.mvvp.ItemViewModel;
import com.dry7.a07082016.mvvp.RecyclerViewAdapter;
import com.dry7.a07082016.viewmodels.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;

public class ProductsAdapter extends RecyclerViewAdapter<Product, ProductViewModel> {

    private List<Product> items;

    public ProductsAdapter() {
    }

    public ProductsAdapter(List<Product> items) {
        this.items = items;
    }

    class ProductViewHolder<T, VT extends ItemViewModel<T>> extends ItemViewHolder<Product, ProductViewModel> {
        public TextView textView;
        public TextView price;

        public ProductViewHolder(View itemView, ViewDataBinding binding, ProductViewModel viewModel) {
            super(itemView, binding, viewModel);
            Log.d("Coffee", "ProductViewHolder");
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.productCard)
        public void addProduct() {
            viewModel.add2Cart();
        }
    }

    public void onBindViewHolder(RecyclerViewAdapter.ItemViewHolder<Product, ProductViewModel> holder, int position) {
        if (position < this.items.size()) {
            holder.setItem(this.items.get(position));
        }
    }

    /**
     * Set products
     *
     * @param items products
     */
    public void setItems(List<Product> items) {
        this.items = items;
        Log.d("Coffee", "ProductsAdapter.setItems - " + items.size());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public List<Product> getItems() {
        return this.items;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        ProductViewModel viewModel = new ProductViewModel();
        ProductItemBinding binding = ProductItemBinding.bind(itemView);
        binding.setProduct(viewModel);

        return new ProductViewHolder(itemView, binding, viewModel);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder<Product, ProductViewModel> holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }
}