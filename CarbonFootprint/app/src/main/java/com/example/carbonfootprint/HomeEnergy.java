package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;

public class HomeEnergy extends AppCompatActivity implements Serializable {
    double naturalGas;
    double electricity;
    double fuelOil;
    double propane;
    Spinner primaryHeatSpinner;
    public static final String NATURAL = "com.example.carbonfootprint.NATURAL";
    public static final String ELECTRICITY = "com.example.carbonfootprint.ELECTRICITY";
    public static final String FUELOIL = "com.example.carbonfootprint.FUELOIL";
    public static final String PROPANE = "com.example.carbonfootprint.PROPANE";

    EditText naturalGasInput, electricityInput, fuelOilInput, propaneInput;

    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_energy);
        naturalGasInput = (EditText) findViewById(R.id.naturalGasInput);
        electricityInput = (EditText) findViewById(R.id.electricityInput);
        fuelOilInput = (EditText)  findViewById(R.id.fuelOilInput);
        propaneInput = (EditText) findViewById(R.id.propaneInput);
        primaryHeatSpinner = findViewById(R.id.primaryHeatSpinner);
        //Intent intent = getIntent();
        //int numberOfPeople = intent.getIntExtra(InitiateCalculator.HOUSEHOLDNUMBER, 0);


        submit = (Button) findViewById(R.id.estimatedC02);
        primaryHeatSource();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String primaryHeat = primaryHeatSpinner.getSelectedItem().toString();
                if(primaryHeat.equals("Natural Gas") || primaryHeat.equals("Electricity") || primaryHeat.equals("Fuel Oil") || primaryHeat.equals("Propane")) {
                    naturalGas = Integer.valueOf(naturalGasInput.getText().toString());
                    electricity = Integer.valueOf(electricityInput.getText().toString());
                    fuelOil = Integer.valueOf(fuelOilInput.getText().toString());
                    propane = Integer.valueOf(propaneInput.getText().toString());

                    naturalGas = naturalGas * 11.2 * 12;
                    electricity = electricity * 4.6 * 12;
                    fuelOil = fuelOil * 5.6 * 12;
                    propane = propane * 5 * 12;


                    Intent intent = new Intent(HomeEnergy.this, HomeEnergyReduceEmissions.class);
                    intent.putExtra(NATURAL, naturalGas);
                    intent.putExtra(ELECTRICITY, electricity);
                    intent.putExtra(FUELOIL, fuelOil);
                    intent.putExtra(PROPANE, propane);
                    startActivity(intent);
                }
                else if(primaryHeat.equals("Select an Option")){
                    Toast.makeText(HomeEnergy.this, "You need to select a primary heat source", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
    private void primaryHeatSource(){
        ArrayAdapter<String> primaryHeatAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Utility));
        primaryHeatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        primaryHeatSpinner.setAdapter(primaryHeatAdapter);
    }



}