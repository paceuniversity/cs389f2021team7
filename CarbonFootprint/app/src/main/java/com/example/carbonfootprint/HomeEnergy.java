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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
    ImageView homeEnergyInfo;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    Spinner primaryHeatSpinner;
    public static final String NATURAL = "com.example.carbonfootprint.NATURAL";
    public static final String ELECTRICITY = "com.example.carbonfootprint.ELECTRICITY";
    public static final String FUELOIL = "com.example.carbonfootprint.FUELOIL";
    public static final String PROPANE = "com.example.carbonfootprint.PROPANE";
    userInfo currentUser;
    TextInputEditText naturalGasInput, electricityInput, fuelOilInput, propaneInput;
    TextInputLayout naturalGasLayout, electricityLayout, fuelOilLayout, propaneLayout;
    TextView errorText1;
    String primaryHeat;
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
        naturalGasLayout = findViewById(R.id.naturalGasInputLayout);
        electricityLayout = findViewById(R.id.electricityInputLayout);
        fuelOilLayout = findViewById(R.id.fuelOilInputLayout);
        propaneLayout = findViewById(R.id.propaneInputLayout);

        primaryHeatSpinner = findViewById(R.id.primaryHeatSpinner);
        submit = (Button) findViewById(R.id.estimatedC02);
        errorText1 = findViewById(R.id.errorText1);
        errorText1.setVisibility(View.GONE);

        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);

        Intent intent = getIntent();
        naturalGas = currentUser.getNaturalGas();
        electricity = currentUser.getElectricity();
        fuelOil = currentUser.getFuelOil();
        propane = currentUser.getPropane();

        primaryHeatSource();

        currentUser.setHeqcomplete(false);

        homeEnergyInfo = findViewById(R.id.homeEnergyInfo);

        homeEnergyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeEnergyDialog();
            }
        });

        primaryHeatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                primaryHeat = primaryHeatSpinner.getSelectedItem().toString();
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
                if (editable.length() == 0) {
                    currentUser.setHeq1(false);
                }
                else if (editable.length() > 4) {
                    naturalGasLayout.setError("You have exceeded the character limit");
                    currentUser.setHeq1(false);
                }
                else {
                    naturalGasLayout.setError(null);
                    currentUser.setHeq1(true);
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
                if (editable.length() == 0) {
                    currentUser.setHeq2(false);
                }
                else if (editable.length() > 4){
                    electricityLayout.setError("You have exceeded the character limit");
                    currentUser.setHeq2(false);
                }
                else {
                    electricityLayout.setError(null);
                    currentUser.setHeq2(true);
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
                if (editable.length() == 0) {
                    currentUser.setHeq3(false);
                }
                else if (editable.length() > 4) {
                    fuelOilLayout.setError("You have exceeded the character limit");
                    currentUser.setHeq3(false);
                }
                else {
                    fuelOilLayout.setError(null);
                    currentUser.setHeq3(true);
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
                if (editable.length() == 0) {
                    currentUser.setHeq4(false);
                }
                else if (editable.length() > 4) {
                    propaneLayout.setError("You have exceeded the character limit");
                    currentUser.setHeq4(false);
                }
                else {
                    propaneLayout.setError(null);
                    currentUser.setHeq4(true);
                    currentUser.setPropaneValue(propaneInput.getText().toString());
                }
                }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUser.isHeq1() && currentUser.isHeq2() && currentUser.isHeq3() && currentUser.isHeq4() && !(primaryHeat.equals("Select an Option"))) {
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
                    currentUser.setPrimaryHeat(primaryHeat);
                    intent.putExtra(CURRENT_USER_KEY, currentUser);
                    startActivity(intent);
                }
                else {
                    errorText1.setVisibility(View.VISIBLE);
                }

            }
        });


    }
    private void primaryHeatSource(){
        ArrayAdapter<String> primaryHeatAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Utility));
        primaryHeatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        primaryHeatSpinner.setAdapter(primaryHeatAdapter);
    }

    public void openHomeEnergyDialog() {
        HomeEnergyDialogue homeEnergyDialogue = new HomeEnergyDialogue();
        homeEnergyDialogue.show(getSupportFragmentManager(), "Home Energy Dialogue");
    }

}