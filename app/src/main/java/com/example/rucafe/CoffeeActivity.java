package com.example.rucafe;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);  // create this XML

        orderManager = OrderManager.getInstance();

        spinnerSize = findViewById(R.id.spinnerCoffeeSize);
        listViewAddIns = findViewById(R.id.listViewAddIns);
        btnAddCoffee = findViewById(R.id.btnAddCoffee);

        setupSizeSpinner();
        setupAddInsList();

        btnAddCoffee.setOnClickListener(v -> addCoffeeToOrder());
    }

    private void setupSizeSpinner() {
        ArrayAdapter<CupSize> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                CupSize.values()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(adapter);
    }

    private void setupAddInsList() {
        ArrayAdapter<AddIns> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                AddIns.values()
        );
        listViewAddIns.setAdapter(adapter);
        listViewAddIns.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    private void addCoffeeToOrder() {
        CupSize size = (CupSize) spinnerSize.getSelectedItem();
        ArrayList<AddIns> chosen = new ArrayList<>();

        for (int i = 0; i < listViewAddIns.getCount(); i++) {
            if (listViewAddIns.isItemChecked(i)) {
                chosen.add((AddIns) listViewAddIns.getItemAtPosition(i));
            }
        }

        // TODO: adjust constructor to match your Coffee class
        Coffee coffee = new Coffee(size, 1);
        orderManager.addToCurrentOrder(coffee);

        Toast.makeText(this, "Coffee added to order", Toast.LENGTH_SHORT).show();
    }
}
