package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;

public class InitiateCalculator extends AppCompatActivity implements Serializable {
    EditText householdNumberInput;
    int numberOfPeople;
    Button homeEnergy;
    Button transportation;
    Button waste;
    userInfo currentUser;
    String url;
    String countryNameWB;
    String dateWB;
    String avgValueWB;
    String locationAvgTop;
    String locationAvgBottom;
    Button getResults;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";

    public static final String HOUSEHOLDNUMBER = "com.example.homeenergy.HOUSEHOLDNUMBER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiate_calculator);
        householdNumberInput = (EditText) findViewById(R.id.houseHoldNumberInput);



        getResults = findViewById(R.id.getResults);


        currentUser = (userInfo) getIntent().getSerializableExtra(DemoHomeActivity.CURRENT_USER_KEY);

        WBdata();

        homeEnergy = (Button) findViewById(R.id.homeEnergyBttn);
        transportation = (Button) findViewById(R.id.transportationBttn);
        waste = (Button) findViewById(R.id.wasteBttn);

        homeEnergy.setEnabled(false);
        transportation.setEnabled(false);
        waste.setEnabled(false);

        householdNumberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0)
                {
                    homeEnergy.setEnabled(false);
                    transportation.setEnabled(false);
                    waste.setEnabled(false);
                }
                else
                {
                    homeEnergy.setEnabled(true);
                    transportation.setEnabled(true);
                    waste.setEnabled(true);
                }
            }
        });


        homeEnergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOfPeople = Integer.valueOf(householdNumberInput.getText().toString());
                Intent intent = new Intent(InitiateCalculator.this, HomeEnergy.class);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                intent.putExtra(HOUSEHOLDNUMBER, numberOfPeople);
                startActivity(intent);
            }
        });
        transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InitiateCalculator.this, Transportation.class);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });
        waste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InitiateCalculator.this, Waste.class);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });
    }
    public void WBdata() {

        RequestQueue queue = Volley.newRequestQueue(InitiateCalculator.this);

        url = "https://api.worldbank.org/v2/country/" + currentUser.getCountryCode() + "/indicator/EN.ATM.CO2E.PC?mrv=1&format=json";


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray test1 = (JSONArray) response.get(1);
                    countryNameWB = test1.getJSONObject(0).getJSONObject("country").getString("value");
                    dateWB = test1.getJSONObject(0).getString("date");
                    avgValueWB = test1.getJSONObject(0).getString("value");
                    avgValueWB = String.format("%.2f", Float.valueOf(avgValueWB));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                locationAvgTop = "Average Carbon footprint (" + countryNameWB + ")";
                locationAvgBottom = "Average CO2 emissions (metric tons per capita)" + " is " + avgValueWB + " (" + countryNameWB + ", " + dateWB + ", source: World Bank)";
                currentUser.setAvgValueWB(avgValueWB);
                currentUser.setLocationAvgTop(locationAvgTop);
                currentUser.setLocationAvgBottom(locationAvgBottom);
//                button6.setEnabled(true);
//                button6.setText("Get Results");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);
    }

}