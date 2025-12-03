package com.example.rucafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import main.Donut;

public class DonutOptionsAdapter extends RecyclerView.Adapter<DonutOptionsAdapter.DonutViewHolder> {

    private final Context context;
    private final List<Donut> donuts;
    private final OrderManager orderManager;

    public DonutOptionsAdapter(Context context, List<Donut> donuts) {
        this.context = context;
        this.donuts = donuts;
        this.orderManager = OrderManager.getInstance();
    }

    @NonNull
    @Override
    public DonutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_donut, parent, false);  // create this XML
        return new DonutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonutViewHolder holder, int position) {
        Donut donut = donuts.get(position);

        // TODO: replace with donut.getFlavor() if you have that
        holder.textDonutName.setText(donut.toString());

        // TODO: call donut.price() or similar
        double price = donut.price();
        holder.textDonutPrice.setText(String.format("$%.2f", price));

        // TODO: map donut flavor -> drawable resource
        // e.g., if (donut.getFlavor().equals("Glazed")) { imageDonut.setImageResource(R.drawable.donut_glazed); }
        holder.imageDonut.setImageResource(R.drawable.donut_placeholder);

        holder.buttonAdd.setOnClickListener(v -> {
            // Create a new donut with quantity 1
            Donut itemToAdd = new Donut(donut.getType(), donut.getFlavor(), 1); // TODO adjust constructor
            orderManager.addToCurrentOrder(itemToAdd);

            Toast.makeText(context,
                    "Added " + donut.toString() + " to order",
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return donuts.size();
    }

    static class DonutViewHolder extends RecyclerView.ViewHolder {
        ImageView imageDonut;
        TextView textDonutName, textDonutPrice;
        Button buttonAdd;

        DonutViewHolder(@NonNull View itemView) {
            super(itemView);
            imageDonut = itemView.findViewById(R.id.imageDonut);
            textDonutName = itemView.findViewById(R.id.textDonutName);
            textDonutPrice = itemView.findViewById(R.id.textDonutPrice);
            buttonAdd = itemView.findViewById(R.id.buttonAddDonut);
        }
    }
}
