package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoCalculatorActivity extends AppCompatActivity implements Serializable {
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    userInfo currentUser;
    EditText editText;
    String countryNameWB;
    String dateWB;
    String avgValueWB;
    String url;
    String locationAvgTop;
    String locationAvgBottom;
    Button button6;
    Button button4;
    String format;
    EditText editTextName, editTextNumberDecimal, editTextNumberDecimal2, editTextNumberDecimal3, editTextNumber, editTextNumberDecimal4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_demo_calculator);
        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);


        button4 = findViewById(R.id.button4);

        button6 = findViewById(R.id.button6);
//        button6.setEnabled(false);
//        button6.setText("loading...");

        editTextName = findViewById(R.id.editTextName);
        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
        editTextNumberDecimal2 = findViewById(R.id.editTextNumberDecimal2);
        editTextNumberDecimal3 = findViewById(R.id.editTextNumberDecimal3);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumberDecimal4 = findViewById(R.id.editTextNumberDecimal4);

//        editTextName.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                currentUser.setName(editTextName.getText().toString());
//            }
//        });



        SimpleDateFormat s = new SimpleDateFormat("hh:mm aa\nMMM dd, yyyy");
        format = s.format(new Date());


//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(DemoCalculatorActivity.this, format, Toast.LENGTH_LONG).show();
//            }
//        });

    }

    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    public void resultsPage(View view) {
        currentUser.setName(editTextName.getText().toString());
        currentUser.setHouseholdNumber(Integer.parseInt(editTextNumber.getText().toString()));
        currentUser.setHomeEnergyTotal(Double.parseDouble(editTextNumberDecimal.getText().toString()));
        currentUser.setTransportationTotal(Integer.parseInt(editTextNumberDecimal2.getText().toString()));
        currentUser.setWasteTotal(Double.parseDouble(editTextNumberDecimal3.getText().toString()));
        currentUser.setDemoTotal(editTextNumberDecimal4.getText().toString());
        currentUser.setTimestamp(format);
        Intent intent = new Intent(this, PastResultsTabbedActivity.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
//        intent.putExtra(CURRENT_USER_KEY, currentUserDB);
        startActivity(intent);
    }

    public void barChartPage(View view) {

    }

    public void pieChartPage(View view) {

    }

    public void lineChartPage(View view) {

    }

    public void resultsTabbedPage(View view) {
        Intent intent = new Intent(this, PastResultsTabbedActivity.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }

    public void WBdata() {

        RequestQueue queue = Volley.newRequestQueue(DemoCalculatorActivity.this);

        url ="https://api.worldbank.org/v2/country/" + currentUser.getCountryCode() + "/indicator/EN.ATM.CO2E.PC?mrv=1&format=json";


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
                locationAvgBottom = "Average CO2 emissions (metric tons per capita)" +" is " +avgValueWB + " (" + countryNameWB + ", " + dateWB + ", source: World Bank)";
                currentUser.setAvgValueWB(avgValueWB);
                currentUser.setLocationAvgTop(locationAvgTop);
                currentUser.setLocationAvgBottom(locationAvgBottom);
                button6.setEnabled(true);
                button6.setText("Get Results");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);
    }


}