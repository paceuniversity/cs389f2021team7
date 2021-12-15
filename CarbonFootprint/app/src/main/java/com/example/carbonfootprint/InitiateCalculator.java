package com.example.carbonfootprint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;

public class InitiateCalculator extends AppCompatActivity implements Serializable {
    TextInputEditText householdNumberInput;
    TextInputEditText nameInput;
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
    ImageView calculatorInfo, check1, check2, check3;
    TextView calculatorError;
    int householdNumber;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";

    public static final String HOUSEHOLDNUMBER = "com.example.homeenergy.HOUSEHOLDNUMBER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_initiate_calculator);
        householdNumberInput = findViewById(R.id.householdEditText);
        nameInput = findViewById(R.id.nameInputEditText);

        getResults = findViewById(R.id.exitButton);



        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);

        homeEnergy = (Button) findViewById(R.id.getSuggestionsbutton);
        transportation = (Button) findViewById(R.id.transportationSuggestionsBttn);
        waste = (Button) findViewById(R.id.wasteSuggestionsBttn);
        calculatorInfo = findViewById(R.id.calculatorInfo);
        calculatorError = findViewById(R.id.calculatorError);
        calculatorError.setVisibility(View.GONE);



        if (currentUser.getUnitsLocationCheck()) {
            if (currentUser.countryCode.equals("US")) {
                currentUser.setImperialSystem(true);
                currentUser.setMetricSystem(false);
            }
            else {
                currentUser.setImperialSystem(false);
                currentUser.setMetricSystem(true);
            }
        }


//        Toast.makeText(this, "metric: " + currentUser.isMetricSystem() + " imperial: " + currentUser.isImperialSystem() + " check: " + currentUser.getUnitsLocationCheck(), Toast.LENGTH_LONG).show();

//        currentUser = (userInfo) getIntent().getSerializableExtra(DemoHomeActivity.CURRENT_USER_KEY);

        WBdata();

        if (currentUser.getName() != null) {
            nameInput.setText(currentUser.getName());

        }

        if (currentUser.getHouseholdNumber() != 0) {
            householdNumberInput.setText(currentUser.getHouseholdNumber() + "");
        }


        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);
        check3 = findViewById(R.id.check3);

        if (currentUser.isHeqcomplete()) {
            check1.setImageResource(R.drawable.ic_baseline_check_24);
        }
        else {
            check1.setImageResource(R.drawable.ic_baseline_check_transparent_24);
        }
        if (currentUser.isTqcomplete()) {
            check2.setImageResource(R.drawable.ic_baseline_check_24);
        }
        else {
            check2.setImageResource(R.drawable.ic_baseline_check_transparent_24);
        }
        if (currentUser.isWqcomplete()) {
            check3.setImageResource(R.drawable.ic_baseline_check_24);
        }
        else {
            check3.setImageResource(R.drawable.ic_baseline_check_transparent_24);
        }


        calculatorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalculatorDialog();
            }
        });




        nameInput.addTextChangedListener(new TextWatcher() {
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

                }
                else
                {
                    currentUser.setName(nameInput.getText().toString());
                }
            }
        });

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

                }
                else
                {
                    currentUser.setHouseholdNumber(Integer.parseInt(householdNumberInput.getText().toString()));
                }
            }
        });


        homeEnergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InitiateCalculator.this, HomeEnergy.class);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
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
//        getResults.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(InitiateCalculator.this, ResultsActivity.class);
//                intent.putExtra(CURRENT_USER_KEY, currentUser);
//                startActivity(intent);
//            }
//        });
    }

    public void calculatortoResults(View view) {
        if(currentUser.getName() != null && currentUser.isHeqcomplete() && currentUser.isTqcomplete() && currentUser.isWqcomplete()) {
            Intent intent = new Intent(InitiateCalculator.this, ResultsTabbedActivity.class);
            intent.putExtra(CURRENT_USER_KEY, currentUser);
            startActivity(intent);
        }
        else {
            calculatorError.setVisibility(View.VISIBLE);
        }
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
                locationAvgBottom = "Average CO2 emissions (metric tons per capita)" + "\n(" + countryNameWB + ", " + dateWB + ", source: World Bank)";
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }

    public void openCalculatorDialog() {
        CalculatorDialogue calculatorDialogue = new CalculatorDialogue();
        calculatorDialogue.show(getSupportFragmentManager(), "Calculator Dialogue");
    }
}