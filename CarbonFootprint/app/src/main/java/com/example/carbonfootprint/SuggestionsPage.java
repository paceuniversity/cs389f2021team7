package com.example.carbonfootprint;

import static com.example.carbonfootprint.HomeActivity.databaseHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SuggestionsPage extends AppCompatActivity implements Serializable {
    TextView changePrimaryHeating, ReduceNaturalGas, ReduceElectricity, ReduceFuelOil, ReducePropane, RecycleMetal, RecycleGlass, RecyclePlastic, RecycleNewspaper, RecycleMagazines, CarMiles, CarMaintenance;
    CheckBox changePrimaryHeatingCheck, ReduceNaturalGasCheck, ReduceElectricityCheck, ReduceFuelOilCheck, ReducePropaneCheck, RecycleMetalCheck, RecycleGlassCheck, RecyclePlasticCheck, RecycleNewspaperCheck, RecycleMagazinesCheck, CarMilesCheck, CarMaintenanceCheck;
    userInfo currentUser;
    TextView OriginalResult, SubtractedAmount, newResult;
    Double changePrimaryHeatingValue, ReduceNaturalGasValue, ReduceElectricityValue, ReduceFuelOilValue, ReducePropaneValue, RecycleMetalValue, RecycleGlassValue, RecyclePlasticValue, RecycleNewspaperValue, RecycleMagazinesValue, CarMilesValue, CarMaintenanceValue;
    Double DemoTotal;
    String format;
    Button exitButton;
    String householdNumber, homeEnergy, transportation, waste;
    float newResultPrevious, subtractedAmountPrevious;
    String subtractedAmountText, newResultText;
    ImageView suggestionsInfo;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_suggestions_page);

        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);
        changePrimaryHeatingValue = 0.0;
        ReduceNaturalGasValue = 0.0;
        ReduceElectricityValue = 0.0;
        ReduceFuelOilValue = 0.0;
        ReducePropaneValue = 0.0;
        RecycleMetalValue = 0.0;
        RecycleGlassValue = 0.0;
        RecyclePlasticValue = 0.0;
        RecycleNewspaperValue = 0.0;
        RecycleMagazinesValue = 0.0;
        CarMilesValue = 0.0;
        CarMaintenanceValue = 0.0;
        DemoTotal = Double.parseDouble(currentUser.getDemoTotal());

        suggestionsInfo = findViewById(R.id.suggestionsInfo);
        changePrimaryHeating = findViewById(R.id.changePrimaryHeating);
        ReduceNaturalGas = findViewById(R.id.ReduceNaturalGas);
        ReduceElectricity = findViewById(R.id.ReduceElectricity);
        ReduceFuelOil = findViewById(R.id.ReduceFuelOil);
        ReducePropane = findViewById(R.id.ReducePropane);
        RecycleMetal = findViewById(R.id.RecycleMetal);
        RecycleGlass = findViewById(R.id.RecycleGlass);
        RecyclePlastic = findViewById(R.id.RecyclePlastic);
        RecycleNewspaper = findViewById(R.id.RecycleNewspaper);
        RecycleMagazines = findViewById(R.id.RecycleMagazines);
        CarMiles = findViewById(R.id.CarMiles);
        CarMaintenance = findViewById(R.id.CarMaintenance);


        OriginalResult = findViewById(R.id.OriginalResult);
        SubtractedAmount = findViewById(R.id.SubtractedAmount);
        newResult = findViewById(R.id.newResult);

        changePrimaryHeatingCheck = findViewById(R.id.changePrimaryHeatingCheck);
        ReduceNaturalGasCheck = findViewById(R.id.ReduceNaturalGasCheck);
        ReduceElectricityCheck = findViewById(R.id.ReduceElectricityCheck);
        ReduceFuelOilCheck = findViewById(R.id.ReduceFuelOilCheck);
        ReducePropaneCheck = findViewById(R.id.ReducePropaneCheck);
        RecycleMetalCheck = findViewById(R.id.RecycleMetalCheck);
        RecycleGlassCheck = findViewById(R.id.RecycleGlassCheck);
        RecyclePlasticCheck = findViewById(R.id.RecyclePlasticCheck);
        RecycleNewspaperCheck = findViewById(R.id.RecycleNewspaperCheck);
        RecycleMagazinesCheck = findViewById(R.id.RecycleMagazinesCheck);
        CarMilesCheck = findViewById(R.id.CarMilesCheck);
        CarMaintenanceCheck = findViewById(R.id.CarMaintenanceCheck);

        exitButton = findViewById(R.id.suggestionsExitButton);


        suggestionsInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSuggestionsDialog();
            }
        });


        householdNumber = Integer.toString(currentUser.getHouseholdNumber());
        homeEnergy = String.format("%.2f", currentUser.getHomeEnergyTotal());
        transportation = String.format("%.2f", (currentUser.getTransportationTotal()/Integer.parseInt(householdNumber)));
        waste = String.format("%.2f", currentUser.getWasteTotal());



        if (currentUser.getSavePastResultsCheck()) {
            exitButton.setText("Save and Exit");
        }
        else {
            exitButton.setText("Exit");
        }


        changePrimaryHeating.setVisibility(View.GONE);
        changePrimaryHeatingCheck.setVisibility(View.GONE);

        if (currentUser.getMetalCheck()) {
            RecycleMetal.setVisibility(View.GONE);
            RecycleMetalCheck.setVisibility(View.GONE);
        }
        if (currentUser.getGlassCheck()) {
            RecycleGlass.setVisibility(View.GONE);
            RecycleGlassCheck.setVisibility(View.GONE);
        }
        if (currentUser.getPlasticCheck()) {
            RecyclePlastic.setVisibility(View.GONE);
            RecyclePlasticCheck.setVisibility(View.GONE);
        }
        if (currentUser.getNewspaperCheck()) {
            RecycleNewspaper.setVisibility(View.GONE);
            RecycleNewspaperCheck.setVisibility(View.GONE);
        }
        if (currentUser.getMagazinesCheck()) {
            RecycleMagazines.setVisibility(View.GONE);
            RecycleMagazinesCheck.setVisibility(View.GONE);
        }

        if (Double.parseDouble(currentUser.getNaturalGasValue())/currentUser.getHouseholdNumber() < 40) {
            ReduceNaturalGas.setVisibility(View.GONE);
            ReduceNaturalGasCheck.setVisibility(View.GONE);
        }
        if (Double.parseDouble(currentUser.getElectricityValue())/currentUser.getHouseholdNumber() < 80) {
            ReduceElectricity.setVisibility(View.GONE);
            ReduceElectricityCheck.setVisibility(View.GONE);
        }
        if (Double.parseDouble(currentUser.getFuelOilValue())/currentUser.getHouseholdNumber() < 140) {
            ReduceFuelOil.setVisibility(View.GONE);
            ReduceFuelOilCheck.setVisibility(View.GONE);
        }
        if (Double.parseDouble(currentUser.getPropaneValue())/currentUser.getHouseholdNumber() < 80) {
            ReducePropane.setVisibility(View.GONE);
            ReducePropaneCheck.setVisibility(View.GONE);
        }
        if (Double.parseDouble(currentUser.getCarMilesValue())/currentUser.getHouseholdNumber() < 4.7) {
            CarMiles.setVisibility(View.GONE);
            CarMilesCheck.setVisibility(View.GONE);
        }
        if (currentUser.CarMaintenanceCheck) {
            CarMaintenance.setVisibility(View.GONE);
            CarMaintenanceCheck.setVisibility(View.GONE);
        }


        OriginalResult.setText(String.format("%.2f", (Double.parseDouble(currentUser.getDemoTotal()))));
        newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));

        RecycleMetalCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    RecycleMetalValue = 89.38*0.0005;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
                else {
                    RecycleMetalValue = 0.0;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
            }
        });
        RecycleGlassCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    RecycleGlassValue = 25.39*0.0005;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
                else {
                    RecycleGlassValue = 0.0;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
            }
        });
        RecyclePlasticCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    RecyclePlasticValue = 35.56*0.0005;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
                else {
                    RecyclePlasticValue = 0.0;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
            }
        });
        RecycleNewspaperCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    RecycleNewspaperValue = 113.14*0.0005;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
                else {
                    RecycleNewspaperValue = 0.0;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
            }
        });
        RecycleMagazinesCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    RecycleMagazinesValue = 27.46*0.0005;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
                else {
                    RecycleMagazinesValue = 0.0;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
            }
        });
        ReduceNaturalGasCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ReduceNaturalGasValue = Double.parseDouble(currentUser.getNaturalGasValue())/currentUser.getHouseholdNumber() * 11.2 * 12 * 0.0005 * 0.3;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
                else {
                    ReduceNaturalGasValue = 0.0;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
            }
        });
        ReduceElectricityCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ReduceElectricityValue = Double.parseDouble(currentUser.getElectricityValue())/currentUser.getHouseholdNumber() * 4.6 * 12 * 0.0005 * 0.3;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
                else {
                    ReduceElectricityValue = 0.0;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
            }
        });

        ReduceFuelOilCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ReduceFuelOilValue = Double.parseDouble(currentUser.getFuelOilValue())/currentUser.getHouseholdNumber() * 5.6 * 12 * 0.0005 * 0.3;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
                else {
                    ReduceFuelOilValue = 0.0;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
            }
        });

        ReducePropaneCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ReducePropaneValue = Double.parseDouble(currentUser.getPropaneValue())/currentUser.getHouseholdNumber() * 5 * 12 * 0.0005 * 0.3;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
                else {
                    ReducePropaneValue = 0.0;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
            }
        });

        CarMilesCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    CarMilesValue = Double.parseDouble(currentUser.getCarMilesValue())/currentUser.getHouseholdNumber() * 0.5;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
                else {
                    CarMilesValue = 0.0;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
            }
        });

        CarMaintenanceCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    CarMaintenanceValue = Double.parseDouble(currentUser.getCarMilesValue())/currentUser.getHouseholdNumber() - Double.parseDouble(currentUser.getCarMilesValue())/currentUser.getHouseholdNumber() * 0.96;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
                else {
                    CarMaintenanceValue = 0.0;
                    subtractedAmountPrevious = Float.parseFloat(SubtractedAmount.getText().toString());
                    subtractedAmountText = String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue);

//                    SubtractedAmount.setText(String.format("%.2f", changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    newResultPrevious = Float.parseFloat(newResult.getText().toString());
//                    newResult.setText(String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue)));
                    newResultText = String.format("%.2f", DemoTotal - (changePrimaryHeatingValue + ReduceNaturalGasValue + ReduceElectricityValue + ReduceFuelOilValue + ReducePropaneValue + RecycleMetalValue + RecycleGlassValue + RecyclePlasticValue + RecycleNewspaperValue + RecycleMagazinesValue + CarMilesValue + CarMaintenanceValue));
                    startCountAnimation(SubtractedAmount, subtractedAmountPrevious, Float.parseFloat(subtractedAmountText));
                    startCountAnimation(newResult, newResultPrevious, Float.parseFloat(newResultText));

                }
            }
        });

//        Toast.makeText(this, Double.parseDouble(currentUser.getElectricityValue()) + " " + currentUser.getHouseholdNumber(), Toast.LENGTH_LONG).show();

    }
    public void exitCalculatorButton(View view) {
        if (currentUser.getSavePastResultsCheck()) {
            currentUser.setPastResultsCheck(true);
            SimpleDateFormat s = new SimpleDateFormat("hh:mm aa\nMMM dd, yyyy");
            format = s.format(new Date());
            currentUser.setTimestamp(format);
            PastResultsData pastResults = new PastResultsData(-1, currentUser.getName(), currentUser.getTimestamp(), currentUser.getCountryCode(), householdNumber, homeEnergy, transportation, waste, currentUser.getDemoTotal(), currentUser.getAvgValueWB());
            databaseHelper.add(pastResults);
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(CURRENT_USER_KEY, currentUser);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, DemoHomeActivity.class);
            intent.putExtra(CURRENT_USER_KEY, currentUser);
            startActivity(intent);
        }
    }
    public void startCountAnimation(TextView textView, float value1, float value2) {
        DecimalFormat df = new DecimalFormat("0.00");
        ValueAnimator animator = ValueAnimator.ofFloat(value1, value2);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(df.format(animation.getAnimatedValue()));
            }
        });
        animator.setDuration(200);

        animator.start();
    }
    public void openSuggestionsDialog() {
        SuggestionsDialogue suggestionsDialogue = new SuggestionsDialogue();
        suggestionsDialogue.show(getSupportFragmentManager(), "Suggestions Dialogue");
    }
}