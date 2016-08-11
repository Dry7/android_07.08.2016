package com.dry7.a07082016;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dry7.a07082016.fragments.BalancesFragment;
import com.dry7.a07082016.fragments.BillsFragment;
import com.dry7.a07082016.fragments.CardsFragment;
import com.dry7.a07082016.fragments.CloseFragment;
import com.dry7.a07082016.fragments.CouponsFragment;
import com.dry7.a07082016.fragments.IngredientFragment;
import com.dry7.a07082016.fragments.MessagesFragment;
import com.dry7.a07082016.fragments.MoneyFragment;
import com.dry7.a07082016.fragments.RevenueFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        EventBus.getDefault().register(this);
    }

    public static class MessageEvent {
        public final String message;

        public MessageEvent(String message) {
            this.message = message;
        }
    }

    @Subscribe
    public void setAction(MenuActivity.MessageEvent event) {
        Log.d("Coffee", "setAction2 + " + event.message);

        Fragment frag;
        switch (event.message) {
            case "Cards": frag = new CardsFragment(); break;
            case "Coupons": frag = new CouponsFragment(); break;
            case "Bills": frag = new BillsFragment(); break;
            case "Warehouse change": frag = new IngredientFragment(); break;
            case "Money change": frag = new MoneyFragment(); break;
            case "Messages": frag = new MessagesFragment(); break;
            case "Warehouse": frag = new BalancesFragment(); break;
            case "Close session": frag = new CloseFragment(); break;
            default: frag = new RevenueFragment(); break;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, frag);
        transaction.commit();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
