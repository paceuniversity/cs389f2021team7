package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
    ImageView resultsInfo;
    Button button12;
    public static userInfo currentUserTemporary3;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_results_tabbed);


        resultsTabLayout = findViewById(R.id.resultsTabLayout);
        resultsViewPager = findViewById(R.id.resultsViewPager);
        button12 = findViewById(R.id.button12);
        resultsInfo = findViewById(R.id.resultsInfo);

        resultsInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openResultsDialog();
            }
        });

        FragmentManager resultsFragmentManager = getSupportFragmentManager();
        resultsFragmentAdapter = new ResultsFragmentAdapter(resultsFragmentManager, getLifecycle());
        resultsViewPager.setAdapter(resultsFragmentAdapter);

        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);

        householdNumber = currentUser.getHouseholdNumber();
        estimatedWaste = (float) currentUser.getWasteTotal();
        transportationTotal = (float) (currentUser.getTransportationTotal()/householdNumber);
        emissionsTotalAfterReduce = (float) (currentUser.getHomeEnergyTotal()/householdNumber);
        demoTotalNumber = (float) (emissionsTotalAfterReduce + estimatedWaste + transportationTotal);

        if(currentUser.isRetrieveCheck() || currentUser.getAvgValueWB() == null || currentUser.getHouseholdNumber() == 0) {
            button12.setText("EXIT");
        }

        resultsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                if (tab.getPosition() == 0) {
                    resultsInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openResultsDialog();
                        }
                    });
//            }
//                else if (tab.getPosition() == 1) {
//                    resultsInfo.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            openBarChartDialog();
//                        }
//                    });
//                }
//                else if (tab.getPosition() == 2) {
//                    resultsInfo.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            openPieChartDialog();
//                        }
//                    });
//                }
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
        if (currentUser.isRetrieveCheck() || currentUser.getAvgValueWB() == null || currentUser.getHouseholdNumber() == 0) {
            currentUserTemporary3 = currentUser;
            finish();
        }
        else {
            currentUser.setHomeEnergyTotal(emissionsTotalAfterReduce);
            currentUser.setDemoTotal(Float.toString(demoTotalNumber));
            Intent intent = new Intent(this, SuggestionsPage.class);
            intent.putExtra(CURRENT_USER_KEY, currentUser);
            startActivity(intent);
        }
    }

    public void openResultsDialog() {
        ResultsDialogue resultsDialogue = new ResultsDialogue();
        resultsDialogue.show(getSupportFragmentManager(), "Results Dialogue");
    }

    public void openBarChartDialog() {
        BarChartDialogue barChartDialogue = new BarChartDialogue();
        barChartDialogue.show(getSupportFragmentManager(), "Bar Chart Dialogue");
    }

    public void openPieChartDialog() {
        PieChartDialogue pieChartDialogue = new PieChartDialogue();
        pieChartDialogue.show(getSupportFragmentManager(), "Pie Chart Dialogue");
    }

    @Override
    public void onBackPressed() {

    }
}