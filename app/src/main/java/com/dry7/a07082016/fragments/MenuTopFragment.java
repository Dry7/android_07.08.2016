package com.dry7.a07082016.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dry7.a07082016.DashboardActivity;
import com.dry7.a07082016.MenuActivity;
import com.dry7.a07082016.R;
import com.dry7.a07082016.models.Category;
import com.dry7.a07082016.services.RestClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Top menu
 */
public class MenuTopFragment extends Fragment {

    String[] categories = {"Coffee", "Beverages"};

    @BindView(R.id.menuTopList) RecyclerView mRecyclerView;

    private MenuTopAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Subscription subscription;

    public MenuTopFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu_top, container, false);
        ButterKnife.bind(this, v);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<String> items = new ArrayList<String>();

        items.add("Home");
        items.add("Menu");
        mAdapter = new MenuTopAdapter(items);
        mRecyclerView.setAdapter(mAdapter);

        this.subscription = RestClient.getInstance().categoriesList().observeOn(AndroidSchedulers.mainThread()).subscribe(categories -> {
                Log.d("Coffee", "Categories onNext");
                ArrayList<String> items2 = new ArrayList<String>();
                items2.add("Home");
                if(getContext().getClass().equals(DashboardActivity.class)) {
                    for (Category category : categories) {
                        Log.d("Coffee", category.getName());
                        items2.add(category.getName());
                    }
                }
                items2.add("Menu");
                mAdapter.setDataset(items2);
                mAdapter.notifyDataSetChanged();
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        if (this.subscription != null && this.subscription.isUnsubscribed()) {
            this.subscription.unsubscribe();
        }
        super.onDestroyView();
    }

    class MenuTopAdapter extends RecyclerView.Adapter<MenuTopAdapter.ViewHolder> {
        private ArrayList<String> mDataset = new ArrayList<>();

        class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;

            public ViewHolder(TextView v) {
                super(v);
                textView = v;
            }
        }

        public MenuTopAdapter() {}

        public MenuTopAdapter(ArrayList<String> myDataset) {
            mDataset = myDataset;
        }

        public void setDataset(ArrayList<String> items) {
            this.mDataset = items;
        }

        @Override
        public MenuTopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_top_item, parent, false);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch ( ((TextView)v.findViewById(android.R.id.text1)).getText().toString() ) {
                        case "Home":
                            toDashboard();
                            break;
                        case "Menu":
                            toMenu();
                            break;
                    }
                }
            });
            ViewHolder vh = new ViewHolder((TextView)v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(mDataset.get(position));
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

    /**
     * Go to dashboard
     */
    @OnClick(R.id.logo)
    public void toDashboard()
    {
        if(!this.getContext().getClass().equals(DashboardActivity.class)) {
            startActivity(new Intent(this.getContext(), DashboardActivity.class));
        }
    }

    /**
     * Go to menu
     */
    public void toMenu()
    {
        if(!this.getContext().getClass().equals(MenuActivity.class)) {
            startActivity(new Intent(this.getContext(), MenuActivity.class));
        }
    }
}
