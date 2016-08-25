package com.dry7.a07082016.viewmodels;

import android.databinding.Bindable;

import com.dry7.a07082016.models.Product;
import com.dry7.a07082016.mvvp.ItemViewModel;

public class ProductViewModel extends ItemViewModel<Product> {
    private Product item;

    @Override
    public void setItem(Product item) {
        this.item = item;
    }

    public void onClick() {
//        notifyPropertyChanged(BR.selected);
    }

    @Bindable
    public Integer getId() {
        return this.item.getId();
    }

    @Bindable
    public String getName() {
        return this.item.getName();
    }

    @Bindable
    public String getCategory() {
        return this.item.getCategory();
    }

    @Bindable
    public String getPrice() {
        return this.item.getPrice().toString() + " 123123";
    }
}
