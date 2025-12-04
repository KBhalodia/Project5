package com.example.rucafe;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import main.MenuItem;
import main.Order;

public class StoreOrdersActivity extends AppCompatActivity {

    private ListView listViewStoreOrders;
    private StoreOrdersAdapter adapter;
    private OrderManager orderManager;

    // Use the same tax rate you’re using in CurrentOrderActivity
    private static final double TAX_RATE = 0.06625; // TODO: adjust if your project uses a different rate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);  // XML you already have

        listViewStoreOrders = findViewById(R.id.listViewStoreOrders);
        orderManager = OrderManager.getInstance();

        // TODO: adjust this if your StoreOrders class uses a different method than getOrders()
        List<Order> orders = orderManager.getStoreOrders().getOrders();

        adapter = new StoreOrdersAdapter(this, orders);
        listViewStoreOrders.setAdapter(adapter);

        // When you tap an order in the list, show a receipt-style dialog
        listViewStoreOrders.setOnItemClickListener((parent, view, position, id) -> {
            Order selected = adapter.getItem(position);
            if (selected != null) {
                showOrderDetailsDialog(selected);
            }
        });
    }

    /**
     * Shows a popup dialog with all details of the selected order,
     * like a little receipt: each item, its price, subtotal, tax, and total.
     */
    private void showOrderDetailsDialog(Order order) {
        StringBuilder sb = new StringBuilder();

        // TODO: adjust method name if your Order class doesn't use getItems()
        for (MenuItem item : order.getItems()) {
            // item.toString() should describe the item (flavor/size/add-ins/etc.)
            sb.append(item.toString())
                    .append("  -  $")
                    .append(String.format("%.2f", item.price()))
                    .append("\n");
        }

        // TODO: adjust method name if your Order uses something like getOrderTotal(), price(), etc.
        double subtotal = order.getTotal();
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        sb.append("\n")
                .append(String.format("Subtotal: $%.2f\n", subtotal))
                .append(String.format("Tax: $%.2f\n", tax))
                .append(String.format("Total: $%.2f\n", total));

        // TODO: adjust getOrderNumber() if your class uses a different name or field
        new AlertDialog.Builder(this)
                .setTitle("Order #" + order.getOrderNumber())
                .setMessage(sb.toString())
                .setPositiveButton("OK", null)
                .show();
    }
}
