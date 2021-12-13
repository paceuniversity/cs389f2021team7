package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class UnitsActivity extends AppCompatActivity implements Serializable {
    ImageView unitsInfo;
    ArrayList<String> unitsArray;
    Spinner unitsSpinner;
    Button unitsButton;
    userInfo currentUser;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    public static userInfo currentUserTemporary2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);
        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);

        unitsInfo = findViewById(R.id.unitsInfo);
        unitsSpinner = findViewById(R.id.unitsSpinner);
        unitsButton = findViewById(R.id.unitsButton);

        unitsInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUnitsDialog();
            }
        });
        unitsArray = new ArrayList<>();
        unitsArray.add("Select System of Measurement");
        unitsArray.add("Imperial System (US)");
        unitsArray.add("Metric System (Majority of the World)");

        ArrayAdapter<String> unitsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unitsArray);
        unitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitsSpinner.setAdapter(unitsAdapter);

        unitsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i) == "Select System of Measurement") {
                    unitsButton.setEnabled(false);
                }
                else if(adapterView.getItemAtPosition(i) == "Imperial System (US)") {
                    unitsButton.setEnabled(true);
                    currentUser.setImperialSystem(true);
                    currentUser.setMetricSystem(false);
                }
                else if(adapterView.getItemAtPosition(i) == "Metric System (Majority of the World)") {
                    unitsButton.setEnabled(true);
                    currentUser.setImperialSystem(false);
                    currentUser.setMetricSystem(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void openUnitsDialog() {
        UnitsDialogue unitsDialogue = new UnitsDialogue();
        unitsDialogue.show(getSupportFragmentManager(), "Units Dialogue");
    }

    public void UnitsToHome (View view) {
        currentUserTemporary2 = currentUser;
        finish();
    }

    @Override
    public void onBackPressed() {
    }

}