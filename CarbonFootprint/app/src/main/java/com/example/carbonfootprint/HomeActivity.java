package com.example.carbonfootprint;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.carbonfootprint.databinding.ActivityHomeBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements Serializable {
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    public static final String SHARED_PREFERENCE = "sharedPreference";
    public static final String COUNTRY_CODE = "countryCode";
    public static final String SAVE_RESULTS_EXIT = "saveResultsExit";
    public static final String UNITS = "units";
    public static final String UNITS_LOCATION = "unitsLocation";

    private static final int PERMISSIONS_FINE_LOCATION = 88;
    FusedLocationProviderClient fusedLocationProviderClient;
    List<Address> addresses;
    userInfo currentUser;
    public static DatabaseHelper databaseHelper;
    ArrayList<String> xmlcountryname;
    ArrayList<String> xmlcountrycode;
    String loadedCountryCode;
    Boolean loadedSaveResultsExit, loadedUnits, loadedUnitsLocation;
    ActivityHomeBinding binding;
    Toolbar homeScreenToolbar;
    ImageView homeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        homeScreenToolbar = findViewById(R.id.homeToolbar);
        BottomNavigationView navView = findViewById(R.id.nav_view);

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


        if (loadedUnitsLocation == true && currentUser != null) {
            currentUser.setUnitsLocationCheck(true);
        }

        else if (loadedUnitsLocation == false && currentUser != null) {
            currentUser.setUnitsLocationCheck(false);
            if (loadedUnits == true && currentUser != null) {
                currentUser.setImperialSystem(true);
                currentUser.setMetricSystem(false);
            }
            else if (loadedUnits == false && currentUser != null) {
                currentUser.setImperialSystem(false);
                currentUser.setMetricSystem(true);
            }
        }

        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(this);

        }


        homeInfo = findViewById(R.id.homeInfo);


        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_pastresults, R.id.navigation_settings, R.id.navigation_dataVisualization)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);



        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getLabel().equals("Home")) {
                    homeScreenToolbar.setTitle("Home");
                    homeInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openHomeDialog();
                        }
                    });
                }
                else if (destination.getLabel().equals("Past Results")) {
                    homeScreenToolbar.setTitle("Past Results");
                    homeInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openPastResultsDialog();
                        }
                    });
                }
                else if (destination.getLabel().equals("Settings")) {
                    homeScreenToolbar.setTitle("Settings");
                    homeInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openSettingsDialog();
                        }
                    });
                }
                else if (destination.getLabel().equals("Data Visualization")) {
                    homeScreenToolbar.setTitle("Data Visualization");
                    homeInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openDataVisualizationDialog();
                        }
                    });
                }

            }
        });


        parseXML();

        currentUser.setXmlcountrycode(xmlcountrycode);
        currentUser.setXmlcountryname(xmlcountryname);


    }

    @Override
    public void onStart() {
        super.onStart();
        if (LocationActivity.currentUserTemporary != null  && currentUser != null) {
            currentUser = LocationActivity.currentUserTemporary;
        }
        if (UnitsActivity.currentUserTemporary2 != null && currentUser != null) {
            currentUser = UnitsActivity.currentUserTemporary2;
        }

        saveData();
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
    public void updateGPS() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(HomeActivity.this);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    Geocoder geocoder = new Geocoder(HomeActivity.this);
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
        editor.putBoolean(UNITS, currentUser.isImperialSystem());
        editor.apply();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        loadedCountryCode = sharedPreferences.getString(COUNTRY_CODE, "");
        loadedSaveResultsExit = sharedPreferences.getBoolean(SAVE_RESULTS_EXIT, true);
        loadedUnits = sharedPreferences.getBoolean(UNITS, true);
        loadedUnitsLocation = sharedPreferences.getBoolean(UNITS_LOCATION, true);
    }

    public void openHomeDialog() {
        HomeDialogue homeDialogue = new HomeDialogue();
        homeDialogue.show(getSupportFragmentManager(), "Home Dialogue");
    }
    public void openPastResultsDialog() {
        PastResultsDialogue pastResultsDialogue = new PastResultsDialogue();
        pastResultsDialogue.show(getSupportFragmentManager(), "Past Results Dialogue");
    }
    public void openDataVisualizationDialog() {
        DataVisualizationDialogue dataVisualizationDialogue = new DataVisualizationDialogue();
        dataVisualizationDialogue.show(getSupportFragmentManager(), "Data Visualization Dialogue");
    }
    public void openSettingsDialog() {
        SettingsDialogue settingsDialogue = new SettingsDialogue();
        settingsDialogue.show(getSupportFragmentManager(), "Settings Dialogue");
    }
    @Override
    public void onBackPressed() {

    }
}
