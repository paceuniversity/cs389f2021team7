package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;

public class PastResultsTabbedActivity extends AppCompatActivity implements Serializable {
    TabLayout pastResultsTabLayout;
    ViewPager2 pastResultsViewPager;
    PastResultsFragmentAdapter pastResultsFragmentAdapter;
    userInfo currentUser;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_past_results_tabbed);

        pastResultsTabLayout = findViewById(R.id.pastResultsTabLayout);
        pastResultsViewPager = findViewById(R.id.pastResultsViewPager);

        FragmentManager pastResultsFragmentManager = getSupportFragmentManager();
        pastResultsFragmentAdapter = new PastResultsFragmentAdapter(pastResultsFragmentManager, getLifecycle());
        pastResultsViewPager.setAdapter(pastResultsFragmentAdapter);

        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);


        pastResultsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pastResultsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        pastResultsViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                pastResultsTabLayout.selectTab(pastResultsTabLayout.getTabAt(position));
            }
        });
    }
    public void PastResultsToHomePage (View view) {
        Intent intent = new Intent(this, DemoHomeActivity.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }

}