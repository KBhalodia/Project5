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
    private int getDonutImageRes(Donut donut) {
        String flavor = donut.getFlavor(); // or however you store name

        if (flavor.equals("Glazed")) return R.drawable.glazed;
        if (flavor.equals("Chocolate Frosted")) return R.drawable.chocolate_frosted;
        if (flavor.equals("Strawberry Frosted")) return R.drawable.straw_frosted;
        if (flavor.equals("Jelly")) return R.drawable.jelly;
        if (flavor.equals("Boston Cream")) return R.drawable.boston_cream;
        if (flavor.equals("Matcha")) return R.drawable.matcha;
        if (flavor.equals("Mochi")) return R.drawable.mochi;
        if (flavor.equals("Powdered")) return R.drawable.powdered;
        if (flavor.equals("Red Velvet")) return R.drawable.redvelvet;
        if (flavor.equals("Strawberry Sprinkled")) return R.drawable.straw_sprinkledonut;
        if (flavor.equals("Vanilla Frosted")) return R.drawable.vanilla_donut;
        if (flavor.equals("Double Chocolate")) return R.drawable.double_chocolate;
        if (flavor.equals("Cookies & Cream")) return R.drawable.cookies_cream_donut;
        if (flavor.equals("Donut Holes")) return R.drawable.donut_holes;

        return R.drawable.donut_icon;
    }


    @Override
    public void onBindViewHolder(@NonNull DonutViewHolder holder, int position) {
        Donut donut = donuts.get(position);

        holder.textDonutName.setText(donut.getFlavor()); // better than toString
        double price = donut.price();
        holder.textDonutPrice.setText(String.format("$%.2f", price));

        holder.imageDonut.setImageResource(getDonutImageRes(donut));

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
