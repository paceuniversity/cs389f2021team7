package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import java.io.Serializable;



public class WasteResults extends AppCompatActivity implements Serializable {
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    CheckBox plasticBox;
    CheckBox metalBox;
    CheckBox glassBox;
    CheckBox magazinesBox;
    CheckBox newspaperBox;

    double newspaperAfterReduce;
    double plasticAfterReduce;
    double metalAfterReduce;
    double glassAfterReduce;
    double magazinesAfterReduce;

    TextView waste;
    TextView total;
    double currentWaste;
    double wasteVariable; //may use for code
    double totalAfterReduction; //may use for code
    userInfo currentUser;
    Button homeenergybtn;
    Button transportationbtn;
    Button suggestionslandingbtn;

    //String newString = new MainActivity.getString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waste_results);

        currentUser = (userInfo) getIntent().getSerializableExtra(Waste.CURRENT_USER_KEY);
        //double estimatedWaste = getIntent().getDoubleExtra(Waste.CURRENT_USER_KEY, 0);

        newspaperBox = findViewById(R.id.newspaper_box); //declares newspaper checkbox
        plasticBox = findViewById(R.id.plastic_box); //declares plastic checkbox
        metalBox = findViewById(R.id.metal_box); //declares metal checkbox
        glassBox = findViewById(R.id.glass_box); //declares glass checkbox
        magazinesBox = findViewById(R.id.magazines_box); //declares magazines checkbox

        homeenergybtn = findViewById(R.id.home_energy_button); //declares home energy button
        transportationbtn = findViewById(R.id.transportation_button); //declares transportation button
        suggestionslandingbtn = findViewById(R.id.suggestions_landing_button); //declares suggestions landing page button

        waste = findViewById(R.id.waste_results); //declares waste total text
        total = findViewById(R.id.total_results); //declares total text

        //code for possible code 1
        newspaperBox.setOnCheckedChangeListener(new Chk_class());
        plasticBox.setOnCheckedChangeListener(new Chk_class1());
        metalBox.setOnCheckedChangeListener(new Chk_class2());
        glassBox.setOnCheckedChangeListener(new Chk_class3());
        magazinesBox.setOnCheckedChangeListener(new Chk_class4());

        //code for possible code 2
        /*newspaperBox.setOnCheckedChangeListener(new Chk_class());
        plasticBox.setOnCheckedChangeListener(new Chk_class());
        metalBox.setOnCheckedChangeListener(new Chk_class());
        glassBox.setOnCheckedChangeListener(new Chk_class());
        magazinesBox.setOnCheckedChangeListener(new Chk_class());*/

        currentWaste = currentUser.getWasteTotal(); //gets Waste score from userInfo
        waste.setText("Waste = " + String.format("%.2f", currentWaste) + " tons"); //current Waste shows on screen before possible reduction

        //when the Home Energy button is clicked, user is brought to Home Energy page
        homeenergybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //estimatedWaste = (waste + plastic + magazines + metal + newspaper + glass) * (0.0005); //total waste in tons
                Intent intent = new Intent(WasteResults.this, HomeEnergy.class);
                //  currentUser.setWasteTotal(estimatedWaste);
                //intent.putExtra(CURRENT_USER_KEY, estimatedWaste);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });

        //when the Transportation button is clicked, user is brought to Transportation page
        transportationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //estimatedWaste = (waste + plastic + magazines + metal + newspaper + glass) * (0.0005); //total waste in tons
                Intent intent = new Intent(WasteResults.this, Transportation.class);
                // currentUser.setWasteTotal(estimatedWaste);
                // intent.putExtra(CURRENT_USER_KEY, estimatedWaste);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });

    }



    // beginning possible code 1

    //reduce Waste score by selecting to recycle newspaper
    class Chk_class implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            currentWaste = currentUser.getWasteTotal();


            if (newspaperBox.isChecked()) {
                newspaperAfterReduce = currentWaste + (-113.14 * 0.0005);
                currentUser.setWasteTotal(newspaperAfterReduce);
                waste.setText("Waste = " + String.format("%.2f", newspaperAfterReduce) + " tons");
            }
            else{
                currentUser.setWasteTotal(currentWaste);
                waste.setText("Waste = " + String.format("%.2f", currentWaste) + " tons");
            }
        }
    }

    //reduce Waste score by selecting to recycle plastic
    class Chk_class1 implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //currentWaste = currentUser.getWasteTotal();

            if (plasticBox.isChecked()) {
                plasticAfterReduce = currentWaste + (-35.56 * 0.0005);
                currentUser.setWasteTotal(plasticAfterReduce);
                waste.setText("Waste = " + String.format("%.2f", plasticAfterReduce) + " tons");
            }
            else{
                currentUser.setWasteTotal(currentWaste);
                waste.setText("Waste = " + String.format("%.2f", currentWaste) + " tons");
            }
        }
    }

    //reduce Waste score by selecting to recycle metal
    class Chk_class2 implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //currentWaste = currentUser.getWasteTotal();

            if (metalBox.isChecked()) {
                metalAfterReduce = currentWaste + (-89.38 * 0.0005);
                currentUser.setWasteTotal(metalAfterReduce);
                waste.setText("Waste = " + String.format("%.2f", metalAfterReduce) + " tons");
            }
            else{
                currentUser.setWasteTotal(currentWaste);
                waste.setText("Waste = " + String.format("%.2f", currentWaste) + " tons");
            }
        }
    }

    //reduce Waste score by selecting to recycle glass
    class Chk_class3 implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //currentWaste = currentUser.getWasteTotal();

            if (glassBox.isChecked()) {
                glassAfterReduce = currentWaste + (-25.39 * 0.0005);
                currentUser.setWasteTotal(glassAfterReduce);
                waste.setText("Waste = " + String.format("%.2f", glassAfterReduce) + " tons");
            }
            else{
                currentUser.setWasteTotal(currentWaste);
                waste.setText("Waste = " + String.format("%.2f", currentWaste) + " tons");
            }
        }
    }

    //reduce Waste score by selecting to recycle magazines
    class Chk_class4 implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //currentWaste = currentUser.getWasteTotal();

            if (magazinesBox.isChecked()) {
                magazinesAfterReduce = currentWaste + (-27.46 * 0.0005);
                currentUser.setWasteTotal(magazinesAfterReduce);
                waste.setText("Waste = " + String.format("%.2f", magazinesAfterReduce) + " tons");
            }
            else{
                currentUser.setWasteTotal(currentWaste);
                waste.setText("Waste = " + String.format("%.2f", currentWaste) + " tons");
            }
        }
    }
}
//end possible code 1







//beginning possible code 2
   /* class Chk_class implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            currentWaste = currentUser.getWasteTotal();
            wasteVariable = currentWaste;

            if (newspaperBox.isChecked()) {
                newspaperAfterReduce = currentWaste + (-113.14 * 0.0005);
                currentUser.setWasteTotal(newspaperAfterReduce);
                waste.setText("Waste = " + String.format("%.2f", newspaperAfterReduce) + " tons");
            }
            if (plasticBox.isChecked()) {
                plasticAfterReduce = currentWaste + (-35.56 * 0.0005);
                currentUser.setWasteTotal(plasticAfterReduce);
                waste.setText("Waste = " + String.format("%.2f", plasticAfterReduce) + " tons");
            }
            if (metalBox.isChecked()) {
                metalAfterReduce = currentWaste + (-89.38 * 0.0005);
                currentUser.setWasteTotal(metalAfterReduce);
                waste.setText("Waste = " + String.format("%.2f", metalAfterReduce) + " tons");
            }
            if (glassBox.isChecked()) {
                glassAfterReduce = currentWaste + (-25.39 * 0.0005);
                currentUser.setWasteTotal(glassAfterReduce);
                waste.setText("Waste = " + String.format("%.2f", glassAfterReduce) + " tons");
            }
            if (magazinesBox.isChecked()) {
                magazinesAfterReduce = currentWaste + (-27.46 * 0.0005);
                currentUser.setWasteTotal(magazinesAfterReduce);
                waste.setText("Waste = " + String.format("%.2f", magazinesAfterReduce) + " tons");
            }
            else{
                currentUser.setWasteTotal(wasteVariable);
            }

        }
    }
}*/
//end possible code 2


//MY 1ST CODE BEGINNING
    /*public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        wasteAfterReduction = currentUser.getWasteTotal();
        //waste.setText("Waste = " + String.format("%.2f", wasteAfterReduction));

        //Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.newspaper_box:
                newspaperAfterReduce = checked ? (wasteAfterReduction + (-113.14 * 0.0005)) : (wasteAfterReduction + 0);
                //Toast.makeText(WasteResults.this, newspaperAfterReduce , Toast.LENGTH_LONG).show();
                break;
            case R.id.plastic_box:
                plasticAfterReduce = checked ? (wasteAfterReduction + (-35.56 * 0.0005)) : (wasteAfterReduction + 0);
                break;
            case R.id.metal_box:
                metalAfterReduce = checked ? (wasteAfterReduction + (-89.38 * 0.0005)) : (wasteAfterReduction + 0);
                break;
            case R.id.glass_box:
                glassAfterReduce = checked ? (wasteAfterReduction + (-25.39 * 0.0005)) : (wasteAfterReduction + 0);
                break;
            case R.id.magazines_box:
                magazinesAfterReduce = checked ? (wasteAfterReduction + (-27.46 * 0.0005)) : (wasteAfterReduction + 0);
                break;
        }

        waste.setText("Waste = " + newspaperAfterReduce + plasticAfterReduce + metalAfterReduce + glassAfterReduce + magazinesAfterReduce);
        //waste.setText("Waste = " + wasteAfterReduction);
        //total.setText("Total = " + wasteAfterReduction);
    }
}*/
//MY 1ST CODE END
