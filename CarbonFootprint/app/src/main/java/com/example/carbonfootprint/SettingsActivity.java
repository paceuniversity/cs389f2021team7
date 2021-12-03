package com.example.carbonfootprint;

import static com.example.carbonfootprint.DemoHomeActivity.COUNTRY_CODE;
import static com.example.carbonfootprint.DemoHomeActivity.SAVE_RESULTS_EXIT;
import static com.example.carbonfootprint.DemoHomeActivity.SHARED_PREFERENCE;
import static com.example.carbonfootprint.DemoHomeActivity.databaseHelper;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity implements Serializable {
    RecyclerView settingsRecyclerView;
    RecyclerViewAdapterSettings recyclerViewAdapterSettings;
    RecyclerView.LayoutManager recyclerViewLayoutManagerSettings;
    ArrayList<CardViewSettings> settingsArrayList;
    CardView aboutCardView, changeCountryCardView, changeUnitsCardView, locationUnitCardView, savePastResultsCardView, deletePastResultsCardView, clearAllDataCardView;
    CheckBox locationUnitCheckBox, savePastResultsCheckBox;
    String checkBoxtest;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    userInfo currentUser;
    String loadedCountryCode;
    Boolean loadedSaveResultsExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_settings);

        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
//
//        }
//        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) = PackageManager.PERMISSION_GRANTED;

//        ((ActivityManager)context.getSystemService(ACTIVITY_SERVICE)).clearApplicationUserData();
//        settingsArrayList = new ArrayList<>();
//        settingsArrayList.add(new CardViewSettings(R.drawable.ic_baseline_info_24, "About"));
//        settingsArrayList.add(new CardViewSettings(R.drawable.ic_baseline_location_on_24, "Allow Location Services"));
//        settingsArrayList.add(new CardViewSettings(R.drawable.ic_baseline_balance_24, "Change Units"));
//        settingsArrayList.add(new CardViewSettings(R.drawable.ic_baseline_my_location_24, "Set Units to Location Units"));
//        settingsArrayList.add(new CardViewSettings(R.drawable.ic_baseline_save_24, "Save Past Results upon Exit"));
//        settingsArrayList.add(new CardViewSettings(R.drawable.ic_baseline_delete_forever_24, "Delete All Past Results"));
//        settingsArrayList.add(new CardViewSettings(R.drawable.ic_baseline_clear_24, "Clear All My Data"));

        loadData();
        currentUser.setCountryCode(loadedCountryCode);
        currentUser.setSavePastResultsCheck(loadedSaveResultsExit);


        aboutCardView = findViewById(R.id.aboutCardView);
        changeCountryCardView = findViewById(R.id.changeCountryCardView);
        changeUnitsCardView = findViewById(R.id.changeUnitsCardView);
        deletePastResultsCardView = findViewById(R.id.deletePastResultsCardView);
        clearAllDataCardView = findViewById(R.id.clearAllDataCardView);
        locationUnitCheckBox = findViewById(R.id.locationUnitCheckBox);
        savePastResultsCheckBox = findViewById(R.id.savePastResultsCheckBox);

        if (currentUser.getSavePastResultsCheck() == true) {
            savePastResultsCheckBox.setChecked(true);
        }
        else {
            savePastResultsCheckBox.setChecked(false);
        }

//        locationServicesCardView = findViewById(R.id.locationServicesCardView);
//        locationServicesCheckBox = findViewById(R.id.locationServicesCheckBox);

//        locationServicesCheckBox.setChecked(true);

        aboutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, AboutActivity.class);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });
        changeCountryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, LocationActivity.class);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });
        changeUnitsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingsActivity.this, "clicked change units", Toast.LENGTH_LONG).show();
            }
        });
        locationUnitCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == false) {
                    Toast.makeText(SettingsActivity.this, "false checkbox units", Toast.LENGTH_LONG).show();
                }
                else if (b == true) {
                    Toast.makeText(SettingsActivity.this, "true checkbox units", Toast.LENGTH_LONG).show();
                }
            }
        });
        savePastResultsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == false) {
                    currentUser.setSavePastResultsCheck(false);
                    saveData();
                }
                else if (b == true) {
                    currentUser.setSavePastResultsCheck(true);
                    saveData();
                }
            }
        });
        deletePastResultsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteAll();
            }
        });
        clearAllDataCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityManager)SettingsActivity.this.getSystemService(Context.ACTIVITY_SERVICE)).clearApplicationUserData();
                Toast.makeText(SettingsActivity.this, "clicked clear all data", Toast.LENGTH_LONG).show();
            }
        });


//        settingsRecyclerView = findViewById(R.id.settingsRecyclerView);
//        settingsRecyclerView.setHasFixedSize(true);
//        recyclerViewLayoutManagerSettings = new LinearLayoutManager(this);
//        recyclerViewAdapterSettings = new RecyclerViewAdapterSettings(settingsArrayList);
//        settingsRecyclerView.setLayoutManager(recyclerViewLayoutManagerSettings);
//        settingsRecyclerView.setAdapter(recyclerViewAdapterSettings);
//        recyclerViewAdapterSettings.setOnItemClickListenerSettings(new RecyclerViewAdapterSettings.OnItemClickListenerSettings() {
//            @Override
//            public void onItemClick(int position) {
//                if (position == 0) {
//                    Toast.makeText(SettingsActivity.this, "clicked", Toast.LENGTH_LONG).show();
//                }
//            }
//
//        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if (LocationActivity.currentUserTemporary != null) {
            currentUser = LocationActivity.currentUserTemporary;
        }
        saveData();
        loadData();
        currentUser.setCountryCode(loadedCountryCode);
        currentUser.setSavePastResultsCheck(loadedSaveResultsExit);
    }
    public void homePage (View view) {
        Intent intent = new Intent(this, DemoHomeActivity.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(COUNTRY_CODE, currentUser.getCountryCode());
        editor.putBoolean(SAVE_RESULTS_EXIT, savePastResultsCheckBox.isChecked());
        editor.apply();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        loadedCountryCode = sharedPreferences.getString(COUNTRY_CODE, "");
        loadedSaveResultsExit = sharedPreferences.getBoolean(SAVE_RESULTS_EXIT, true);
    }
}