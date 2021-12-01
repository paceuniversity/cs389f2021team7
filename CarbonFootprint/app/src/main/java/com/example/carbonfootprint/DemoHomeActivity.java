package com.example.carbonfootprint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DemoHomeActivity extends AppCompatActivity implements Serializable {

    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    public static final String SHARED_PREFERENCE = "sharedPreference";
    public static final String COUNTRY_CODE = "countryCode";
    public static final String SAVE_RESULTS_EXIT = "saveResultsExit";
    private static final int PERMISSIONS_FINE_LOCATION = 88;
    FusedLocationProviderClient fusedLocationProviderClient;
    List<Address> addresses;
    userInfo currentUser;
    public static DatabaseHelper databaseHelper;
    ArrayList<String> xmlcountryname;
    ArrayList<String> xmlcountrycode;
    String loadedCountryCode;
    Boolean loadedSaveResultsExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_demo_home);
        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);


        loadData();
        if (loadedCountryCode.length() > 0 && currentUser == null) {
            currentUser = new userInfo();
            currentUser.setCountryCode(loadedCountryCode);
        }

        if (currentUser == null) {
            currentUser = new userInfo();
            updateGPS();
        }

        if (loadedSaveResultsExit == true && currentUser != null) {
            currentUser.setSavePastResultsCheck(true);
        }
        else if (loadedSaveResultsExit == false && currentUser != null) {
            currentUser.setSavePastResultsCheck(false);
        }

        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(this);

        }

        parseXML();

        currentUser.setXmlcountrycode(xmlcountrycode);
        currentUser.setXmlcountryname(xmlcountryname);


//        Toast.makeText(this, currentUser.getCountryCode() + "", Toast.LENGTH_LONG).show();

//        if (LocationActivity.currentUserTemporary != null) {
//            currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);
//            currentUser = LocationActivity.currentUserTemporary.getCurrentUserTemporary();
//        }
//
//        if (LocationActivity.currentUserTemporary != null) {
//            currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);
//            currentUser = LocationActivity.currentUserTemporary.getCurrentUserTemporary();
//            Toast.makeText(DemoHomeActivity.this, LocationActivity.currentUserTemporary + "", Toast.LENGTH_LONG).show();
//
//        }



    }

    @Override
    public void onStart() {
        super.onStart();
        if (LocationActivity.currentUserTemporary != null && currentUser != null) {
            currentUser = LocationActivity.currentUserTemporary;
        }
        saveData();
    }


    public void demoCalculatorPage(View view) {
        Intent intent = new Intent(this, InitiateCalculator.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }

    public void pastResultsPage (View view) {
        Intent intent = new Intent(this, PastResultsTabbedActivity.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }

    public void settingsPage (View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
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
    public void parseXML() {
        XmlPullParserFactory xmlPullParserFactory;

        try {
            xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = xmlPullParserFactory.newPullParser();
            InputStream inputStream = getAssets().open("countries.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);

            xmlcountryname = new ArrayList<>();
            xmlcountrycode = new ArrayList<>();

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String xmlname = null;

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        xmlname = parser.getName();

                        if ("code".equals(xmlname)) {
                            xmlcountrycode.add(parser.nextText());
                        } else if ("name".equals(xmlname)) {
                            xmlcountryname.add(parser.nextText());
                        }
                        break;
                }

                eventType = parser.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(COUNTRY_CODE, currentUser.getCountryCode());
        editor.apply();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        loadedCountryCode = sharedPreferences.getString(COUNTRY_CODE, "");
        loadedSaveResultsExit = sharedPreferences.getBoolean(SAVE_RESULTS_EXIT, true);
    }
}