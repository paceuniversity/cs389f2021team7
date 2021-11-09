package com.example.carbonfootprint;

import android.content.Intent;
import android.location.Address;
import android.location.LocationRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, Serializable {
    private static final int PERMISSIONS_FINE_LOCATION = 88;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    Button button;
    TextView textView;
    TextView textView2;
    String countryCode;
    List<Address> addresses;
    String url;
    ImageView footprint;
    ImageView footprint2;
    String countryNameWB;
    String dateWB;
    String avgValueWB;
    String test;
    userInfo currentUser;
    float demoTotalNumber;
    TextView textView5;
    float footprintScale;
    float footprintScale2;
    float footprintScale3;
    int footprintScaleDivisor;
    TextView textView3;
    private DocumentReference documentReference = FirebaseFirestore.getInstance().document("CarbonFootprint/carbon_footprint_document");
    Map<String, Object> dataToSave;
    Object avgArray2;
    ArrayList<Double> avgArray1;
    ArrayList<String> resultsSpinnerArray;
    Button button3;
    ArrayList<Double> testArray3;
    double appTotal;
    double appAverageValue;
    double doubleResult;
    double appAvgValue;
    boolean avgSpinnerCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button3 = findViewById(R.id.button3);


        textView2 = findViewById(R.id.textView2);
        textView5 = findViewById(R.id.textView5);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        currentUser = (userInfo) getIntent().getSerializableExtra(DemoCalculatorActivity.CURRENT_USER_KEY);

        footprintScaleDivisor = 21;


        footprint2 = findViewById(R.id.footprint2);
        footprint2.setVisibility(View.INVISIBLE);

        footprint = findViewById(R.id.footprint);
        footprint.setVisibility(View.INVISIBLE);



        demoTotalNumber = Float.parseFloat(currentUser.getDemoTotal());
        if (demoTotalNumber > 21) {
            footprint2.setVisibility(View.VISIBLE);
            textView.setTextSize(10);
            textView.setText("Your household average CO2 emissions (metric tons per capita) is " + String.format("%.2f", demoTotalNumber) + "\nNote: The footprint is at maximum size and cannot be enlarged any further.");
            footprint2.setScaleX(1);
            footprint2.setScaleY(1);
        }
        else {
            footprint2.setVisibility(View.VISIBLE);
            textView.setText("Your household average CO2 emissions (metric tons per capita) is " + String.format("%.2f", demoTotalNumber));
            footprintScale2 = (demoTotalNumber/footprintScaleDivisor);
            footprint2.setScaleX(footprintScale2);
            footprint2.setScaleY(footprintScale2);

        }


        resultsSpinnerArray = new ArrayList<String>();
        resultsSpinnerArray.add("Location Average");
        resultsSpinnerArray.add("App Average");

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, resultsSpinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        }



    public void ResultstoDemoCalculator(View view) {
        Intent intent = new Intent(this, DemoCalculatorActivity.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }

    public void FirebaseAppAverage(View view) {
//        button3.setEnabled(false);
        dataToSave = new HashMap<String, Object>();
        avgArray1 = new ArrayList<Double>();
        avgArray2 = new ArrayList<Double>();
        avgArray2 = documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    avgArray2 = documentSnapshot.get("App Average Array");
                    avgArray1 = (ArrayList<Double>) avgArray2;
                    doubleResult = Double.valueOf(currentUser.getDemoTotal());
                    avgArray1.add(doubleResult);
                    dataToSave.put("App Average Array", avgArray1);
                    documentReference.set(dataToSave);

                    if (avgSpinnerCheck ==  true) {
                        appAvgValue = (appAverage(avgArray1));
                        textView2.setText("The app average emissions (metric tons per capita) is " + String.format("%.2f", appAvgValue));
                        footprintScale3 = (float) (appAvgValue/footprintScaleDivisor);
                        footprint.setScaleX(footprintScale3);
                        footprint.setScaleY(footprintScale3);
                    }

                }
            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String test2 = adapterView.getItemAtPosition(i).toString();


        if (test2 == resultsSpinnerArray.get(1)) {
            avgSpinnerCheck = true;
            avgArray2 = new ArrayList<Double>();
            avgArray2 = documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {


                        avgArray2 = documentSnapshot.get("App Average Array");
                        avgArray1 = (ArrayList<Double>) avgArray2;
//                        textView2.setText(testArray5.toString());
//                        textView2.setText(String.valueOf(appAverage(testArray5)));

                        footprint.setVisibility(View.VISIBLE);
                        textView5.setText("App Average");
                        appAvgValue = (appAverage(avgArray1));
                        textView2.setText("The app average emissions (metric tons per capita) is " + String.format("%.2f", appAvgValue));
                        footprintScale3 = (float) (appAvgValue/footprintScaleDivisor);
                        footprint.setScaleX(footprintScale3);
                        footprint.setScaleY(footprintScale3);
                    }
                }
            });
        }
        else if (test2 == resultsSpinnerArray.get(0)) {
            avgSpinnerCheck = false;
            footprint.setVisibility(View.VISIBLE);
            if (Float.parseFloat(currentUser.getAvgValueWB()) > 21) {
                textView5.setText(currentUser.getLocationAvgTop());
                textView2.setTextSize(10);
                textView2.setText(currentUser.getLocationAvgBottom() + "\nNote: The footprint is at maximum size and cannot be enlarged any further.");
                footprint.setScaleX(1);
                footprint.setScaleY(1);
            }
            else {
                textView5.setText(currentUser.getLocationAvgTop());
                textView2.setText(currentUser.getLocationAvgBottom());
                footprintScale = (Float.parseFloat(currentUser.getAvgValueWB()) / footprintScaleDivisor);
                footprint.setScaleX(footprintScale);
                footprint.setScaleY(footprintScale);
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public double appAverage(ArrayList<Double> arrayList) {
        appTotal = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            appTotal = appTotal + arrayList.get(i);
        }
        appAverageValue = appTotal/arrayList.size();

        return appAverageValue;
    }


//    public void WBdata() {
//
//        RequestQueue queue = Volley.newRequestQueue(ResultsActivity.this);
//
//        url ="https://api.worldbank.org/v2/country/" + currentUser.getCountryCode() + "/indicator/EN.ATM.CO2E.PC?mrv=1&format=json";
//
//
//        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    JSONArray test1 = (JSONArray) response.get(1);
//                    countryNameWB = test1.getJSONObject(0).getJSONObject("country").getString("value");
//                    dateWB = test1.getJSONObject(0).getString("date");
//                    avgValueWB = test1.getJSONObject(0).getString("value");
//                    avgValueWB = String.format("%.2f", Float.valueOf(avgValueWB));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                textView5.setText("Average Carbon footprint (" + countryNameWB + ")");
//                textView2.setText("Average CO2 emissions (metric tons per capita)" +" is " +avgValueWB + " (" + countryNameWB + ", " + dateWB + ", source: World Bank)");
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                textView.setText("Error");
//
//            }
//        });
//
//        queue.add(request);
//    }

//            new android.os.Handler().postDelayed(
//                new Runnable() {
//        public void run() {
//
//        }
//    },
//            5000);


}