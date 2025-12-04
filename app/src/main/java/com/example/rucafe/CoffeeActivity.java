package com.example.rucafe;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import main.AddIns;
import main.Coffee;
import main.CupSize;

public class CoffeeActivity extends AppCompatActivity {

    private Spinner spinnerSize;
    private ListView listViewAddIns;
    private Button btnAddCoffee;
    private OrderManager orderManager;
    private TextView textCartSummary, textCoffeePrice;
    private static final double TAX_RATE = 0.06625;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);  // create this XML
        textCartSummary = findViewById(R.id.textCartSummary);
        textCoffeePrice = findViewById(R.id.textCoffeePrice);
        orderManager = OrderManager.getInstance();

        spinnerSize = findViewById(R.id.spinnerCoffeeSize);
        listViewAddIns = findViewById(R.id.listViewAddIns);
        btnAddCoffee = findViewById(R.id.btnAddCoffee);

        setupSizeSpinner();
        setupAddInsList();
        updateCartSummary();
        updateCurrentCoffeePrice();
        btnAddCoffee.setOnClickListener(v -> addCoffeeToOrder());
    }
    private void updateCartSummary() {
        int count = orderManager.getCurrentItems().size();
        double subtotal = orderManager.getCurrentSubtotal();
        textCartSummary.setText(
                String.format("Cart: %d item%s  |  $%.2f",
                        count,
                        count == 1 ? "" : "s",
                        subtotal)
        );
    }

    private void updateCurrentCoffeePrice() {
        // Build a temporary Coffee object with current selections to ask it for price
        CupSize size = (CupSize) spinnerSize.getSelectedItem();
        ArrayList<AddIns> chosen = new ArrayList<>();

        for (int i = 0; i < listViewAddIns.getCount(); i++) {
            if (listViewAddIns.isItemChecked(i)) {
                chosen.add((AddIns) listViewAddIns.getItemAtPosition(i));
            }
        }

        Coffee temp = new Coffee(size, 1);
        double price = temp.price(); // or temp.itemPrice(), etc.
        textCoffeePrice.setText(String.format("Coffee price: $%.2f", price));
    }

    private void setupSizeSpinner() {
        ArrayAdapter<CupSize> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                CupSize.values()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(adapter);

        spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateCurrentCoffeePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void setupAddInsList() {
        ArrayAdapter<AddIns> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                AddIns.values()
        );
        listViewAddIns.setAdapter(adapter);
        listViewAddIns.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listViewAddIns.setOnItemClickListener((parent, view, position, id) -> {
            updateCurrentCoffeePrice();
        });
    }


    private void addCoffeeToOrder() {
        CupSize size = (CupSize) spinnerSize.getSelectedItem();
        ArrayList<AddIns> chosen = new ArrayList<>();

        for (int i = 0; i < listViewAddIns.getCount(); i++) {
            if (listViewAddIns.isItemChecked(i)) {
                chosen.add((AddIns) listViewAddIns.getItemAtPosition(i));
            }
        }

        Coffee coffee = new Coffee(size, 1);
        orderManager.addToCurrentOrder(coffee);
        updateCartSummary();
        updateCurrentCoffeePrice(); // resets label in case you clear later
        Toast.makeText(this, "Coffee added to order", Toast.LENGTH_SHORT).show();
    }
}
