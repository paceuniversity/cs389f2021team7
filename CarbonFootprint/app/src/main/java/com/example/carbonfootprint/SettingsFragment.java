package com.example.carbonfootprint;

import static android.content.Context.MODE_PRIVATE;
import static com.example.carbonfootprint.HomeActivity.COUNTRY_CODE;
import static com.example.carbonfootprint.HomeActivity.SAVE_RESULTS_EXIT;
import static com.example.carbonfootprint.HomeActivity.SHARED_PREFERENCE;
import static com.example.carbonfootprint.HomeActivity.UNITS;
import static com.example.carbonfootprint.HomeActivity.UNITS_LOCATION;
import static com.example.carbonfootprint.HomeActivity.databaseHelper;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbonfootprint.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;


public class SettingsFragment extends Fragment implements Serializable {
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    userInfo currentUser;
    RecyclerView settingsRecyclerView;
    RecyclerViewAdapterSettings recyclerViewAdapterSettings;
    RecyclerView.LayoutManager recyclerViewLayoutManagerSettings;
    ArrayList<CardViewSettings> settingsArrayList;
    CardView aboutCardView, changeCountryCardView, changeUnitsCardView, locationUnitCardView, savePastResultsCardView, deletePastResultsCardView, clearAllDataCardView;
    CheckBox locationUnitCheckBox, savePastResultsCheckBox;
    String checkBoxtest;
    String loadedCountryCode;
    Boolean loadedSaveResultsExit;
    Boolean loadedUnits;
    Boolean loadedUnitsLocation;
    ConstraintLayout settingsLayout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_settings, container, false);


        if (currentUser == null) {
            currentUser = ((HomeActivity) getActivity()).currentUser;
        }
        else {
            currentUser = (userInfo) getActivity().getIntent().getSerializableExtra(CURRENT_USER_KEY);
        }

        settingsLayout = view.findViewById(R.id.settingsLayout);

        loadData();
        currentUser.setCountryCode(loadedCountryCode);
        currentUser.setSavePastResultsCheck(loadedSaveResultsExit);
        currentUser.setUnitsLocationCheck(loadedUnitsLocation);

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

        if (!currentUser.getUnitsLocationCheck()) {
            currentUser.setImperialSystem(loadedUnits);
            if (currentUser.isImperialSystem()) {
                currentUser.setMetricSystem(false);
            }
            else {
                currentUser.setMetricSystem(true);
            }
        }

        aboutCardView = view.findViewById(R.id.aboutCardView);
        changeCountryCardView = view.findViewById(R.id.changeCountryCardView);
        changeUnitsCardView = view.findViewById(R.id.changeUnitsCardView);
        deletePastResultsCardView = view.findViewById(R.id.deletePastResultsCardView);
        clearAllDataCardView = view.findViewById(R.id.clearAllDataCardView);
        locationUnitCheckBox = view.findViewById(R.id.locationUnitCheckBox);
        savePastResultsCheckBox = view.findViewById(R.id.savePastResultsCheckBox);

        if (currentUser.getSavePastResultsCheck() == true) {
            savePastResultsCheckBox.setChecked(true);
        }
        else {
            savePastResultsCheckBox.setChecked(false);
        }

        if (currentUser.getUnitsLocationCheck() == true) {
            locationUnitCheckBox.setChecked(true);
        }
        else {
            locationUnitCheckBox.setChecked(false);
        }

        if (locationUnitCheckBox.isChecked()) {
            changeUnitsCardView.setEnabled(false);
        }
        aboutCardView.setBackgroundColor(Color.parseColor("#FFFFFF"));

//        locationServicesCardView = findViewById(R.id.locationServicesCardView);
//        locationServicesCheckBox = findViewById(R.id.locationServicesCheckBox);

//        locationServicesCheckBox.setChecked(true);

        aboutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutCardView.setBackgroundColor(Color.parseColor("#D3D3D3"));
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });
        changeCountryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeCountryCardView.setBackgroundColor(Color.parseColor("#D3D3D3"));
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });
        changeUnitsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeUnitsCardView.setBackgroundColor(Color.parseColor("#D3D3D3"));
                Intent intent = new Intent(getActivity(), UnitsActivity.class);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });
        locationUnitCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == false) {
                    currentUser.setUnitsLocationCheck(false);
                    saveData();
                    changeUnitsCardView.setEnabled(true);
                    Intent intent = new Intent(getActivity(), UnitsActivity.class);
                    intent.putExtra(CURRENT_USER_KEY, currentUser);
                    startActivity(intent);
                }
                else if (b == true) {
                    currentUser.setUnitsLocationCheck(true);
                    saveData();
                    changeUnitsCardView.setEnabled(false);
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
                deletePastResultsCardView.setBackgroundColor(Color.parseColor("#D3D3D3"));
                AlertDialog dialog1 = new AlertDialog.Builder(getActivity())
                        .setTitle("Warning")
                        .setMessage("Are you sure you want to delete all of your past results?")
                        .setPositiveButton("YES", null)
                        .setNegativeButton("NO", null)
                        .show();
                Button positiveButton1 = dialog1.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        databaseHelper.deleteAll();
                        dialog1.dismiss();
                        Snackbar.make(settingsLayout, "Deleted All Past Results", Snackbar.LENGTH_SHORT).show();
                    }
                });
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        deletePastResultsCardView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                }, 300);
            }
        });
        clearAllDataCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllDataCardView.setBackgroundColor(Color.parseColor("#D3D3D3"));
                AlertDialog dialog2 = new AlertDialog.Builder(getActivity())
                        .setTitle("Warning")
                        .setMessage("Are you sure you want to clear all of your data and exit the app?")
                        .setPositiveButton("YES", null)
                        .setNegativeButton("NO", null)
                        .show();
                Button positiveButton2 = dialog2.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((ActivityManager)getActivity().getSystemService(Context.ACTIVITY_SERVICE)).clearApplicationUserData();
                        dialog2.dismiss();
                    }
                });

                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clearAllDataCardView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                }, 300);


            }
        });



        return view;
    }

    public void homePage (View view) {
        Intent intent = new Intent(getActivity(), DemoHomeActivity.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }
    public void saveData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(COUNTRY_CODE, currentUser.getCountryCode());
        editor.putBoolean(SAVE_RESULTS_EXIT, savePastResultsCheckBox.isChecked());
        editor.putBoolean(UNITS, currentUser.isImperialSystem());
        editor.putBoolean(UNITS_LOCATION, locationUnitCheckBox.isChecked());
        editor.apply();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        loadedCountryCode = sharedPreferences.getString(COUNTRY_CODE, "");
        loadedSaveResultsExit = sharedPreferences.getBoolean(SAVE_RESULTS_EXIT, true);
        loadedUnits = sharedPreferences.getBoolean(UNITS, true);
        loadedUnitsLocation = sharedPreferences.getBoolean(UNITS_LOCATION, true);
    }
    @Override
    public void onStart() {
        super.onStart();
        aboutCardView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        changeCountryCardView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        changeUnitsCardView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        deletePastResultsCardView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        clearAllDataCardView.setBackgroundColor(Color.parseColor("#FFFFFF"));

    }


}