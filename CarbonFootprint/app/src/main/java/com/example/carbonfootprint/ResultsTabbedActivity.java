package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;

public class ResultsTabbedActivity extends AppCompatActivity implements Serializable {
    TabLayout resultsTabLayout;
    ViewPager2 resultsViewPager;
    ResultsFragmentAdapter resultsFragmentAdapter;
    userInfo currentUser;
    float emissionsTotalAfterReduce;
    float estimatedWaste;
    float transportationTotal;
    int householdNumber;
    float demoTotalNumber;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_results_tabbed);


        resultsTabLayout = findViewById(R.id.resultsTabLayout);
        resultsViewPager = findViewById(R.id.resultsViewPager);

        FragmentManager resultsFragmentManager = getSupportFragmentManager();
        resultsFragmentAdapter = new ResultsFragmentAdapter(resultsFragmentManager, getLifecycle());
        resultsViewPager.setAdapter(resultsFragmentAdapter);

        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);

        householdNumber = currentUser.getHouseholdNumber();
        estimatedWaste = (float) currentUser.getWasteTotal();
        transportationTotal = (float) (currentUser.getTransportationTotal()/householdNumber);
        emissionsTotalAfterReduce = (float) (currentUser.getHomeEnergyTotal()/householdNumber);
        demoTotalNumber = (float) (emissionsTotalAfterReduce + estimatedWaste + transportationTotal);


        resultsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                resultsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        resultsViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                resultsTabLayout.selectTab(resultsTabLayout.getTabAt(position));
            }
        });

    }
    public void suggestionsPage(View view) {
        currentUser.setHomeEnergyTotal(emissionsTotalAfterReduce);
        currentUser.setDemoTotal(Float.toString(demoTotalNumber));
        Intent intent = new Intent(this, InitiateSuggestionsActivity.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }

}