package com.example.rucafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import main.Order;

public class StoreOrdersAdapter extends ArrayAdapter<Order> {

    public StoreOrdersAdapter(@NonNull Context context, @NonNull List<Order> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Order order = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_store_order, parent, false);  // create this XML
        }

        TextView textOrderNumber = convertView.findViewById(R.id.textOrderNumber);
        TextView textOrderTotal = convertView.findViewById(R.id.textOrderTotal);

        // TODO: adjust methods for your Order class
        textOrderNumber.setText("Order #" + order.getOrderNumber());
        textOrderTotal.setText(String.format("$%.2f", order.getTotal()));

        return convertView;
    }
}
