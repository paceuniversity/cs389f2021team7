package com.example.carbonfootprint;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;


public class SuggestionPage extends AppCompatActivity implements Serializable {
    userInfo currentUser;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    int currentTransportation;
    CompoundButton firstSuggestion, secondSuggestion, thirdSuggestion, fourthSuggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);
        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);

        firstSuggestion = findViewById(R.id.checkBox);
        secondSuggestion = findViewById(R.id.checkBox2);
        thirdSuggestion = findViewById(R.id.checkBox3);
        fourthSuggestion = findViewById(R.id.checkBox4);

        class Check_class implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //currentTransportation = currentUser.getWasteTotal();

            if (firstSuggestion.isChecked()) {
                //currentTransportation = currentTransportation;
                currentUser.setTransportationTotal(currentTransportation);
            }
            if (secondSuggestion.isChecked()) {
                //currentTransportation = currentTransportation;
                currentUser.setTransportationTotal(currentTransportation);
            }
            if (thirdSuggestion.isChecked()) {
                currentTransportation = (int) (currentTransportation * 0.96);
                currentUser.setTransportationTotal(currentTransportation);
            }
            if (fourthSuggestion.isChecked()) {
                //currentTransportation = currentTransportation;
                currentUser.setTransportationTotal(currentTransportation);
            }

        }
    }

    }
    /*
    public void resultsPage(View view) {

        Intent intent = new Intent(this, ResultsActivity.class);
        currentUser.setTransportationTotal(total);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }
    public void homeEnergyPage(View view) {

        Intent intent = new Intent(this, HomeEnergy.class);
        currentUser.setTransportationTotal(emission1);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }
    public void wastePage(View view) {

        Intent intent = new Intent(this, Waste.class);
        currentUser.setTransportationTotal(emission1);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }

     */
}
