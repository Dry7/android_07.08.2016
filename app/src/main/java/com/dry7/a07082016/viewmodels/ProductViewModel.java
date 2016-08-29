package com.dry7.a07082016.viewmodels;

import android.databinding.Bindable;
import android.util.Log;

import com.dry7.a07082016.models.Product;
import com.dry7.a07082016.mvvp.ItemViewModel;
import com.google.android.gms.common.api.BooleanResult;

public class ProductViewModel extends ItemViewModel<Product> {
    private Product item;

    @Override
    public void setItem(Product item) {
        Log.d("Coffee", "ProductViewModel.setItem");
        this.item = item;
        notifyChange();
    }

    public void onClick() {
//        notifyPropertyChanged(BR.selected);
    }

    @Bindable
    public Integer getId() {
        return this.item != null ? this.item.getId() : 0;
    }

    @Bindable
    public String getName() {
        return this.item != null ? this.item.getName() : "";
    }

    @Bindable
    public String getCategory() {
        return this.item != null ? this.item.getCategory() : "";
    }

    @Bindable
    public String getPrice() {
        return this.item != null ? this.item.getPrice().toString() + " $" : "";
    }

    public Boolean getSelected() {
        return false;
    }

    public boolean add2Cart() {
        Log.d("Coffee", "add2Cart - " + this.getName());
        notifyChange();
        return true;
    }
}
