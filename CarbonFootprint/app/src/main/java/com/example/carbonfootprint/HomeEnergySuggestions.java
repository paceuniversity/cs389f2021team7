package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class HomeEnergySuggestions extends AppCompatActivity {

    double originalTotal, newTotal, amountSubtracted1, amountSubtracted2, amountSubtracted3, amountSubtracted4;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    userInfo currentUser;
    CheckBox checkBoxSealWindows, checkBoxHumidity, checkBoxSpaceHeater, checkBoxSolar, checkBoxShowers, checkBoxUnplug, checkBoxReplaceHeat, checkBoxInsulation, checkBoxOpenDrapes, checkBoxDoorSnakes, checkBoxSealChimney, checkBoxWeatherStripWindows;
    TextView homeEnergyTotal, scoreDetail;
    Button naturalButton, electricityButton, fuelOilButton, propaneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeenergysuggestions);

        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);
        //originalTotal = currentUser.getHomeEnergyTotal();

        checkBoxSealWindows = (CheckBox) findViewById(R.id.checkBoxSealWindows);
        checkBoxHumidity = (CheckBox) findViewById(R.id.checkBoxHumidity);
        checkBoxSpaceHeater = (CheckBox) findViewById(R.id.checkBoxSpaceHeaters);
        naturalButton = (Button) findViewById(R.id.naturalbutton);


        checkBoxSolar = (CheckBox) findViewById(R.id.checkBoxSolar);
        checkBoxShowers = (CheckBox) findViewById(R.id.checkBoxShowers);
        checkBoxUnplug = (CheckBox) findViewById(R.id.checkBoxUnplug);
        electricityButton = (Button) findViewById(R.id.electicityButton);
        checkBoxSolar.setEnabled(false);
        checkBoxShowers.setEnabled(false);
        checkBoxUnplug.setEnabled(false);
        electricityButton.setEnabled(false);


        checkBoxReplaceHeat = (CheckBox) findViewById(R.id.checkBoxReplaceHeat);
        checkBoxInsulation = (CheckBox) findViewById(R.id.checkBoxInsulation);
        checkBoxOpenDrapes = (CheckBox) findViewById(R.id.checkBoxOpenDrapes);
        fuelOilButton = (Button) findViewById(R.id.fuelOilButton);
        checkBoxReplaceHeat.setEnabled(false);
        checkBoxInsulation.setEnabled(false);
        checkBoxOpenDrapes.setEnabled(false);
        fuelOilButton.setEnabled(false);


        checkBoxDoorSnakes = (CheckBox) findViewById(R.id.checkBoxDoorSnake);
        checkBoxSealChimney = (CheckBox) findViewById(R.id.checkBoxSealChimney);
        checkBoxWeatherStripWindows = (CheckBox) findViewById(R.id.checkBoxWeatherStripWindows);
        propaneButton = (Button) findViewById(R.id.propaneButton);
        checkBoxDoorSnakes.setEnabled(false);
        checkBoxSealChimney.setEnabled(false);
        checkBoxWeatherStripWindows.setEnabled(false);
        propaneButton.setEnabled(false);

        homeEnergyTotal = (TextView) findViewById(R.id.homeEnergyTotal);
        scoreDetail = (TextView) findViewById(R.id.scoreDetail);

        naturalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxSealWindows.isChecked() && checkBoxHumidity.isChecked() && checkBoxSpaceHeater.isChecked()) {
                    amountSubtracted1 = 1;
                    newTotal = originalTotal - amountSubtracted1;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted1 + " = " + newTotal);

                } else if (checkBoxHumidity.isChecked() && checkBoxSealWindows.isChecked()) {
                    amountSubtracted1 = .5;
                    newTotal = originalTotal - amountSubtracted1;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted1 + " = " + newTotal);

                } else if (checkBoxSpaceHeater.isChecked() && checkBoxHumidity.isChecked()) {
                    amountSubtracted1 = .5;
                    newTotal = originalTotal - amountSubtracted1;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted1 + " = " + newTotal);
                } else if (checkBoxSealWindows.isChecked() && checkBoxSpaceHeater.isChecked()) {
                    amountSubtracted1 = .5;
                    newTotal = originalTotal - amountSubtracted1;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted1 + " = " + newTotal);
                } else if (checkBoxSealWindows.isChecked()) {
                    amountSubtracted1 = .25;
                    newTotal = originalTotal - amountSubtracted1;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted1 + " = " + newTotal);
                } else if (checkBoxHumidity.isChecked()) {
                    amountSubtracted1 = .25;
                    newTotal = originalTotal - amountSubtracted1;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted1 + " = " + newTotal);
                } else if (checkBoxSpaceHeater.isChecked()) {
                    amountSubtracted1 = .25;
                    newTotal = originalTotal - amountSubtracted1;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted1 + " = " + newTotal);
                }
                checkBoxSolar.setEnabled(true);
                checkBoxShowers.setEnabled(true);
                checkBoxUnplug.setEnabled(true);
                electricityButton.setEnabled(true);
            }
        });

        electricityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkBoxSolar.isChecked() && checkBoxShowers.isChecked() && checkBoxUnplug.isChecked()) {
                    amountSubtracted2 = amountSubtracted1 + 1;
                    newTotal = originalTotal - amountSubtracted2;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted2 + " = " + newTotal);
                } else if (checkBoxSolar.isChecked() && checkBoxShowers.isChecked()) {
                    amountSubtracted2 = amountSubtracted1 + .5;
                    newTotal = originalTotal - amountSubtracted2;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted2 + " = " + newTotal);
                } else if (checkBoxShowers.isChecked() && checkBoxUnplug.isChecked()) {
                    amountSubtracted2 = amountSubtracted1 + .5;
                    newTotal = originalTotal - amountSubtracted2;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted2 + " = " + newTotal);
                } else if (checkBoxSolar.isChecked() && checkBoxUnplug.isChecked()) {
                    amountSubtracted2 = amountSubtracted1 + .5;
                    newTotal = originalTotal - amountSubtracted2;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted2 + " = " + newTotal);
                } else if (checkBoxSolar.isChecked()) {
                    amountSubtracted2 = amountSubtracted1 + .25;
                    newTotal = originalTotal - amountSubtracted2;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted2 + " = " + newTotal);
                } else if (checkBoxShowers.isChecked()) {

                    amountSubtracted2 = amountSubtracted1 + .25;
                    newTotal = originalTotal - amountSubtracted2;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted2 + " = " + newTotal);
                } else if (checkBoxUnplug.isChecked()) {
                    amountSubtracted2 = amountSubtracted1 + .25;
                    newTotal = originalTotal - amountSubtracted2;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted2 + " = " + newTotal);
                }
                checkBoxReplaceHeat.setEnabled(true);
                checkBoxInsulation.setEnabled(true);
                checkBoxOpenDrapes.setEnabled(true);
                fuelOilButton.setEnabled(true);
            }

        });
        fuelOilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxReplaceHeat.isChecked() && checkBoxInsulation.isChecked() && checkBoxOpenDrapes.isChecked()) {
                    amountSubtracted3 = amountSubtracted2 + amountSubtracted1 + 1;
                    newTotal = originalTotal - amountSubtracted3;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted3 + " = " + newTotal);
                } else if (checkBoxReplaceHeat.isChecked() && checkBoxInsulation.isChecked()) {
                    amountSubtracted3 = amountSubtracted2 + amountSubtracted1 + .5;
                    newTotal = originalTotal - amountSubtracted3;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted3 + " = " + newTotal);
                } else if (checkBoxInsulation.isChecked() && checkBoxOpenDrapes.isChecked()) {
                    amountSubtracted3 = amountSubtracted2 + amountSubtracted1 + .5;
                    newTotal = originalTotal - amountSubtracted3;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted3 + " = " + newTotal);
                } else if (checkBoxReplaceHeat.isChecked() && checkBoxOpenDrapes.isChecked()) {
                    amountSubtracted3 = amountSubtracted2 + amountSubtracted1 + .5;
                    newTotal = originalTotal - amountSubtracted3;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted3 + " = " + newTotal);
                } else if (checkBoxReplaceHeat.isChecked()) {
                    amountSubtracted3 = amountSubtracted2 + amountSubtracted1 + .25;
                    newTotal = originalTotal - amountSubtracted3;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted3 + " = " + newTotal);
                } else if (checkBoxInsulation.isChecked()) {
                    amountSubtracted3 = amountSubtracted2 + amountSubtracted1 + .25;
                    newTotal = originalTotal - amountSubtracted3;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted3 + " = " + newTotal);
                } else if (checkBoxOpenDrapes.isChecked()) {
                    amountSubtracted3 = amountSubtracted2 + amountSubtracted1 + .25;
                    newTotal = originalTotal - amountSubtracted3;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted3 + " = " + newTotal);
                }
                checkBoxDoorSnakes.setEnabled(true);
                checkBoxSealChimney.setEnabled(true);
                checkBoxWeatherStripWindows.setEnabled(true);
                propaneButton.setEnabled(true);
            }
        });
        propaneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBoxDoorSnakes.isChecked() && checkBoxSealChimney.isChecked() && checkBoxWeatherStripWindows.isChecked()){
                    amountSubtracted4 = amountSubtracted3 + amountSubtracted2 + amountSubtracted1 + 1;
                    newTotal = originalTotal - amountSubtracted4;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted4 + " = " + newTotal);
                }
                else if(checkBoxDoorSnakes.isChecked() && checkBoxSealChimney.isChecked()){
                    amountSubtracted4 = amountSubtracted3 + amountSubtracted2 + amountSubtracted1 + .5;
                    newTotal = originalTotal - amountSubtracted4;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted4 + " = " + newTotal);
                }
                else if(checkBoxSealChimney.isChecked() && checkBoxWeatherStripWindows.isChecked()){
                    amountSubtracted4 = amountSubtracted3 + amountSubtracted2 + amountSubtracted1 + .5;
                    newTotal = originalTotal - amountSubtracted4;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted4 + " = " + newTotal);
                }
                else if(checkBoxDoorSnakes.isChecked() && checkBoxWeatherStripWindows.isChecked()){
                    amountSubtracted4 = amountSubtracted3 + amountSubtracted2 + amountSubtracted1 + .5;
                    newTotal = originalTotal - amountSubtracted4;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted4 + " = " + newTotal);
                }
                else if(checkBoxDoorSnakes.isChecked()){
                    amountSubtracted4 = amountSubtracted3 + amountSubtracted2 + amountSubtracted1 + .25;
                    newTotal = originalTotal - amountSubtracted4;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted4 + " = " + newTotal);
                }
                else if(checkBoxSealChimney.isChecked()){
                    amountSubtracted4 = amountSubtracted3 + amountSubtracted2 + amountSubtracted1 + .25;
                    newTotal = originalTotal - amountSubtracted4;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted4 + " = " + newTotal);
                }
                else if(checkBoxWeatherStripWindows.isChecked()){
                    amountSubtracted4 = amountSubtracted3 + amountSubtracted2 + amountSubtracted1 + .25;
                    newTotal = originalTotal - amountSubtracted4;
                    homeEnergyTotal.setText(originalTotal + " - " + amountSubtracted4 + " = " + newTotal);
                }
                if(newTotal <  originalTotal / 2)
                {
                    scoreDetail.setText("Good Score!");
                }
                else if(newTotal > originalTotal / 2)
                    scoreDetail.setText("Bad Score!");

            }
        });

    }
}