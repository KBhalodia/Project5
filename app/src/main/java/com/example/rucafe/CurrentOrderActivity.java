package com.example.rucafe;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import main.MenuItem;

public class CurrentOrderActivity extends AppCompatActivity {

    private ListView listViewOrderItems;
    private TextView textSubtotal, textTax, textTotal;
    private Button btnRemoveItem, btnPlaceOrder;
    private OrderManager orderManager;
    private OrderItemAdapter adapter;
    private int selectedPosition = -1;
    private static final double TAX_RATE = 0.06625;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);  // create this XML

        orderManager = OrderManager.getInstance();
        textTax = findViewById(R.id.textTax);
        listViewOrderItems = findViewById(R.id.listViewOrderItems);
        textSubtotal = findViewById(R.id.textSubtotal);
        btnRemoveItem = findViewById(R.id.btnRemoveItem);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        textTotal = findViewById(R.id.textTotal);

        setupListView();
        updateSubtotal();

        btnRemoveItem.setOnClickListener(v -> removeSelectedItem());
        btnPlaceOrder.setOnClickListener(v -> confirmPlaceOrder());
    }

    private void setupListView() {
        List<MenuItem> items = orderManager.getCurrentOrder().getItems();
        adapter = new OrderItemAdapter(this, items);
        listViewOrderItems.setAdapter(adapter);

        listViewOrderItems.setOnItemClickListener((parent, view, position, id) ->
                selectedPosition = position
        );
    }

    private void updateSubtotal() {
        double subtotal = orderManager.getCurrentSubtotal();
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        textSubtotal.setText(String.format("Subtotal: $%.2f", subtotal));
        textTax.setText(String.format("Tax: $%.2f", tax));
        textTotal.setText(String.format("Total: $%.2f", total));
    }

    private void removeSelectedItem() {
        if (selectedPosition < 0) {
            Toast.makeText(this, "Select an item first", Toast.LENGTH_SHORT).show();
            return;
        }
        MenuItem item = adapter.getItem(selectedPosition);
        orderManager.removeFromCurrentOrder(item);
        adapter.notifyDataSetChanged();
        selectedPosition = -1;
        updateSubtotal();
    }

    private void confirmPlaceOrder() {
        if (orderManager.getCurrentOrder().getItems().isEmpty()) {
            Toast.makeText(this, "Current order is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Place Order")
                .setMessage("Are you sure you want to place this order?")
                .setPositiveButton("Yes", (dialog, which) -> placeOrder())
                .setNegativeButton("No", null)
                .show();
    }

    private void placeOrder() {
        orderManager.placeCurrentOrder();
        adapter.notifyDataSetChanged();
        updateSubtotal();
        Toast.makeText(this, "Order placed", Toast.LENGTH_SHORT).show();
    }
}
