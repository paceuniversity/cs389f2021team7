package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import java.util.ArrayList;

public class PastResultsDetailsActivity extends AppCompatActivity implements Serializable {
    TextView nameTextView, timeTextView, locationTextView, householdTextView, homeEnergyTextView, transportationTextView, wasteTextView, totalTextView;
    userInfo currentUser;
    ArrayList<String> xmlcountryname;
    ArrayList<String> xmlcountrycode;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    Button retrieveResultsButton;
    String url;
    String countryNameWB;
    String dateWB;
    String avgValueWB;
    String locationAvgTop;
    String locationAvgBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_results_details);

        nameTextView = findViewById(R.id.nameTextView);
        timeTextView = findViewById(R.id.timeTextView);
        locationTextView = findViewById(R.id.locationTextView);
        householdTextView = findViewById(R.id.householdTextView);
        homeEnergyTextView = findViewById(R.id.homeEnergyTextView);
        transportationTextView = findViewById(R.id.transportationTextView);
        wasteTextView = findViewById(R.id.wasteTextView);
        totalTextView = findViewById(R.id.totalTextView);
        retrieveResultsButton = findViewById(R.id.retrieveResultsButton);

        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);

        xmlcountryname = currentUser.getXmlcountryname();
        xmlcountrycode = currentUser.getXmlcountrycode();
        WBdata();



        currentUser.setPastResultsLocationName(xmlcountryname.get(xmlcountrycode.indexOf(currentUser.getPastResultsLocation())));

//        Toast.makeText(this,"Location Average: " + currentUser.getPastResultsLocationAvg(), Toast.LENGTH_LONG).show();

        nameTextView.setText(currentUser.getPastResultsName());
        timeTextView.setText(currentUser.getPastResultsTime());
        locationTextView.setText(currentUser.getPastResultsLocationName());
        householdTextView.setText(currentUser.getPastResultsHousehold());
        homeEnergyTextView.setText(currentUser.getPastResultsHomeEnergy());
        transportationTextView.setText(currentUser.getPastResultsTransportation());
        wasteTextView.setText(currentUser.getPastResultsWaste());
        totalTextView.setText(String.format("%.2f", Double.parseDouble(currentUser.getPastResultsTotal())));

    }

    public void retrieveResults(View view) {
        currentUser.setRetrieveCheck(true);
        currentUser.setHomeEnergyTotal(Double.parseDouble(currentUser.getPastResultsHomeEnergy()) * Integer.parseInt(currentUser.getPastResultsHousehold()));
        currentUser.setTransportationTotal(Double.parseDouble(currentUser.getPastResultsTransportation()) * Integer.parseInt(currentUser.getPastResultsHousehold()));
        currentUser.setWasteTotal(Double.parseDouble(currentUser.getPastResultsWaste()));
        currentUser.setHouseholdNumber(Integer.parseInt(currentUser.getPastResultsHousehold()));
        Intent intent = new Intent(PastResultsDetailsActivity.this, ResultsTabbedActivity.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);

    }

    public void WBdata() {

        retrieveResultsButton.setEnabled(false);
//        retrieveResultsButton.setText("Loading...");

        RequestQueue queue = Volley.newRequestQueue(PastResultsDetailsActivity.this);

        url = "https://api.worldbank.org/v2/country/" + currentUser.getPastResultsLocation() + "/indicator/EN.ATM.CO2E.PC?mrv=1&format=json";


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

        retrieveResultsButton.setEnabled(true);
//        retrieveResultsButton.setText("Retrieve Results");
    }

    @Override
    public void onStart() {
        super.onStart();
        if (ResultsTabbedActivity.currentUserTemporary3 != null) {
            currentUser = ResultsTabbedActivity.currentUserTemporary3;
        }

    }

}
