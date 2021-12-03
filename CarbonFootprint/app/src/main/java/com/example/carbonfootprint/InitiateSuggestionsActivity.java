package com.example.carbonfootprint;

import static com.example.carbonfootprint.DemoHomeActivity.databaseHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InitiateSuggestionsActivity extends AppCompatActivity implements Serializable {
    String format;
    Button exitButton;
    userInfo currentUser;
    String householdNumber, homeEnergy, transportation, waste;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_initiate_suggestions);

//        exitButton = findViewById(R.id.exitButton);
//
//        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);
//
//        householdNumber = Integer.toString(currentUser.getHouseholdNumber());
//        homeEnergy = String.format("%.2f", currentUser.getHomeEnergyTotal());
//        transportation = String.format("%.2f", (currentUser.getTransportationTotal()/Integer.parseInt(householdNumber)));
//        waste = String.format("%.2f", currentUser.getWasteTotal());
//
//
//
//        if (currentUser.getSavePastResultsCheck()) {
//            exitButton.setText("Save and Exit");
//        }
//        else {
//            exitButton.setText("Exit");
//        }
//
//    }
//
//    public void exitCalculator(View view) {
//        if (currentUser.getSavePastResultsCheck()) {
//            currentUser.setPastResultsCheck(true);
//            SimpleDateFormat s = new SimpleDateFormat("hh:mm aa\nMMM dd, yyyy");
//            format = s.format(new Date());
//            currentUser.setTimestamp(format);
//            PastResultsData pastResults = new PastResultsData(-1, currentUser.getName(), currentUser.getTimestamp(), currentUser.getCountryCode(), householdNumber, homeEnergy, transportation, waste, currentUser.getDemoTotal(), currentUser.getAvgValueWB());
//            databaseHelper.add(pastResults);
//            Intent intent = new Intent(this, DemoHomeActivity.class);
//            intent.putExtra(CURRENT_USER_KEY, currentUser);
//            startActivity(intent);
//        }
//        else {
//            Intent intent = new Intent(this, DemoHomeActivity.class);
//            intent.putExtra(CURRENT_USER_KEY, currentUser);
//            startActivity(intent);
//        }
//    }
//
//    public void suggestionsPage(View view) {
//        Intent intent = new Intent(this, SuggestionsPage.class);
//        intent.putExtra(CURRENT_USER_KEY, currentUser);
//        startActivity(intent);
    }
}