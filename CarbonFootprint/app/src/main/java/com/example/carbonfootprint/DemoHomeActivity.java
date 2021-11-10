package com.example.carbonfootprint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.Serializable;
import java.util.List;

public class DemoHomeActivity extends AppCompatActivity implements Serializable {

    public static final String CURRENT_USER_KEY = "CurrentUserKey";

    private static final int PERMISSIONS_FINE_LOCATION = 88;
    FusedLocationProviderClient fusedLocationProviderClient;
    List<Address> addresses;
    userInfo currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_home);

        currentUser = new userInfo();



        if (getIntent().getSerializableExtra(LocationActivity.CURRENT_USER_KEY) != null) {
            currentUser = (userInfo) getIntent().getSerializableExtra(LocationActivity.CURRENT_USER_KEY);
        }

        if (getIntent().getSerializableExtra(DemoCalculatorActivity.CURRENT_USER_KEY) != null) {
            currentUser = (userInfo) getIntent().getSerializableExtra(DemoCalculatorActivity.CURRENT_USER_KEY);
        }


        updateGPS();



    }


    public void demoCalculatorPage(View view) {
        Intent intent = new Intent(this, InitiateCalculator.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSIONS_FINE_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateGPS();
                }
                else {
                    Intent intent1 = new Intent(this, LocationActivity.class);
                    intent1.putExtra(CURRENT_USER_KEY, currentUser);
                    startActivity(intent1);
                }
        }
    }
    private void updateGPS() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(DemoHomeActivity.this);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    Geocoder geocoder = new Geocoder(DemoHomeActivity.this);
                    try {
                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        currentUser.setCountryCode(addresses.get(0).getCountryCode());
                    }
                    catch (Exception e) {
                    }

                }
            });
        }
        else {
            if (currentUser.getLocationCheck() == false) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
                }
            }
        }
    }
}