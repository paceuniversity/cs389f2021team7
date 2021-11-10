package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Waste extends AppCompatActivity implements Serializable
{
    int numPeople;

    double estimatedWaste;
    double emissionsTotalAfterReduce;
    double waste;
    double plastic;
    double magazines;
    double metal;
    double newspaper;
    double glass;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    userInfo currentUser;
    Spinner wasteSpinner1, wasteSpinner2, wasteSpinner3, wasteSpinner4, wasteSpinner5;
    EditText numPeopleInput, plasticInput, magazinesInput, metalInput, newspaperInput, glassInput;
    ArrayList<String> wasteArrayList;
    Button homeenergybtn;
    Button transportationbtn;
    Button getresultspage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waste_activity);
//        numPeopleInput = (EditText) findViewById(R.id.numPeopleHousehold);


        currentUser = (userInfo) getIntent().getSerializableExtra(HomeEnergyReduceEmissions.CURRENT_USER_KEY);
        emissionsTotalAfterReduce = getIntent().getDoubleExtra(HomeEnergyReduceEmissions.CURRENT_USER_KEY, 0);

        wasteArrayList = new ArrayList<>();
        wasteArrayList.add("Y/N");
        wasteArrayList.add("Y");
        wasteArrayList.add("N");


        getresultspage = findViewById(R.id.getresultspage);

        Spinner wasteSpinner1 = findViewById(R.id.wasteSpinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, wasteArrayList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wasteSpinner1.setAdapter(adapter1);

        wasteSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i) == "Y") {
                    metal = -89.38;
                }
                else if (adapterView.getItemAtPosition(i) == "N") {
                    metal = 0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Spinner wasteSpinner2 = findViewById(R.id.wasteSpinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, wasteArrayList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wasteSpinner2.setAdapter(adapter2);

        wasteSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i) == "Y") {
                    glass = -25.39;
                }
                else if (adapterView.getItemAtPosition(i) == "N") {
                    glass = 0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner wasteSpinner3 = findViewById(R.id.wasteSpinner3);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, wasteArrayList);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wasteSpinner3.setAdapter(adapter3);

        wasteSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i) == "Y") {
                    plastic = -35.56;
                }
                else if (adapterView.getItemAtPosition(i) == "N") {
                    plastic = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner wasteSpinner4 = findViewById(R.id.wasteSpinner4);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, wasteArrayList);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wasteSpinner4.setAdapter(adapter4);

        wasteSpinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i) == "Y") {
                    newspaper = -113.14;
                }
                else if (adapterView.getItemAtPosition(i) == "N") {
                    newspaper = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner wasteSpinner5 = findViewById(R.id.wasteSpinner5);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, wasteArrayList);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wasteSpinner5.setAdapter(adapter5);

        wasteSpinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i) == "Y") {
                    magazines = -27.46;
                }
                else if (adapterView.getItemAtPosition(i) == "N") {
                    magazines = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        plasticInput = (EditText) findViewById(R.id.plasticInput);
//        magazinesInput = (EditText) findViewById(R.id.magazinesInput);
//        metalInput = (EditText) findViewById(R.id.metalInput);
//        newspaperInput = (EditText) findViewById(R.id.newspaperInput);
//        glassInput = (EditText) findViewById(R.id.glassInput);

//        submit = (Button) findViewById(R.id.button);
//        submit.setOnClickListener(view -> {
//
//            numPeople = Integer.parseInt(numPeopleInput.getText().toString());
//            waste = numPeople * 692; //waste in lbs without recycling
//
//
//            if (metalInput.getText().toString().equals("y")) //metal
//                metal = numPeople * (-89.38);
//            else
//                metal = 0;
//
//            if (glassInput.getText().toString().equals("y")) //glass
//                glass = numPeople * (-25.39);
//            else
//                glass = 0;
//
//            if (plasticInput.getText().toString().equals("y")) //plastic
//                plastic = numPeople * (-35.56);
//            else
//                plastic = 0;
//
//            if (newspaperInput.getText().toString().equals("y")) //newspaper
//                newspaper = numPeople * (-113.14);
//            else
//                newspaper = 0;
//
//            if (magazinesInput.getText().toString().equals("y")) //magazines
//                magazines = numPeople * (-27.46);
//            else
//                magazines = 0;
//
//            estimatedWaste = (waste + plastic + magazines + metal + newspaper + glass) * (0.0005); //total waste in tons
//            showToast(String.valueOf(estimatedWaste) + "tons per year");
//        });

        homeenergybtn = findViewById(R.id.homeenergybtn);
        transportationbtn = findViewById(R.id.transportationbtn);



        homeenergybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estimatedWaste = (waste + plastic + magazines + metal + newspaper + glass) * (0.0005); //total waste in tons
                Intent intent = new Intent(Waste.this, HomeEnergy.class);
//                currentUser.setWasteTotal(estimatedWaste);
                intent.putExtra(CURRENT_USER_KEY, estimatedWaste);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });

        transportationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estimatedWaste = (waste + plastic + magazines + metal + newspaper + glass) * (0.0005); //total waste in tons
                Intent intent = new Intent(Waste.this, Transportation.class);
//                currentUser.setWasteTotal(estimatedWaste);
                intent.putExtra(CURRENT_USER_KEY, estimatedWaste);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });

        getresultspage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estimatedWaste = (waste + plastic + magazines + metal + newspaper + glass) * (0.0005); //total waste in tons
                Intent intent = new Intent(Waste.this, ResultsActivity.class);
//                currentUser.setWasteTotal(estimatedWaste);
                intent.putExtra(CURRENT_USER_KEY, estimatedWaste);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });




    }
    private void showToast(String text)
    {
        Toast.makeText(Waste.this, text, Toast.LENGTH_LONG).show();
    }

}