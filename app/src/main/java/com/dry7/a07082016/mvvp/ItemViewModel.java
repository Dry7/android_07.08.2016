package com.dry7.a07082016.mvvp;

public abstract class ItemViewModel<ITEM_T> extends ViewModel {
    public ItemViewModel() {
        super(null);
    }

    public abstract void setItem(ITEM_T item);
}
