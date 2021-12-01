package com.example.carbonfootprint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ResultsFragmentAdapter extends FragmentStateAdapter {
    public ResultsFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {


        switch (position) {
            case 1:
                return new ResultsBarChartFragment();
            case 2:
                return new ResultsPieChartFragment();
        }

        return new ResultsFootprintFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
