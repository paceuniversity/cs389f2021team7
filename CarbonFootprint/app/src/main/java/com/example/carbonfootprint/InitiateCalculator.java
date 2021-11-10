package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class InitiateCalculator extends AppCompatActivity implements Serializable {
    EditText householdNumberInput;
    int numberOfPeople;
    Button homeEnergy;
    Button transportation;
    Button waste;
    public static final String HOUSEHOLDNUMBER = "com.example.homeenergy.HOUSEHOLDNUMBER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiate_calculator);
        householdNumberInput = (EditText) findViewById(R.id.houseHoldNumberInput);



        homeEnergy = (Button) findViewById(R.id.homeEnergyBttn);
        transportation = (Button) findViewById(R.id.transportationBttn);
        waste = (Button) findViewById(R.id.wasteBttn);

        homeEnergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOfPeople = Integer.valueOf(householdNumberInput.getText().toString());
                Intent intent = new Intent(InitiateCalculator.this, HomeEnergy.class);
                intent.putExtra(HOUSEHOLDNUMBER, numberOfPeople);
                startActivity(intent);
            }
        });
        transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(InitiateCalculator.this, Transportation.class);
                startActivity(intent);
            }
        });
        waste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InitiateCalculator.this, Waste.class);
                startActivity(intent);
            }
        });
    }
}