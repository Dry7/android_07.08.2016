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

import com.dry7.a07082016.DashboardActivity;
import com.dry7.a07082016.MenuActivity;
import com.dry7.a07082016.R;
import com.dry7.a07082016.adapters.MenuTopAdapter;
import com.dry7.a07082016.models.Category;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import rx.Subscription;


/**
 * Top menu
 */
public class MenuTopFragment extends Fragment {

    /** Elements to the left of the categories */
    ArrayList<String> before = new ArrayList<String>(){{ add("Home"); }};

    /** Elements to the right of the categories */
    ArrayList<String> after  = new ArrayList<String>(){{ add("Menu"); }};

    @BindView(R.id.menuTopList)
    RecyclerView mRecyclerView;

    /** RecycleView adapter */
    private MenuTopAdapter mAdapter;

    /** Subscriptions */
    private Subscription subscriptionRealm;

    public MenuTopFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu_top, container, false);
        ButterKnife.bind(this, v);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        mAdapter = new MenuTopAdapter(this, this.before, null, this.after);
        mRecyclerView.setAdapter(mAdapter);

        if(getContext().getClass().equals(DashboardActivity.class)) {
            try (Realm realmInstance = Realm.getDefaultInstance()) {
                if (this.subscriptionRealm != null && this.subscriptionRealm.isUnsubscribed()) {
                    this.subscriptionRealm.unsubscribe();
                }
                this.subscriptionRealm = realmInstance.where(Category.class).findAllAsync().asObservable().subscribe(categories -> {
                    mAdapter.setItems(categories);
                    Log.d("Coffee", "Subscribe");
                });
            }
        } else {
            mAdapter = new MenuTopAdapter(this, this.before, null, this.after);
            mRecyclerView.setAdapter(mAdapter);
        }

        return v;
    }

    @Override
    public void onDestroyView() {
        if (this.subscriptionRealm != null && this.subscriptionRealm.isUnsubscribed()) {
            this.subscriptionRealm.unsubscribe();
        }
        super.onDestroyView();
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
