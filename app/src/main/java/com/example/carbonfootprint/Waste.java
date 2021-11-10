package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Waste extends AppCompatActivity
{
    int numPeople;

    double estimatedWaste;
    double waste;
    double plastic;
    double magazines;
    double metal;
    double newspaper;
    double glass;

    EditText numPeopleInput, plasticInput, magazinesInput, metalInput, newspaperInput, glassInput;

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waste_activity);
        numPeopleInput = (EditText) findViewById(R.id.numPeopleHousehold);
        plasticInput = (EditText) findViewById(R.id.plasticInput);
        magazinesInput = (EditText) findViewById(R.id.magazinesInput);
        metalInput = (EditText) findViewById(R.id.metalInput);
        newspaperInput = (EditText) findViewById(R.id.newspaperInput);
        glassInput = (EditText) findViewById(R.id.glassInput);

        submit = (Button) findViewById(R.id.button);
        submit.setOnClickListener(view -> {

            numPeople = Integer.parseInt(numPeopleInput.getText().toString());
            waste = numPeople * 692; //waste in lbs without recycling


            if (metalInput.getText().toString().equals("y")) //metal
                metal = numPeople * (-89.38);
            else
                metal = 0;

            if (glassInput.getText().toString().equals("y")) //glass
                glass = numPeople * (-25.39);
            else
                glass = 0;

            if (plasticInput.getText().toString().equals("y")) //plastic
                plastic = numPeople * (-35.56);
            else
                plastic = 0;

            if (newspaperInput.getText().toString().equals("y")) //newspaper
                newspaper = numPeople * (-113.14);
            else
                newspaper = 0;

            if (magazinesInput.getText().toString().equals("y")) //magazines
                magazines = numPeople * (-27.46);
            else
                magazines = 0;

            estimatedWaste = (waste + plastic + magazines + metal + newspaper + glass) * (0.0005); //total waste in tons
            showToast(String.valueOf(estimatedWaste) + "tons per year");
        });
    }
    private void showToast(String text)
    {
        Toast.makeText(Waste.this, text, Toast.LENGTH_LONG).show();
    }
}