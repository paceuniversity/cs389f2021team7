package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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
    ImageView wasteInfo;
    CheckBox wasteCheckBox1, wasteCheckBox2, wasteCheckBox3, wasteCheckBox4, wasteCheckBox5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.waste_activity);
//        numPeopleInput = (EditText) findViewById(R.id.numPeopleHousehold);

        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);

        waste = 692;

        getresultspage = findViewById(R.id.getresultspage);
        homeenergybtn = findViewById(R.id.homeenergybtn);
        transportationbtn = findViewById(R.id.transportationbtn);

        wasteInfo = findViewById(R.id.wasteInfo);

        wasteInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWasteDialog();
            }
        });


        wasteCheckBox1 = findViewById(R.id.wasteCheckBox1);
        wasteCheckBox2 = findViewById(R.id.wasteCheckBox2);
        wasteCheckBox3 = findViewById(R.id.wasteCheckBox3);
        wasteCheckBox4 = findViewById(R.id.wasteCheckBox4);
        wasteCheckBox5 = findViewById(R.id.wasteCheckBox5);


        metal = 0;
        currentUser.setMetalCheck(false);
        glass = 0;
        currentUser.setGlassCheck(false);
        plastic = 0;
        currentUser.setPlasticCheck(false);
        newspaper = 0;
        currentUser.setNewspaperCheck(false);
        magazines = 0;
        currentUser.setMagazinesCheck(false);

        wasteCheckBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    metal = -89.38;
                    currentUser.setMetalCheck(true);
                }
                else {
                    metal = 0;
                    currentUser.setMetalCheck(false);
                }
            }
        });

        wasteCheckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    glass = -25.39;
                    currentUser.setGlassCheck(true);
                }
                else {
                    glass = 0;
                    currentUser.setGlassCheck(false);
                }
            }
        });

        wasteCheckBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    plastic = -35.56;
                    currentUser.setPlasticCheck(true);
                }
                else {
                    plastic = 0;
                    currentUser.setPlasticCheck(false);
                }
            }
        });

        wasteCheckBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    newspaper = -113.14;
                    currentUser.setNewspaperCheck(true);
                }
                else {
                    newspaper = 0;
                    currentUser.setNewspaperCheck(false);
                }
            }
        });

        wasteCheckBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    magazines = -27.46;
                    currentUser.setMagazinesCheck(true);
                }
                else {
                    magazines = 0;
                    currentUser.setMagazinesCheck(false);
                }
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





        homeenergybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentUser.setWqcomplete(true);
                estimatedWaste = (waste + plastic + magazines + metal + newspaper + glass) * (0.0005); //total waste in tons
                Intent intent = new Intent(Waste.this, HomeEnergy.class);
                currentUser.setWasteTotal(estimatedWaste);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });

        transportationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentUser.setWqcomplete(true);
                estimatedWaste = (waste + plastic + magazines + metal + newspaper + glass) * (0.0005); //total waste in tons
                Intent intent = new Intent(Waste.this, Transportation.class);
                currentUser.setWasteTotal(estimatedWaste);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });

        getresultspage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentUser.setWqcomplete(true);
                estimatedWaste = (waste + plastic + magazines + metal + newspaper + glass) * (0.0005); //total waste in tons
                Intent intent = new Intent(Waste.this, InitiateCalculator.class);
                currentUser.setWasteTotal(estimatedWaste);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });




    }
    private void showToast(String text)
    {
        Toast.makeText(Waste.this, text, Toast.LENGTH_LONG).show();
    }

    public void openWasteDialog() {
        WasteDialogue wasteDialogue = new WasteDialogue();
        wasteDialogue.show(getSupportFragmentManager(), "Waste Dialogue");
    }
}