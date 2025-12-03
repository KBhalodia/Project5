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

import main.MenuItem;

public class OrderItemAdapter extends ArrayAdapter<MenuItem> {

    public OrderItemAdapter(@NonNull Context context, @NonNull List<MenuItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MenuItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_order, parent, false);  // create this XML
        }

        TextView textItemName = convertView.findViewById(R.id.textItemName);
        TextView textItemPrice = convertView.findViewById(R.id.textItemPrice);

        textItemName.setText(item.toString());
        textItemPrice.setText(String.format("$%.2f", item.price()));

        return convertView;
    }
}
