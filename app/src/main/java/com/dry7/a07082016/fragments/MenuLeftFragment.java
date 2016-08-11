package com.dry7.a07082016.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dry7.a07082016.MenuActivity;
import com.dry7.a07082016.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Left menu
 */
public class MenuLeftFragment extends Fragment {

    String[] items = {"Menu", "Cards", "Coupons", "Bills", "Warehouse change", "Money change", "Messages", "Warehouse", "Close session", "Exit"};

    @BindView(R.id.menuLeftList) RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public MenuLeftFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_left, container, false);

        ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MenuLeftAdapter(items);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    class MenuLeftAdapter extends RecyclerView.Adapter<MenuLeftAdapter.ViewHolder> {
        private String[] mDataset;

        class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;

            public ViewHolder(TextView v) {
                super(v);
                textView = v;
            }
        }

        public MenuLeftAdapter(String[] myDataset) {
            mDataset = myDataset;
        }

        @Override
        public MenuLeftAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_left_item, parent, false);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new MenuActivity.MessageEvent(((TextView)v.findViewById(android.R.id.text1)).getText().toString()));
                }
            });
            ViewHolder vh = new ViewHolder((TextView)v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(mDataset[position]);
        }

        @Override
        public int getItemCount() {
            return mDataset.length;
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
