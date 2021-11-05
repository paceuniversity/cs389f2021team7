package com.example.homeenergy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeEnergy extends AppCompatActivity {
double naturalGas;
double electricity;
double fuelOil;
double propane;

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

        submit = (Button) findViewById(R.id.estimatedC02);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                naturalGas = Integer.valueOf(naturalGasInput.getText().toString());
                electricity = Integer.valueOf(electricityInput.getText().toString());
                fuelOil = Integer.valueOf(fuelOilInput.getText().toString());
                propane = Integer.valueOf(propaneInput.getText().toString());

                naturalGas = naturalGas * 11.2 * 12;
                electricity = electricity * 4.6 * 12;
                fuelOil = fuelOil * 5.6 * 12;
                propane = propane * 5 * 12;

                showToast(String.valueOf(naturalGas));
                showToast(String.valueOf(electricity));
                showToast(String.valueOf(fuelOil));
                showToast(String.valueOf(propane));

            }
        });
    }

    private void showToast(String text){
        Toast.makeText(HomeEnergy.this, text, Toast.LENGTH_LONG).show();
    }
}