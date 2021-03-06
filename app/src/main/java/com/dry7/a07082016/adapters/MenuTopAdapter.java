package com.dry7.a07082016.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dry7.a07082016.MenuActivity;
import com.dry7.a07082016.R;
import com.dry7.a07082016.fragments.MenuTopFragment;
import com.dry7.a07082016.fragments.ProductsFragment;
import com.dry7.a07082016.models.Category;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import io.realm.RealmResults;

public class MenuTopAdapter extends RecyclerView.Adapter<MenuTopAdapter.ViewHolder> {
    /** Menu fragment */
    private MenuTopFragment menu;

    /** Elements to the left of the categories */
    private ArrayList<String> before;

    /** Categories */
    private RealmResults<Category> items;

    /** Elements to the right of the categories */
    private ArrayList<String> after;

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    public MenuTopAdapter() {}

    public MenuTopAdapter(MenuTopFragment menu, ArrayList<String> before, RealmResults<Category> items, ArrayList<String> after) {
        this.menu   = menu;
        this.before = before;
        this.items  = items;
        this.after  = after;
    }

    /**
     * Set categories
     *
     * @param items Categories
     */
    public void setItems(RealmResults<Category> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    @Override
    public MenuTopAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_top_item, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Coffee", "Click");
                String value = ((TextView)v.findViewById(android.R.id.text1)).getText().toString();
                switch ( value ) {
                    case "Home":
                        menu.toDashboard();
                        EventBus.getDefault().post(new ProductsFragment.SetCategoryEvent(""));
                        break;
                    case "Menu":
                        menu.toMenu();
                        break;
                    default:
                        EventBus.getDefault().post(new ProductsFragment.SetCategoryEvent(value));
                        Toast.makeText(parent.getContext(), value, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        ViewHolder vh = new ViewHolder((TextView)v);
        return vh;
    }

    /**
     * We calculate where to get information
     *
     * @param holder Holder
     * @param position Index element
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name;
        if (position < this.getBeforeSize()) {
            name = this.before.get(position);
        } else if (position >= this.getAfterSize()+this.getItemsSize()) {
            name = this.after.get(position-this.getBeforeSize()-this.getItemsSize());
        } else  {
            name = this.items.get(position-this.getBeforeSize()).getName();
        }
        holder.textView.setText(name);
    }

    /**
     * Total size
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        return this.getBeforeSize() + this.getItemsSize() + this.getAfterSize();
    }

    /**
     * Count of before elements
     *
     * @return int
     */
    private int getBeforeSize() {
        return this.before != null ? this.before.size() : 0;
    }

    /**
     * Count of items elements
     *
     * @return int
     */
    private int getItemsSize() {
        return this.items != null ? this.items.size() : 0;
    }

    /**
     * Count of after elements
     *
     * @return int
     */
    private int getAfterSize() {
        return this.after != null ? this.after.size() : 0;
    }
}