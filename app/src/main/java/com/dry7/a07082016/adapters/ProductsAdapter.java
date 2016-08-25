package com.dry7.a07082016.adapters;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dry7.a07082016.R;
import com.dry7.a07082016.databinding.ProductItemBinding;
import com.dry7.a07082016.fragments.ProductsFragment;
import com.dry7.a07082016.models.Product;
import com.dry7.a07082016.mvvp.RecyclerViewAdapter;
import com.dry7.a07082016.viewmodels.ProductViewModel;

import butterknife.ButterKnife;
import io.realm.RealmResults;

public class ProductsAdapter extends RecyclerViewAdapter<Product, ProductViewModel> {
    /** Products fragment */
    private ProductsFragment fragment;

    /** Products */
    private RealmResults<Product> items;

    class ViewHolder extends ItemViewHolder<Product, ProductViewModel> {
        public TextView textView;
        public TextView price;

        public ViewHolder(View itemView, ViewDataBinding binding, ProductViewModel viewModel) {
            super(itemView, binding, viewModel);
            ButterKnife.bind(this, itemView);
        }
    }

//    public ProductsAdapter(ProductsFragment menu) {
//        this.fragment = fragment;
//    }
//
//    public ProductsAdapter(ProductsFragment menu, RealmResults<Product> items) {
//        this.fragment = fragment;
//        this.items    = items;
//    }

    /**
     * Set products
     *
     * @param items products
     */
    public void setItems(RealmResults<Product> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    public RealmResults<Product> getItems() {
        return items;
    }

    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String value = ((TextView)v.findViewById(android.R.id.text1)).getText().toString();
//                Toast.makeText(parent.getContext(), value, Toast.LENGTH_SHORT).show();
//            }
//        });
        ProductViewModel viewModel = new ProductViewModel();
        ProductItemBinding binding = ProductItemBinding.bind(itemView);
        binding.setProduct(viewModel);

        return new ViewHolder(itemView, binding, viewModel);
    }
}