package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_calculator);
        currentUser = (userInfo) getIntent().getSerializableExtra(ResultsActivity.CURRENT_USER_KEY);
        currentUser = (userInfo) getIntent().getSerializableExtra(DemoHomeActivity.CURRENT_USER_KEY);
        button6 = findViewById(R.id.button6);
        button6.setEnabled(false);
        button6.setText("loading...");
        editText = findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    button6.setEnabled(false);
                }
                else {
                    button6.setEnabled(true);
                }

            }
        });



        WBdata();

    }

    public void resultsPage(View view) {
        currentUser.setDemoTotal(editText.getText().toString());
        Intent intent = new Intent(this, ResultsActivity.class);
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