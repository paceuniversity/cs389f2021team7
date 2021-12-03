package com.example.carbonfootprint;

import static android.content.Context.MODE_PRIVATE;
import static com.example.carbonfootprint.HomeActivity.COUNTRY_CODE;
import static com.example.carbonfootprint.HomeActivity.SAVE_RESULTS_EXIT;
import static com.example.carbonfootprint.HomeActivity.SHARED_PREFERENCE;
import static com.example.carbonfootprint.HomeActivity.databaseHelper;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbonfootprint.R;

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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_settings, container, false);


        if (currentUser == null) {
            currentUser = ((HomeActivity) getActivity()).currentUser;
        }
        else {
            currentUser = (userInfo) getActivity().getIntent().getSerializableExtra(CURRENT_USER_KEY);
        }

        loadData();
        currentUser.setCountryCode(loadedCountryCode);
        currentUser.setSavePastResultsCheck(loadedSaveResultsExit);


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

//        locationServicesCardView = findViewById(R.id.locationServicesCardView);
//        locationServicesCheckBox = findViewById(R.id.locationServicesCheckBox);

//        locationServicesCheckBox.setChecked(true);

        aboutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });
        changeCountryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });
        changeUnitsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "clicked change units", Toast.LENGTH_LONG).show();
            }
        });
        locationUnitCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == false) {
                    Toast.makeText(getActivity(), "false checkbox units", Toast.LENGTH_LONG).show();
                }
                else if (b == true) {
                    Toast.makeText(getActivity(), "true checkbox units", Toast.LENGTH_LONG).show();
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
                ((ActivityManager)getActivity().getSystemService(Context.ACTIVITY_SERVICE)).clearApplicationUserData();
                Toast.makeText(getActivity(), "clicked clear all data", Toast.LENGTH_LONG).show();
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
        editor.apply();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        loadedCountryCode = sharedPreferences.getString(COUNTRY_CODE, "");
        loadedSaveResultsExit = sharedPreferences.getBoolean(SAVE_RESULTS_EXIT, true);
    }


}