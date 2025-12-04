package com.example.rucafe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import main.Donut;
import main.DonutCategory;

public class DonutActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDonuts;
    private DonutOptionsAdapter adapter;
    private List<Donut> donutOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);  // create this XML

        recyclerViewDonuts = findViewById(R.id.recyclerViewDonuts);
        recyclerViewDonuts.setLayoutManager(new LinearLayoutManager(this));

        donutOptions = buildDonutOptions();
        adapter = new DonutOptionsAdapter(this, donutOptions);
        recyclerViewDonuts.setAdapter(adapter);
    }

    private List<Donut> buildDonutOptions() {
        List<Donut> list = new ArrayList<>();

        // TODO: adjust constructor if your Donut signature is different
        list.add(new Donut(DonutCategory.YEAST, "Glazed", 1));
        list.add(new Donut(DonutCategory.YEAST, "Chocolate Frosted", 1));
        list.add(new Donut(DonutCategory.YEAST, "Strawberry Frosted", 1));
        list.add(new Donut(DonutCategory.YEAST, "Jelly", 1));
        list.add(new Donut(DonutCategory.YEAST, "Boston Cream", 1));

        list.add(new Donut(DonutCategory.SEASONAL, "Mochi", 1));
        list.add(new Donut(DonutCategory.SEASONAL, "Matcha", 1));
        list.add(new Donut(DonutCategory.CAKE, "Powdered", 1));
        list.add(new Donut(DonutCategory.CAKE, "Red Velvet", 1));
        list.add(new Donut(DonutCategory.CAKE, "Strawberry Sprinkled", 1));
        list.add(new Donut(DonutCategory.CAKE, "Double Chocolate", 1));

        list.add(new Donut(DonutCategory.HOLE, "Donut Holes", 1));
        list.add(new Donut(DonutCategory.YEAST, "Vanilla Frosted", 1));
        list.add(new Donut(DonutCategory.CAKE, "Cookies & Cream", 1));

        return list;
    }
}
