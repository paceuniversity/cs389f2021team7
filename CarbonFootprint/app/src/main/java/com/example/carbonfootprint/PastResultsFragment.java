package com.example.carbonfootprint;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.carbonfootprint.PastResultsFragmentAdapter;
import com.example.carbonfootprint.R;
import com.example.carbonfootprint.userInfo;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;

public class PastResultsFragment extends Fragment implements Serializable {
    TabLayout pastResultsTabLayout;
    ViewPager2 pastResultsViewPager;
    PastResultsFragmentAdapter pastResultsFragmentAdapter;
    userInfo currentUser;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_past_results_tabbed, container, false);

        pastResultsTabLayout = view.findViewById(R.id.pastResultsTabLayout);
        pastResultsViewPager = view.findViewById(R.id.pastResultsViewPager);

        FragmentManager pastResultsFragmentManager = getActivity().getSupportFragmentManager();
        pastResultsFragmentAdapter = new PastResultsFragmentAdapter(pastResultsFragmentManager, getLifecycle());
        pastResultsViewPager.setAdapter(pastResultsFragmentAdapter);

        if (currentUser == null) {
            currentUser = ((HomeActivity) getActivity()).currentUser;
        }
        else {
            currentUser = (userInfo) getActivity().getIntent().getSerializableExtra(CURRENT_USER_KEY);
        }

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


        return view;
    }

}