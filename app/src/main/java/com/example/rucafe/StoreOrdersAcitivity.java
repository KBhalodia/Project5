package com.example.rucafe;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import main.Order;

public class StoreOrdersActivity extends AppCompatActivity {

    private ListView listViewStoreOrders;
    private StoreOrdersAdapter adapter;
    private OrderManager orderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);  // create this XML

        listViewStoreOrders = findViewById(R.id.listViewStoreOrders);
        orderManager = OrderManager.getInstance();

        // TODO: adjust method if your StoreOrders exposes orders differently
        List<Order> orders = orderManager.getStoreOrders().getOrders();
        adapter = new StoreOrdersAdapter(this, orders);
        listViewStoreOrders.setAdapter(adapter);
    }
}
