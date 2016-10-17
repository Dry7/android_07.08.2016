package com.dry7.a07082016.viewmodels;

import android.databinding.Bindable;
import android.util.Log;

import com.dry7.a07082016.models.CartItem;
import com.dry7.a07082016.models.Product;
import com.dry7.a07082016.mvvp.ItemViewModel;
import com.dry7.a07082016.services.CartService;

public class CartItemViewModel extends ItemViewModel<CartItem> {
    private CartItem item;

    @Override
    public void setItem(CartItem item) {
        Log.d("Coffee", "CartItemViewModel.setItem");
        this.item = item;
        notifyChange();
    }

    public void onClick() {
//        notifyPropertyChanged(BR.selected);
    }

    @Bindable
    public Product getProduct() {
        return this.item != null ? this.item.getProduct() : new Product();
    }

    @Bindable
    public Integer getAmount() {
        return this.item != null ? this.item.getAmount() : 0;
    }
}
