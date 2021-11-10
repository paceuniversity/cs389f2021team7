package com.example.carbonfootprint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class Transportation extends AppCompatActivity implements AdapterView.OnItemSelectedListener, Serializable {

    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    Spinner numVehicles, maintenanceAnswer, maintenanceRedo, timePeriod1, timePeriod2, timePeriod3, reduceTimePeriod;
    EditText mileage1, milesNum1, mileage2, milesNum2, mileage3, milesNum3;
    int emissionYes1, emissionYes2, emissionYes3;
    TextView emission1;
    userInfo currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation);

        numVehicles = findViewById(R.id.numVehicles);
        maintenanceAnswer = findViewById(R.id.maintenanceAnswer);
        maintenanceRedo = findViewById(R.id.answer);
        timePeriod1 = findViewById(R.id.time);
        timePeriod2 = findViewById(R.id.time2);
        timePeriod3 = findViewById(R.id.time3);
        reduceTimePeriod = findViewById(R.id.reduceTime);
        emission1 = findViewById(R.id.emission1);
        numberOfVehicles();
        maintenanceAnswer();
        maintenanceRedo();
        timePeriod();
        reduceTimePeriod();

        milesNum1 = (EditText) findViewById(R.id.milesNum);
        mileage1 = (EditText) findViewById(R.id.mileage);

        milesNum2 = (EditText) findViewById(R.id.milesNum2);
        mileage2 = (EditText) findViewById(R.id.mileage2);

        milesNum3 = (EditText) findViewById(R.id.milesNum3);
        mileage3 = (EditText) findViewById(R.id.mileage3);

        emissionCalculator();
        //emissionYes1 = (value * 52) / (22 * 19 * 1);
        //emission1.setText(Integer.toString(emissionYes1));
        //emissionNo1 = (Double.parseDouble(milesNum1.getText().toString()) * 52) / ((Double.parseDouble(mileage1.getText().toString()) * 19.6 * 1.01) *.96);

    
    }

    @SuppressLint("SetTextI18n")
    private void emissionCalculator() {
        milesNum1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // Place the logic here for your output edittext
                String temp1 = milesNum1.getText().toString();
                String temp2 = mileage1.getText().toString();
                int value1 = 0;
                int value2 = 0;
                if ((temp1.length() > 0) && (temp2.length() > 0))
                {
                    value1 = Integer.parseInt(temp1);
                    value2 = Integer.parseInt(temp2);
                    emissionYes1 = (value1 * 52) / (value2 * 19);
                    emission1.setText(Integer.toString(emissionYes1));
                }
            }
        });

        milesNum1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // Place the logic here for your output edittext
                String temp1 = milesNum1.getText().toString();
                String temp2 = mileage1.getText().toString();
                int value1 = 0;
                int value2 = 0;
                if ((temp1.length() > 0) && (temp2.length() > 0))
                {
                    value1 = Integer.parseInt(temp1);
                    value2 = Integer.parseInt(temp2);
                    emissionYes1 = (value1 * 52) / (value2 * 19);
                    emission1.setText(Integer.toString(emissionYes1));
                }
            }
        });
    }

    private void reduceTimePeriod() {
        String[] time = getResources().getStringArray(R.array.time);
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, time);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reduceTimePeriod.setAdapter(timeAdapter);
        reduceTimePeriod.setOnItemSelectedListener(this);
    }

    private void timePeriod() {
        String[] time = getResources().getStringArray(R.array.time);
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, time);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timePeriod1.setAdapter(timeAdapter);
        timePeriod1.setOnItemSelectedListener(this);

        String[] time2 = getResources().getStringArray(R.array.time);
        ArrayAdapter<String> timeAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, time2);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timePeriod2.setAdapter(timeAdapter2);
        timePeriod2.setOnItemSelectedListener(this);

        String[] time3 = getResources().getStringArray(R.array.time);
        ArrayAdapter<String> timeAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, time3);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timePeriod3.setAdapter(timeAdapter3);
        timePeriod3.setOnItemSelectedListener(this);
    }

    private void maintenanceRedo() {
        String[] answer = getResources().getStringArray(R.array.answer);
        ArrayAdapter<String> answerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, answer);
        answerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maintenanceRedo.setAdapter(answerAdapter);
        maintenanceRedo.setOnItemSelectedListener(this);
    }

    private void numberOfVehicles() {
        String[] number = getResources().getStringArray(R.array.numVehicles);
        ArrayAdapter<String> vehicleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, number);
        vehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numVehicles.setAdapter(vehicleAdapter);
        numVehicles.setOnItemSelectedListener(this);

    }

    private void maintenanceAnswer() {
        String[] maintenance = getResources().getStringArray(R.array.maintenanceAnswer);
        ArrayAdapter<String> maintenanceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, maintenance);
        maintenanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maintenanceAnswer.setAdapter(maintenanceAdapter);
        maintenanceAnswer.setOnItemSelectedListener(this);
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    public void resultsPage(View view) {
//        currentUser.setDemoTotal("15");
//        Intent intent = new Intent(this, ResultsActivity.class);
//        intent.putExtra(CURRENT_USER_KEY, currentUser);
//        startActivity(intent);
//    }


}
