package com.example.carbonfootprint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PastResultsFragmentAdapter extends FragmentStateAdapter {
    public PastResultsFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new PastResultsLineChartFragment();
        }

        return new PastResultsListFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
