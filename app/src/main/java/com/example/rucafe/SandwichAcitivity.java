package com.example.rucafe;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import main.AddOns;
import main.Bread;
import main.Protein;
import main.Sandwich;

public class SandwichActivity extends AppCompatActivity {

    private Spinner spinnerBread, spinnerProtein;
    private ListView listViewAddOns;
    private Button btnAddSandwich;
    private OrderManager orderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich);  // create this XML

        orderManager = OrderManager.getInstance();

        spinnerBread = findViewById(R.id.spinnerBread);
        spinnerProtein = findViewById(R.id.spinnerProtein);
        listViewAddOns = findViewById(R.id.listViewAddOns);
        btnAddSandwich = findViewById(R.id.btnAddSandwich);

        setupSpinners();
        setupAddOnsList();

        btnAddSandwich.setOnClickListener(v -> addSandwichToOrder());
    }

    private void setupSpinners() {
        spinnerBread.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Bread.values())
        );

        spinnerProtein.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Protein.values())
        );
    }

    private void setupAddOnsList() {
        ArrayAdapter<AddOns> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                AddOns.values()
        );
        listViewAddOns.setAdapter(adapter);
        listViewAddOns.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    private void addSandwichToOrder() {
        Bread bread = (Bread) spinnerBread.getSelectedItem();
        Protein protein = (Protein) spinnerProtein.getSelectedItem();
        ArrayList<AddOns> chosen = new ArrayList<>();

        for (int i = 0; i < listViewAddOns.getCount(); i++) {
            if (listViewAddOns.isItemChecked(i)) {
                chosen.add((AddOns) listViewAddOns.getItemAtPosition(i));
            }
        }

        // TODO: adjust constructor to match your Sandwich class
        Sandwich sandwich = new Sandwich(bread, protein, chosen, 1);
        orderManager.addToCurrentOrder(sandwich);

        Toast.makeText(this, "Sandwich added to order", Toast.LENGTH_SHORT).show();
    }
}
