package com.example.carbonfootprint;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class Transportation extends AppCompatActivity implements Serializable {

    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    Spinner numVehicles, maintenanceAnswer, maintenanceRedo, timePeriod1, timePeriod2, timePeriod3, reduceTimePeriod1, reduceTimePeriod2, reduceTimePeriod3;
    EditText mileage1, milesNum1, mileage2, milesNum2, mileage3, milesNum3;
    TextView maintenanceQuestion, milesQuestion, milesQuestion2, milesQuestion3, miles, miles2, miles3, mileageQuestion, mileageQuestion2, mileageQuestion3, milesText, milesText2, milesText3;
    View divider, divider2, divider3;
    Boolean maintenanceCheck, timeCheckYear1, timeCheckYear2, timeCheckYear3, checkGone1, checkGone2, checkGone3;
    double milesNum1Value, milesNum2Value, milesNum3Value, mileage1Value, mileage2Value, mileage3Value, vehicle1Total, vehicle2Total, vehicle3Total, transportationTotal;
//    EditText reduceMile1, reduceMileage1, reduceMile2, reduceMileage2, reduceMile3, reduceMileage3;
    int emission1 = 0;
    int emission2 = 0;
    int emission3 = 0;
    int total = 0;
    userInfo currentUser;
    Button next, previous, submit;
    double emissionsTotalAfterReduce;
    ArrayList<String> numVehiclesArray, maintenanceArray, timePeriod1Array, timePeriod2Array, timePeriod3Array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_transportation);

        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);
        emissionsTotalAfterReduce = getIntent().getDoubleExtra(HomeEnergyReduceEmissions.CURRENT_USER_KEY, 0);

        maintenanceCheck = false;
        timeCheckYear1 = false;
        timeCheckYear2 = false;
        timeCheckYear3 = false;

        numVehicles = findViewById(R.id.numVehicles);
        maintenanceAnswer = findViewById(R.id.maintenanceAnswer);
//        maintenanceRedo = findViewById(R.id.answer);
        timePeriod1 = findViewById(R.id.time);
        timePeriod2 = findViewById(R.id.time2);
        timePeriod3 = findViewById(R.id.time3);
        maintenanceQuestion = findViewById(R.id.maintenanceQuestion);
        milesQuestion = findViewById(R.id.milesQuestion);
        milesQuestion2 = findViewById(R.id.milesQuestion2);
        milesQuestion3 = findViewById(R.id.milesQuestion3);
        miles = findViewById(R.id.miles);
        miles2 = findViewById(R.id.miles2);
        miles3 = findViewById(R.id.miles3);
        mileageQuestion = findViewById(R.id.mileageQuestion);
        mileageQuestion2 = findViewById(R.id.mileageQuestion2);
        mileageQuestion3 = findViewById(R.id.mileageQuestion3);
        milesText = findViewById(R.id.milesText);
        milesText2 = findViewById(R.id.milesText2);
        milesText3 = findViewById(R.id.milesText3);
        divider = findViewById(R.id.divider);
        divider2 = findViewById(R.id.divider2);
        divider3 = findViewById(R.id.divider3);
//        reduceTimePeriod1 = findViewById(R.id.reduceTime);
//        reduceTimePeriod2 = findViewById(R.id.reduceTime2);
//        reduceTimePeriod3 = findViewById(R.id.reduceTime3);
//        numberOfVehicles();
//        maintenanceAnswer();
////        maintenanceRedo();
//        timePeriod();
//        //reduceTimePeriod();

        milesNum1 = (EditText) findViewById(R.id.milesNum);
        mileage1 = (EditText) findViewById(R.id.mileage);
        milesNum2 = (EditText) findViewById(R.id.milesNum2);
        mileage2 = (EditText) findViewById(R.id.mileage2);
        milesNum3 = (EditText) findViewById(R.id.milesNum3);
        mileage3 = (EditText) findViewById(R.id.mileage3);


        numVehiclesArray = new ArrayList<>();
        numVehiclesArray.add("0");
        numVehiclesArray.add("1");
        numVehiclesArray.add("2");
        numVehiclesArray.add("3");

        ArrayAdapter<String> numVehiclesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numVehiclesArray);
        numVehiclesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numVehicles.setAdapter(numVehiclesAdapter);

        numVehicles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i) == "0"){
                    checkGone1 = true;
                    checkGone2 = true;
                    checkGone3 = true;

                    maintenanceQuestion.setVisibility(View.GONE);
                    maintenanceAnswer.setVisibility(View.GONE);
                    divider.setVisibility(View.GONE);
                    milesQuestion.setVisibility(View.GONE);
                    milesNum1.setVisibility(View.GONE);
                    miles.setVisibility(View.GONE);
                    timePeriod1.setVisibility(View.GONE);
                    mileageQuestion.setVisibility(View.GONE);
                    mileage1.setVisibility(View.GONE);
                    milesText.setVisibility(View.GONE);

                    divider2.setVisibility(View.GONE);
                    milesQuestion2.setVisibility(View.GONE);
                    milesNum2.setVisibility(View.GONE);
                    miles2.setVisibility(View.GONE);
                    timePeriod2.setVisibility(View.GONE);
                    mileageQuestion2.setVisibility(View.GONE);
                    mileage2.setVisibility(View.GONE);
                    milesText2.setVisibility(View.GONE);

                    divider3.setVisibility(View.GONE);
                    milesQuestion3.setVisibility(View.GONE);
                    milesNum3.setVisibility(View.GONE);
                    miles3.setVisibility(View.GONE);
                    timePeriod3.setVisibility(View.GONE);
                    mileageQuestion3.setVisibility(View.GONE);
                    mileage3.setVisibility(View.GONE);
                    milesText3.setVisibility(View.GONE);
                }
                else if (adapterView.getItemAtPosition(i) == "1") {
                    checkGone1 = false;
                    checkGone2 = true;
                    checkGone3 = true;

                    maintenanceQuestion.setVisibility(View.VISIBLE);
                    maintenanceAnswer.setVisibility(View.VISIBLE);
                    divider.setVisibility(View.VISIBLE);
                    milesQuestion.setVisibility(View.VISIBLE);
                    milesNum1.setVisibility(View.VISIBLE);
                    miles.setVisibility(View.VISIBLE);
                    timePeriod1.setVisibility(View.VISIBLE);
                    mileageQuestion.setVisibility(View.VISIBLE);
                    mileage1.setVisibility(View.VISIBLE);
                    milesText.setVisibility(View.VISIBLE);

                    divider2.setVisibility(View.GONE);
                    milesQuestion2.setVisibility(View.GONE);
                    milesNum2.setVisibility(View.GONE);
                    miles2.setVisibility(View.GONE);
                    timePeriod2.setVisibility(View.GONE);
                    mileageQuestion2.setVisibility(View.GONE);
                    mileage2.setVisibility(View.GONE);
                    milesText2.setVisibility(View.GONE);

                    divider3.setVisibility(View.GONE);
                    milesQuestion3.setVisibility(View.GONE);
                    milesNum3.setVisibility(View.GONE);
                    miles3.setVisibility(View.GONE);
                    timePeriod3.setVisibility(View.GONE);
                    mileageQuestion3.setVisibility(View.GONE);
                    mileage3.setVisibility(View.GONE);
                    milesText3.setVisibility(View.GONE);
                }
                else if (adapterView.getItemAtPosition(i) == "2") {
                    checkGone1 = false;
                    checkGone2 = false;
                    checkGone3 = true;


                    maintenanceQuestion.setVisibility(View.VISIBLE);
                    maintenanceAnswer.setVisibility(View.VISIBLE);
                    divider.setVisibility(View.VISIBLE);
                    milesQuestion.setVisibility(View.VISIBLE);
                    milesNum1.setVisibility(View.VISIBLE);
                    miles.setVisibility(View.VISIBLE);
                    timePeriod1.setVisibility(View.VISIBLE);
                    mileageQuestion.setVisibility(View.VISIBLE);
                    mileage1.setVisibility(View.VISIBLE);
                    milesText.setVisibility(View.VISIBLE);

                    divider2.setVisibility(View.VISIBLE);
                    milesQuestion2.setVisibility(View.VISIBLE);
                    milesNum2.setVisibility(View.VISIBLE);
                    miles2.setVisibility(View.VISIBLE);
                    timePeriod2.setVisibility(View.VISIBLE);
                    mileageQuestion2.setVisibility(View.VISIBLE);
                    mileage2.setVisibility(View.VISIBLE);
                    milesText2.setVisibility(View.VISIBLE);

                    divider3.setVisibility(View.GONE);
                    milesQuestion3.setVisibility(View.GONE);
                    milesNum3.setVisibility(View.GONE);
                    miles3.setVisibility(View.GONE);
                    timePeriod3.setVisibility(View.GONE);
                    mileageQuestion3.setVisibility(View.GONE);
                    mileage3.setVisibility(View.GONE);
                    milesText3.setVisibility(View.GONE);
                }
                else if (adapterView.getItemAtPosition(i) == "3") {
                    checkGone1 = false;
                    checkGone2 = false;
                    checkGone3 = false;

                    maintenanceQuestion.setVisibility(View.VISIBLE);
                    maintenanceAnswer.setVisibility(View.VISIBLE);
                    divider.setVisibility(View.VISIBLE);
                    milesQuestion.setVisibility(View.VISIBLE);
                    milesNum1.setVisibility(View.VISIBLE);
                    miles.setVisibility(View.VISIBLE);
                    timePeriod1.setVisibility(View.VISIBLE);
                    mileageQuestion.setVisibility(View.VISIBLE);
                    mileage1.setVisibility(View.VISIBLE);
                    milesText.setVisibility(View.VISIBLE);

                    divider2.setVisibility(View.VISIBLE);
                    milesQuestion2.setVisibility(View.VISIBLE);
                    milesNum2.setVisibility(View.VISIBLE);
                    miles2.setVisibility(View.VISIBLE);
                    timePeriod2.setVisibility(View.VISIBLE);
                    mileageQuestion2.setVisibility(View.VISIBLE);
                    mileage2.setVisibility(View.VISIBLE);
                    milesText2.setVisibility(View.VISIBLE);

                    divider3.setVisibility(View.VISIBLE);
                    milesQuestion3.setVisibility(View.VISIBLE);
                    milesNum3.setVisibility(View.VISIBLE);
                    miles3.setVisibility(View.VISIBLE);
                    timePeriod3.setVisibility(View.VISIBLE);
                    mileageQuestion3.setVisibility(View.VISIBLE);
                    mileage3.setVisibility(View.VISIBLE);
                    milesText3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        milesNum1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    milesNum1Value = Double.parseDouble(milesNum1.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
        });

        milesNum2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    milesNum2Value = Double.parseDouble(milesNum2.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        milesNum3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    milesNum3Value = Double.parseDouble(milesNum3.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        mileage1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    mileage1Value = Double.parseDouble(mileage1.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        mileage2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    mileage2Value = Double.parseDouble(mileage2.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        mileage3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    mileage3Value = Double.parseDouble(mileage3.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        maintenanceArray = new ArrayList<>();
        maintenanceArray.add("Select");
        maintenanceArray.add("I have");
        maintenanceArray.add("I have not");

        ArrayAdapter<String> maintenanceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, maintenanceArray);
        maintenanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maintenanceAnswer.setAdapter(maintenanceAdapter);

        maintenanceAnswer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i) == "Select"){

                }
                else if (adapterView.getItemAtPosition(i) == "I have") {
                    maintenanceCheck = true;
                    currentUser.setCarMaintenanceCheck(true);
                }
                else if (adapterView.getItemAtPosition(i) == "I have not") {
                    maintenanceCheck = false;
                    currentUser.setCarMaintenanceCheck(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        timePeriod1Array = new ArrayList<>();
        timePeriod1Array.add("Select");
        timePeriod1Array.add("Per Week");
        timePeriod1Array.add("Per Year");

        ArrayAdapter<String> timePeriod1Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timePeriod1Array);
        timePeriod1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timePeriod1.setAdapter(timePeriod1Adapter);

        timePeriod1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i) == "Select"){

                }
                else if (adapterView.getItemAtPosition(i) == "Per Week") {
                    timeCheckYear1 = false;
                }
                else if (adapterView.getItemAtPosition(i) == "Per Year") {
                    timeCheckYear1 = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        timePeriod2Array = new ArrayList<>();
        timePeriod2Array.add("Select");
        timePeriod2Array.add("Per Week");
        timePeriod2Array.add("Per Year");

        ArrayAdapter<String> timePeriod2Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timePeriod2Array);
        timePeriod2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timePeriod2.setAdapter(timePeriod2Adapter);

        timePeriod2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i) == "Select"){

                }
                else if (adapterView.getItemAtPosition(i) == "Per Week") {
                    timeCheckYear2 = false;
                }
                else if (adapterView.getItemAtPosition(i) == "Per Year") {
                    timeCheckYear2 = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        timePeriod3Array = new ArrayList<>();
        timePeriod3Array.add("Select");
        timePeriod3Array.add("Per Week");
        timePeriod3Array.add("Per Year");

        ArrayAdapter<String> timePeriod3Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timePeriod3Array);
        timePeriod3Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timePeriod3.setAdapter(timePeriod3Adapter);

        timePeriod3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i) == "Select"){

                }
                else if (adapterView.getItemAtPosition(i) == "Per Week") {
                    timeCheckYear3 = false;
                }
                else if (adapterView.getItemAtPosition(i) == "Per Year") {
                    timeCheckYear3 = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void transportationCalculator() {

        if (timeCheckYear1)
        {
            if (maintenanceCheck)
            {
                vehicle1Total = (1/(mileage1Value - mileage1Value*0.04)) * milesNum1Value * 19.6;
            }
            else
            {
                vehicle1Total = (1/(mileage1Value)) * milesNum1Value * 19.6;
            }
        }
        else if (!timeCheckYear1)
        {
            if (maintenanceCheck)
            {
                vehicle1Total = (1/(mileage1Value - mileage1Value*0.04)) * milesNum1Value * 19.6 * 52;
            }
            else
            {
                vehicle1Total = (1/(mileage1Value)) * milesNum1Value * 19.6 * 52;
            }
        }
        if (timeCheckYear2)
        {
            if (maintenanceCheck)
            {
                vehicle2Total = (1/(mileage2Value - mileage2Value*0.04)) * milesNum2Value * 19.6;
            }
            else
            {
                vehicle2Total = (1/(mileage2Value)) * milesNum2Value * 19.6;
            }
        }
        else if (!timeCheckYear2)
        {
            if (maintenanceCheck)
            {
                vehicle2Total = (1/(mileage2Value - mileage2Value*0.04)) * milesNum2Value * 19.6 * 52;
            }
            else
            {
                vehicle2Total = (1/(mileage2Value)) * milesNum2Value * 19.6 * 52;
            }
        }
        if (timeCheckYear3)
        {
            if (maintenanceCheck)
            {
                vehicle3Total = (1/(mileage3Value - mileage3Value*0.04)) * milesNum3Value * 19.6;
            }
            else
            {
                vehicle3Total = (1/(mileage3Value)) * milesNum3Value * 19.6;
            }
        }
        else if (!timeCheckYear3)
        {
            if (maintenanceCheck)
            {
                vehicle3Total = (1/(mileage3Value - mileage3Value*0.04)) * milesNum3Value * 19.6 * 52;
            }
            else
            {
                vehicle3Total = (1/(mileage3Value)) * milesNum3Value * 19.6 * 52;
            }
        }

    }

    public void TransportationtoWaste(View view) {
        transportationCalculator();

        if (!checkGone1 && !checkGone2 && !checkGone3) {
            transportationTotal = (vehicle1Total + vehicle2Total + vehicle3Total) * 0.0005;
        }
        else if (!checkGone1 && !checkGone2) {
            transportationTotal = (vehicle1Total + vehicle2Total) * 0.0005;

        }
        else if (!checkGone1) {
            transportationTotal = (vehicle1Total) * 0.0005;
        }
        else {
            transportationTotal = 0;
        }
//        Toast.makeText(Transportation.this, transportationTotal + "", Toast.LENGTH_LONG).show();
        currentUser.setTransportationTotal(transportationTotal);
        currentUser.setCarMilesValue(String.format("%.2f", transportationTotal));
        Intent intent = new Intent(this, Waste.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }

    public void TransportationToHomeEnergy(View view) {
        transportationCalculator();

        if (!checkGone1 && !checkGone2 && !checkGone3) {
            transportationTotal = (vehicle1Total + vehicle2Total + vehicle3Total) * 0.0005;
        }
        else if (!checkGone1 && !checkGone2) {
            transportationTotal = (vehicle1Total + vehicle2Total) * 0.0005;

        }
        else if (!checkGone1) {
            transportationTotal = (vehicle1Total) * 0.0005;
        }
        else {
            transportationTotal = 0;
        }
//        Toast.makeText(Transportation.this, transportationTotal + "", Toast.LENGTH_LONG).show();
        currentUser.setTransportationTotal(transportationTotal);
        currentUser.setCarMilesValue(String.format("%.2f", transportationTotal));
        Intent intent = new Intent(this, HomeEnergy.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }

    public void TransportationToResults(View view) {
        transportationCalculator();

        if (!checkGone1 && !checkGone2 && !checkGone3) {
            transportationTotal = (vehicle1Total + vehicle2Total + vehicle3Total) * 0.0005;
        }
        else if (!checkGone1 && !checkGone2) {
            transportationTotal = (vehicle1Total + vehicle2Total) * 0.0005;

        }
        else if (!checkGone1) {
            transportationTotal = (vehicle1Total) * 0.0005;
        }
        else {
            transportationTotal = 0;
        }
//        Toast.makeText(Transportation.this, transportationTotal + "", Toast.LENGTH_LONG).show();
        currentUser.setTransportationTotal(transportationTotal);
        currentUser.setCarMilesValue(String.format("%.2f", transportationTotal));
        Intent intent = new Intent(this, ResultsTabbedActivity.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }


//        reduceMile1 = (EditText) findViewById(R.id.milesReduce);
//        reduceMileage1 = (EditText) findViewById(R.id.reduceMileage);
//        reduceMile2 = (EditText) findViewById(R.id.milesReduce2);
//        reduceMileage2 = (EditText) findViewById(R.id.reduceMileage2);
//        reduceMile3 = (EditText) findViewById(R.id.milesReduce3);
//        reduceMileage3 = (EditText) findViewById(R.id.reduceMileage3);


//        submit = (Button) findViewById(R.id.submit);
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String tempMile1 = milesNum1.getText().toString();
//                String tempMileage1 = mileage1.getText().toString();
//                String tempMile2 = milesNum2.getText().toString();
//                String tempMileage2 = mileage2.getText().toString();
//                String tempMile3 = milesNum3.getText().toString();
//                String tempMileage3 = mileage3.getText().toString();

//                String tempReduceMile1 = reduceMile1.getText().toString();
//                String tempReduceMileage1 = reduceMileage1.getText().toString();
//                String tempReduceMile2 = reduceMile2.getText().toString();
//                String tempReduceMileage2 = reduceMileage2.getText().toString();
//                String tempReduceMile3 = reduceMile3.getText().toString();
//                String tempReduceMileage3 = reduceMileage3.getText().toString();
//
//                int value1, value2, value3, value4, value5, value6;
//                int reduceValue1, reduceValue2, reduceValue3, reduceValue4, reduceValue5, reduceValue6;
//
//                if (maintenanceAnswer.toString().equals("Have not done")) {
//                    if ((tempMile1.length() > 0) && (tempMileage1.length() > 0) && timePeriod1.toString().equals("Per Week")) {
//                        value1 = Integer.parseInt(tempMile1);
//                        value2 = Integer.parseInt(tempMileage1);
//                        emission1 = (int) ((value1 * 52) / value2 * 19.6);
//                        //emission1.setText(Integer.toString(emissionYes1));
////                    showToast(String.valueOf(emissionYes1));
//                    }
//                    else if ((tempMile1.length() > 0) && (tempMileage1.length() > 0) && timePeriod1.toString().equals("Per Year")) {
//                        value1 = Integer.parseInt(tempMile1);
//                        value2 = Integer.parseInt(tempMileage1);
//                        emission1 = (int) (value1 / value2 * 19.6);
//                    }
//
//                    if ((tempMile2.length() > 0) && (tempMileage2.length() > 0) && timePeriod2.toString().equals("Per Week")) {
//                        value3 = Integer.parseInt(tempMile2);
//                        value4 = Integer.parseInt(tempMileage2);
//                        emission2 = (int) ((value3 * 52) / value4 * 19.6);
//                    }
//                    else if ((tempMile2.length() > 0) && (tempMileage2.length() > 0) && timePeriod2.toString().equals("Per Year")) {
//                        value3 = Integer.parseInt(tempMile2);
//                        value4 = Integer.parseInt(tempMileage2);
//                        emission2 = (int) (value3 / value4 * 19.6);
//                    }
//
//                    if((tempMile3.length() > 0) && (tempMileage3.length() > 0) && timePeriod3.toString().equals("Per Week")) {
//                        value5 = Integer.parseInt(tempMile3);
//                        value6 = Integer.parseInt(tempMileage3);
//                        emission3 = (int) ((value5 * 52) / value6 * 19.6);
//                    }
//                    else if ((tempMile3.length() > 0) && (tempMileage3.length() > 0) && timePeriod3.toString().equals("Per Year")) {
//                        value5 = Integer.parseInt(tempMile3);
//                        value6 = Integer.parseInt(tempMileage3);
//                        emission3 = (int) (value5 / value6 * 19.6);
//                    }
//
//                    total = (int) ((emission1 + emission2 + emission3) * 1.04);
//                }
//
//                else if(maintenanceAnswer.toString().equals("Already done")){
//                    if ((tempMile1.length() > 0) && (tempMileage1.length() > 0) && timePeriod1.toString().equals("Per Week")) {
//                        value1 = Integer.parseInt(tempMile1);
//                        value2 = Integer.parseInt(tempMileage1);
//                        emission1 = (int) ((value1 * 52) / value2 * 19.6);
//                        //emission1.setText(Integer.toString(emissionYes1));
////                    showToast(String.valueOf(emissionYes1));
//                    }
//                    else if ((tempMile1.length() > 0) && (tempMileage1.length() > 0) && timePeriod1.toString().equals("Per Year")) {
//                        value1 = Integer.parseInt(tempMile1);
//                        value2 = Integer.parseInt(tempMileage1);
//                        emission1 = (int) (value1 / value2 * 19.6);
//                    }
//
//                    if ((tempMile2.length() > 0) && (tempMileage2.length() > 0) && timePeriod2.toString().equals("Per Week")) {
//                        value3 = Integer.parseInt(tempMile1);
//                        value4 = Integer.parseInt(tempMileage1);
//                        emission2 = (int) ((value3 * 52) / value4 * 19.6);
//                    }
//                    else if ((tempMile2.length() > 0) && (tempMileage2.length() > 0) && timePeriod2.toString().equals("Per Year")) {
//                        value3 = Integer.parseInt(tempMile1);
//                        value4 = Integer.parseInt(tempMileage1);
//                        emission2 = (int) (value3 / value4 * 19.6);
//                    }
//
//                    if((tempMile3.length() > 0) && (tempMileage3.length() > 0) && timePeriod3.toString().equals("Per Week")) {
//                        value5 = Integer.parseInt(tempMile1);
//                        value6 = Integer.parseInt(tempMileage1);
//                        emission3 = (int) ((value5 * 52) / value6 * 19.6);
//                    }
//                    else if ((tempMile3.length() > 0) && (tempMileage3.length() > 0) && timePeriod3.toString().equals("Per Year")) {
//                        value5 = Integer.parseInt(tempMile1);
//                        value6 = Integer.parseInt(tempMileage1);
//                        emission3 = (int) (value5 / value6 * 19.6);
//                    }
//                    total = (int) ((emission1 + emission2 + emission3) * .96);
//                }
//
//                if(maintenanceRedo.toString().equals("Will do") && maintenanceAnswer.toString().equals("Have not done")) {
//                    if(emission1 > 0){
//                        emission1 = (int) (emission1 * .96);
//                    }
//                    if(emission2 > 0){
//                        emission2 = (int) (emission2 * .96);
//                    }
//                    if(emission3 > 0){
//                        emission3 = (int) (emission3 * .96);
//                    }
//                }

//                if((emission1 > 0) && (tempReduceMile1.length() > 0) && (tempReduceMileage1.length() > 0) && reduceTimePeriod1.toString().equals("Per Week")){
//                    reduceValue1 = Integer.parseInt(tempReduceMile1);
//                    reduceValue2 = Integer.parseInt(tempReduceMileage1);
//                    emission1 = (int) (emission1 - ((reduceValue1 * 52) / reduceValue2 * 19.6));
//                }
//                else if((emission1 > 0) && (tempReduceMile1.length() > 0) && (tempReduceMileage1.length() > 0) && reduceTimePeriod1.toString().equals("Per Year")){
//                    reduceValue1 = Integer.parseInt(tempReduceMile1);
//                    reduceValue2 = Integer.parseInt(tempReduceMileage1);
//                    emission1 = (int) (emission1 - ((reduceValue1 / reduceValue2) * 19.6));
//                }
//
//                if((emission2 > 0) && (tempReduceMile2.length() > 0) && (tempReduceMileage2.length() > 0) && reduceTimePeriod2.toString().equals("Per Week")){
//                    reduceValue3 = Integer.parseInt(tempReduceMile2);
//                    reduceValue4 = Integer.parseInt(tempReduceMileage2);
//                    emission2 = (int) (emission2 - ((reduceValue3 * 52) / reduceValue4 * 19.6));
//                }
//                else if((emission2 > 0) && (tempReduceMile2.length() > 0) && (tempReduceMileage2.length() > 0) && reduceTimePeriod2.toString().equals("Per Year")){
//                    reduceValue3 = Integer.parseInt(tempReduceMile2);
//                    reduceValue4 = Integer.parseInt(tempReduceMileage2);
//                    emission2 = (int) (emission2 - ((reduceValue3 / reduceValue4) * 19.6));
//                }
//
//                if((emission3 > 0) && (tempReduceMile3.length() > 0) && (tempReduceMileage3.length() > 0) && reduceTimePeriod3.toString().equals("Per Week")){
//                    reduceValue5 = Integer.parseInt(tempReduceMile2);
//                    reduceValue6 = Integer.parseInt(tempReduceMileage3);
//                    emission3 = (int) (emission3 - ((reduceValue5 * 52) / reduceValue6 * 19.6));
//                }
//                else if((emission3 > 0) && (tempReduceMile3.length() > 0) && (tempReduceMileage3.length() > 0) && reduceTimePeriod3.toString().equals("Per Year")){
//                    reduceValue5 = Integer.parseInt(tempReduceMile3);
//                    reduceValue6 = Integer.parseInt(tempReduceMileage3);
//                    emission3 = (int) (emission3 - ((reduceValue5 / reduceValue6) * 19.6));
//                }

//                total = (int) (emission1 + emission2 + emission3);
//                Toast.makeText(Transportation.this, total + "", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(Transportation.this, ResultsTabbedActivity.class);
//                currentUser.setTransportationTotal(total);
//                intent.putExtra(CURRENT_USER_KEY, currentUser);
//                startActivity(intent);
//
//            }
//        });



        //emissionCalculator();
        //emissionYes1 = (value * 52) / (22 * 19 * 1);
        //emission1.setText(Integer.toString(emissionYes1));
        //emissionNo1 = (Double.parseDouble(milesNum1.getText().toString()) * 52) / ((Double.parseDouble(mileage1.getText().toString()) * 19.6 * 1.01) *.96);

        //next = findViewById(R.id.next);
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String temp1 = milesNum1.getText().toString();
//                String temp2 = mileage1.getText().toString();
//                int value1 = 0;
//                int value2 = 0;
//                if ((temp1.length() > 0) && (temp2.length() > 0))
//                {
//                    value1 = Integer.parseInt(temp1);
//                    value2 = Integer.parseInt(temp2);
//                    emissionYes1 = (value1 * 52) / (value2 * 19);
//                    Toast.makeText(Transportation.this, emissionYes1 + " ", Toast.LENGTH_LONG);
////                    emission1.setText(Integer.toString(emissionYes1));
//                }
//            }
//        });


//        previous = (Button) findViewById(R.id.previous);
//        previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Transportation.this, HomeEnergy.class);
//                currentUser.setTransportationTotal(total);
//                intent.putExtra(CURRENT_USER_KEY, currentUser);
//                startActivity(intent);
//            }
//        });

//        next = (Button) findViewById(R.id.next);
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Transportation.this, Waste.class);
//                currentUser.setTransportationTotal(total);
//                intent.putExtra(CURRENT_USER_KEY, currentUser);
//                startActivity(intent);
//            }
//        });


   /* @SuppressLint("SetTextI18n")
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
//                String temp1 = milesNum1.getText().toString();
//                String temp2 = mileage1.getText().toString();
//                int value1 = 0;
//                int value2 = 0;
//                if ((temp1.length() > 0) && (temp2.length() > 0))
//                {
//                    value1 = Integer.parseInt(temp1);
//                    value2 = Integer.parseInt(temp2);
//                    emissionYes1 = (value1 * 52) / (value2 * 19);
//                    emission1.setText(Integer.toString(emissionYes1));
//                }
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
    }*/
    /*
   private void reduceTimePeriod() {
        String[] time = getResources().getStringArray(R.array.time);
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, time);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reduceTimePeriod.setAdapter(timeAdapter);
        reduceTimePeriod.setOnItemSelectedListener(this);
    }
    */
//   private void timePeriod() {
//        String[] time = getResources().getStringArray(R.array.time);
//        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, time);
//        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        timePeriod1.setAdapter(timeAdapter);
//        timePeriod1.setOnItemSelectedListener(this);
//
//        String[] time2 = getResources().getStringArray(R.array.time);
//        ArrayAdapter<String> timeAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, time2);
//        timeAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        timePeriod2.setAdapter(timeAdapter2);
//        timePeriod2.setOnItemSelectedListener(this);
//
//        String[] time3 = getResources().getStringArray(R.array.time);
//        ArrayAdapter<String> timeAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, time3);
//        timeAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        timePeriod3.setAdapter(timeAdapter3);
//        timePeriod3.setOnItemSelectedListener(this);

//       String[] time4 = getResources().getStringArray(R.array.time);
//       ArrayAdapter<String> timeAdapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, time4);
//       timeAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//       reduceTimePeriod1.setAdapter(timeAdapter4);
//       reduceTimePeriod1.setOnItemSelectedListener(this);
//
//       String[] time5 = getResources().getStringArray(R.array.time);
//       ArrayAdapter<String> timeAdapter5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, time5);
//       timeAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//       reduceTimePeriod2.setAdapter(timeAdapter5);
//       reduceTimePeriod2.setOnItemSelectedListener(this);
//
//       String[] time6 = getResources().getStringArray(R.array.time);
//       ArrayAdapter<String> timeAdapter6 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, time6);
//       timeAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//       reduceTimePeriod3.setAdapter(timeAdapter6);
//       reduceTimePeriod3.setOnItemSelectedListener(this);

//   private void maintenanceRedo() {
//        String[] answer = getResources().getStringArray(R.array.answer);
//        ArrayAdapter<String> answerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, answer);
//        answerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        maintenanceRedo.setAdapter(answerAdapter);
//        maintenanceRedo.setOnItemSelectedListener(this);
//    }
//   private void numberOfVehicles() {
//        String[] number = getResources().getStringArray(R.array.numVehicles);
//        ArrayAdapter<String> vehicleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, number);
//        vehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        numVehicles.setAdapter(vehicleAdapter);
//        numVehicles.setOnItemSelectedListener(this);
//
//    }
//
//   private void maintenanceAnswer() {
//        String[] maintenance = getResources().getStringArray(R.array.maintenanceAnswer);
//        ArrayAdapter<String> maintenanceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, maintenance);
//        maintenanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        maintenanceAnswer.setAdapter(maintenanceAdapter);
//        maintenanceAnswer.setOnItemSelectedListener(this);
//    }
//
//
//
//    @Override
//     public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//     }
//
//     @Override
//     public void onNothingSelected(AdapterView<?> adapterView) {
//
//     }
/*
     public void resultsPage(View view) {
         currentUser.setDemoTotal("15.0");
         Intent intent = new Intent(this, ResultsActivity.class);
         currentUser.setDemoTotal("15.0");
         intent.putExtra(CURRENT_USER_KEY, currentUser);
         startActivity(intent);
     }
*/
/*
    public void homeEnergyPage(View view) {

        Intent intent = new Intent(this, HomeEnergy.class);
        currentUser.setTransportationTotal(total);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }
    public void wastePage(View view) {

        Intent intent = new Intent(this, Waste.class);
        currentUser.setTransportationTotal(total);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }

*/
    private void showToast(String text) {
        Toast.makeText(Transportation.this, text, Toast.LENGTH_LONG).show();
    }

}
