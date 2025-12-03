package com.example.rucafe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnDonuts, btnCoffee, btnSandwich, btnCurrentOrder, btnStoreOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // make sure this XML exists

        btnDonuts = findViewById(R.id.btnDonuts);
        btnCoffee = findViewById(R.id.btnCoffee);
        btnSandwich = findViewById(R.id.btnSandwich);
        btnCurrentOrder = findViewById(R.id.btnCurrentOrder);
        btnStoreOrders = findViewById(R.id.btnStoreOrders);

        btnDonuts.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, DonutActivity.class)));

        btnCoffee.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CoffeeActivity.class)));

        btnSandwich.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, SandwichActivity.class)));

        btnCurrentOrder.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CurrentOrderActivity.class)));

        btnStoreOrders.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, StoreOrdersActivity.class)));
    }
}
