package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

public class HomeEnergy extends AppCompatActivity implements Serializable {
    double naturalGas;
    double electricity;
    double fuelOil;
    double propane;
    double naturalGas2;
    double electricity2;
    double fuelOil2;
    double propane2;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    Spinner primaryHeatSpinner;
    public static final String NATURAL = "com.example.carbonfootprint.NATURAL";
    public static final String ELECTRICITY = "com.example.carbonfootprint.ELECTRICITY";
    public static final String FUELOIL = "com.example.carbonfootprint.FUELOIL";
    public static final String PROPANE = "com.example.carbonfootprint.PROPANE";
    userInfo currentUser;
    TextInputEditText naturalGasInput, electricityInput, fuelOilInput, propaneInput;

    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_home_energy);
        naturalGasInput = findViewById(R.id.naturalGasEditText);
        electricityInput = findViewById(R.id.electricityEditText);
        fuelOilInput = findViewById(R.id.fuelOilEditText);
        propaneInput = findViewById(R.id.propaneEditText);
        primaryHeatSpinner = findViewById(R.id.primaryHeatSpinner);
        submit = (Button) findViewById(R.id.estimatedC02);
        submit.setEnabled(false);
        electricityInput.setEnabled(false);
        fuelOilInput.setEnabled(false);
        propaneInput.setEnabled(false);

        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);


        Intent intent = getIntent();
        naturalGas = currentUser.getNaturalGas();
        electricity = currentUser.getElectricity();
        fuelOil = currentUser.getFuelOil();
        propane = currentUser.getPropane();


        primaryHeatSource();



        primaryHeatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String primaryHeat = primaryHeatSpinner.getSelectedItem().toString();
                if(primaryHeat.equals("Select an Option")){
                    naturalGasInput.setEnabled(false);
                }
                else{
                    naturalGasInput.setEnabled(true);
                }
            }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        naturalGasInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0)
                electricityInput.setEnabled(false);
                else {
                    electricityInput.setEnabled(true);
                    currentUser.setNaturalGasValue(naturalGasInput.getText().toString());
                }
            }
        });

        electricityInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0)
                fuelOilInput.setEnabled(false);
                else {
                    fuelOilInput.setEnabled(true);
                    currentUser.setElectricityValue(electricityInput.getText().toString());
                }
            }
        });
        fuelOilInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0)
                propaneInput.setEnabled(false);
                else {
                    propaneInput.setEnabled(true);
                    currentUser.setFuelOilValue(fuelOilInput.getText().toString());
                }
            }
        });
        propaneInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0)
                submit.setEnabled(false);
                else {
                    submit.setEnabled(true);
                    currentUser.setPropaneValue(propaneInput.getText().toString());
                }
                }
        });
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


                    Intent intent = new Intent(HomeEnergy.this, HomeEnergyReduceEmissions.class);
                    currentUser.setNaturalGas(naturalGas);
                    currentUser.setElectricity(electricity);
                    currentUser.setFuelOil(fuelOil);
                    currentUser.setPropane(propane);
                    intent.putExtra(CURRENT_USER_KEY, currentUser);
                    startActivity(intent);




            }
        });


    }
    private void primaryHeatSource(){
        ArrayAdapter<String> primaryHeatAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Utility));
        primaryHeatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        primaryHeatSpinner.setAdapter(primaryHeatAdapter);
    }



}