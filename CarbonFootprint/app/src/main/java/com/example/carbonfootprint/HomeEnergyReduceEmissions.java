package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;

public class HomeEnergyReduceEmissions extends AppCompatActivity implements Serializable {
    Spinner spinnerRefridge, spinnerFurnaceBoiler, spinnerWindow;
    Button transportationbutton, wastebutton, homeenergypreviousbutton;
    double acThermostat, winterThermostat, reduceLighting, coldWater, emissionsTotal, emissionsTotalAfterReduce, naturalGas, electricity, fuelOil, propane;
    userInfo currentUser;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    TextInputEditText acThermostatInput, winterThermostatInput, reduceLightingInput, coldWaterInput;
    TextInputLayout acThermostatLayout, winterThermostatLayout, reduceLightingLayout, coldWaterLayout;
    ImageView homeEnergyReduceInfo;
    String refridge;
    String furnaceBoiler, primaryHeat;
    String window;
    TextView errorText2;
    Boolean disabledCheck = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_home_energy_reduce_emissions);

        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);



        acThermostatInput = findViewById(R.id.acThermostatEditText);
        winterThermostatInput = findViewById(R.id.winterThermostatEditText);
        reduceLightingInput = findViewById(R.id.reduceLightingEditText);
        coldWaterInput = findViewById(R.id.coldWaterEditText);
        acThermostatLayout = findViewById(R.id.acThermostatInputLayout);
        winterThermostatLayout = findViewById(R.id.winterThermostatInputLayout);
        reduceLightingLayout = findViewById(R.id.reduceLightingInputLayout);
        coldWaterLayout = findViewById(R.id.coldWaterInputLayout);
        errorText2 = findViewById(R.id.errorText2);
        errorText2.setVisibility(View.GONE);

        naturalGas = currentUser.getNaturalGas();
        electricity = currentUser.getElectricity();
        fuelOil = currentUser.getFuelOil();
        propane = currentUser.getPropane();
        primaryHeat = currentUser.getPrimaryHeat();


        spinnerRefridge = findViewById(R.id.starEnergyRefrigerator);
        spinnerFurnaceBoiler = findViewById(R.id.starEnergyFurnaceBoiler);
        spinnerWindow = findViewById(R.id.starEnergyWindow);

        if(primaryHeat.equals("Electricity") || primaryHeat.equals("Propane")){
            disabledCheck = true;
            currentUser.setRheq6(true);
            spinnerFurnaceBoiler.setEnabled(false);
        }

        transportationbutton = findViewById(R.id.transportationbutton);
        wastebutton = findViewById(R.id.wasteSuggestionsBttn);
//        homeenergypreviousbutton = findViewById(R.id.homenergypreviousbutton);

        if (currentUser.isImperialSystem()) {
            acThermostatLayout.setHint("Degrees (°F)");
            winterThermostatLayout.setHint("Degrees (°F)");
        }
        else if (currentUser.isMetricSystem()) {
            acThermostatLayout.setHint("Degrees (°C)");
            winterThermostatLayout.setHint("Degrees (°C)");
        }



        homeEnergyReduceInfo = findViewById(R.id.homeEnergyReduceInfo);

        homeEnergyReduceInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeEnergyReduceDialog();
            }
        });

        energyStarRefridge();
        energyStarFurnaceBoiler();
        energyStarWindow();

        acThermostatInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    currentUser.setRheq1(false);
                }
                else if (editable.length() > 2) {
                    acThermostatLayout.setError("You have exceeded the character limit");
                    currentUser.setRheq1(false);
                }
                else {
                    acThermostatLayout.setError(null);
                    currentUser.setRheq1(true);
                }
            }
        });

        winterThermostatInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    currentUser.setRheq2(false);
                }
                else if (editable.length() > 2) {
                    winterThermostatLayout.setError("You have exceeded the character limit");
                    currentUser.setRheq2(false);
                }
                else {
                    winterThermostatLayout.setError(null);
                    currentUser.setRheq2(true);
                }
            }
        });

        reduceLightingInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    currentUser.setRheq3(false);
                }
                else if (editable.length() > 2) {
                    reduceLightingLayout.setError("You have exceeded the character limit");
                    currentUser.setRheq3(false);
                }
                else {
                    reduceLightingLayout.setError(null);
                    currentUser.setRheq3(true);
                }
            }
        });

        coldWaterInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    currentUser.setRheq4(false);
                }
                else if (editable.length() > 2) {
                    coldWaterLayout.setError("You have exceeded the character limit");
                    currentUser.setRheq4(false);
                }
                else {
                    coldWaterLayout.setError(null);
                    currentUser.setRheq4(true);
                }
            }
        });

    }



    public void HomeReducetoHomeEnergy(View view) {
        Intent intent = new Intent(HomeEnergyReduceEmissions.this, HomeEnergy.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }

    public void HomeReducetoTransportation(View view) {
        refridge = spinnerRefridge.getSelectedItem().toString();
        furnaceBoiler = spinnerFurnaceBoiler.getSelectedItem().toString();
        window = spinnerWindow.getSelectedItem().toString();

        if (refridge.equals("Select an Option")) {
            currentUser.setRheq5(false);
        }
        else {
            currentUser.setRheq5(true);
        }
        if (furnaceBoiler.equals("Select an Option") && !disabledCheck) {
            currentUser.setRheq6(false);
        }
        else {
            currentUser.setRheq6(true);
        }
        if (window.equals("Select an Option")) {
            currentUser.setRheq7(false);
        }
        else {
            currentUser.setRheq7(true);
        }

        if (currentUser.isRheq1() && currentUser.isRheq2() && currentUser.isRheq3() && currentUser.isRheq4() && currentUser.isRheq5() && currentUser.isRheq6() && currentUser.isRheq7()) {
            currentUser.setHeqcomplete(true);
            homeEnergyCalculation();
            Intent intent = new Intent(HomeEnergyReduceEmissions.this, Transportation.class);
            currentUser.setHomeEnergyTotal(emissionsTotalAfterReduce);
            intent.putExtra(CURRENT_USER_KEY, currentUser);
            startActivity(intent);
        }
        else {
            currentUser.setHeqcomplete(false);
            errorText2.setVisibility(View.VISIBLE);
        }
    }


    public void HomeReducetoWaste(View view) {
        refridge = spinnerRefridge.getSelectedItem().toString();
        furnaceBoiler = spinnerFurnaceBoiler.getSelectedItem().toString();
        window = spinnerWindow.getSelectedItem().toString();

        if (refridge.equals("Select an Option")) {
            currentUser.setRheq5(false);
        }
        else {
            currentUser.setRheq5(true);
        }
        if (furnaceBoiler.equals("Select an Option") && !disabledCheck) {
            currentUser.setRheq6(false);
        }
        else {
            currentUser.setRheq6(true);
        }
        if (window.equals("Select an Option")) {
            currentUser.setRheq7(false);
        }
        else {
            currentUser.setRheq7(true);
        }

        if (currentUser.isRheq1() && currentUser.isRheq2() && currentUser.isRheq3() && currentUser.isRheq4() && currentUser.isRheq5() && currentUser.isRheq6() && currentUser.isRheq7()) {
            currentUser.setHeqcomplete(true);
            homeEnergyCalculation();
            Intent intent = new Intent(HomeEnergyReduceEmissions.this, Waste.class);
            currentUser.setHomeEnergyTotal(emissionsTotalAfterReduce);
            intent.putExtra(CURRENT_USER_KEY, currentUser);
            startActivity(intent);
        }
        else {
            currentUser.setHeqcomplete(false);
            errorText2.setVisibility(View.VISIBLE);
        }
    }

    public void HomeReducetoResults(View view) {
        refridge = spinnerRefridge.getSelectedItem().toString();
        furnaceBoiler = spinnerFurnaceBoiler.getSelectedItem().toString();
        window = spinnerWindow.getSelectedItem().toString();

        if (refridge.equals("Select an Option")) {
            currentUser.setRheq5(false);
        }
        else {
            currentUser.setRheq5(true);
        }
        if (furnaceBoiler.equals("Select an Option") && !disabledCheck) {
            currentUser.setRheq6(false);
        }
        else {
            currentUser.setRheq6(true);
        }
        if (window.equals("Select an Option")) {
            currentUser.setRheq7(false);
        }
        else {
            currentUser.setRheq7(true);
        }

        if (currentUser.isRheq1() && currentUser.isRheq2() && currentUser.isRheq3() && currentUser.isRheq4() && currentUser.isRheq5() && currentUser.isRheq6() && currentUser.isRheq7()) {
            currentUser.setHeqcomplete(true);
            homeEnergyCalculation();
            Intent intent = new Intent(HomeEnergyReduceEmissions.this, InitiateCalculator.class);
            currentUser.setHomeEnergyTotal(emissionsTotalAfterReduce);
            intent.putExtra(CURRENT_USER_KEY, currentUser);
            startActivity(intent);
        }
        else {
            currentUser.setHeqcomplete(false);
            errorText2.setVisibility(View.VISIBLE);
        }
    }



    private void energyStarRefridge() {
        ArrayAdapter<String> refridgeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.starProducts));
        refridgeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRefridge.setAdapter(refridgeAdapter);
    }

    private void energyStarFurnaceBoiler() {
        ArrayAdapter<String> furnaceBoilerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.starProducts));
        furnaceBoilerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRefridge.setAdapter(furnaceBoilerAdapter);
    }

    private void energyStarWindow() {
        ArrayAdapter<String> windowAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.starProducts));
        windowAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRefridge.setAdapter(windowAdapter);
    }

    private void showToast(String text) {
        Toast.makeText(HomeEnergyReduceEmissions.this, text, Toast.LENGTH_LONG).show();
    }

    private void homeEnergyCalculation() {
        if (currentUser.isImperialSystem()) {
            acThermostat = Integer.valueOf(acThermostatInput.getText().toString());
            winterThermostat = Integer.valueOf(winterThermostatInput.getText().toString());
        }
        else if (currentUser.isMetricSystem()) {
            acThermostat = Integer.valueOf(acThermostatInput.getText().toString())*5/9;
            winterThermostat = Integer.valueOf(winterThermostatInput.getText().toString())*5/9;
        }

        reduceLighting = Integer.valueOf(reduceLightingInput.getText().toString());
        coldWater = Integer.valueOf(coldWaterInput.getText().toString());

        double acMoneySaved = 0;
        double acCO2Saved = 0;


        for (int i = 1; i <= acThermostat; i++) {

            if (i % 2 == 1) {
                acMoneySaved = acMoneySaved + 4;
                acCO2Saved = acCO2Saved + 20;
            }
            if (i % 2 == 0) {
                acMoneySaved = acMoneySaved + 5;
                acCO2Saved = acCO2Saved + 21;
            }
        }

        acThermostat = acCO2Saved;

        double acWinterMoneySaved = 0;
        double acWinterCO2Saved = 0;

        for (int i = 1; i <= winterThermostat; i++) {

            if (i % 2 == 1) {
                acWinterMoneySaved = acWinterMoneySaved + 7;
                acWinterCO2Saved = acWinterCO2Saved + 84;
            }
            if (i % 2 == 0) {
                acWinterMoneySaved = acWinterMoneySaved + 8;
                acWinterCO2Saved = acWinterCO2Saved + 84;
            }
        }

        winterThermostat = acWinterCO2Saved;

        double lightingMoneySaved = 0;
        double lightingCO2Saved = 0;

        for (int i = 1; i <= reduceLighting; i++) {
            lightingMoneySaved = lightingMoneySaved + 4;
            lightingCO2Saved = lightingCO2Saved + 18;
        }

        reduceLighting = lightingCO2Saved;

        double coldWaterMoneySaved = 0;
        double coldWaterCO2Saved = 0;

        for (int i = 1; i <= coldWater; i++) {
            coldWaterMoneySaved += 6;
            if (i % 2 == 1) {
                coldWaterCO2Saved += 27;
            }
            if (i % 2 == 0) {
                coldWaterCO2Saved += 28;
            }
        }
        coldWater = coldWaterCO2Saved;

        refridge = spinnerRefridge.getSelectedItem().toString();
        furnaceBoiler = spinnerFurnaceBoiler.getSelectedItem().toString();
        window = spinnerWindow.getSelectedItem().toString();
        int energyStarRefridgeCO2 = 0;
        if (refridge.equals("Will Do"))
            energyStarRefridgeCO2 += 177;
        else
            energyStarRefridgeCO2 = 0;

        int energyStarFurnaceBoiler = 0;
        if (furnaceBoiler.equals("Will Do"))
            energyStarFurnaceBoiler += 728;
        else
            energyStarFurnaceBoiler = 0;

        int energyStarWindow = 0;
        if (window.equals("Will Do"))
            energyStarWindow += 2947;
        else
            energyStarWindow = 0;


        emissionsTotal = emissionsTotal + (electricity + fuelOil + propane + naturalGas);

        emissionsTotalAfterReduce = emissionsTotal - (acThermostat + coldWater + reduceLighting + winterThermostat + energyStarRefridgeCO2 + energyStarWindow + energyStarFurnaceBoiler);
        emissionsTotal = emissionsTotal / 2000;
        emissionsTotalAfterReduce = emissionsTotalAfterReduce / 2000;

    }
    public void openHomeEnergyReduceDialog() {
        HomeEnergyReduceDialogue homeEnergyReduceDialogue = new HomeEnergyReduceDialogue();
        homeEnergyReduceDialogue.show(getSupportFragmentManager(), "Home Energy Reduce Dialogue");
    }
}


