package com.example.carbonfootprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.carbonfootprint.R;
import com.example.carbonfootprint.databinding.FragmentHomeBinding;

import java.io.Serializable;

public class HomeFragment extends Fragment implements Serializable {
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    userInfo currentUser;
    ImageView calculatorIcon;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        calculatorIcon = view.findViewById(R.id.calculatorIcon);

        calculatorIcon.setImageResource(R.drawable.calculator_icon);


        calculatorIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorIcon.setImageResource(R.drawable.calculator_icon_darkened);
                if (currentUser == null) {
                    currentUser = ((HomeActivity) getActivity()).currentUser;
                }
                else {
                    currentUser = (userInfo) getActivity().getIntent().getSerializableExtra(CURRENT_USER_KEY);
                }
                currentUser.setName(null);
                currentUser.setHouseholdNumber(0);
                currentUser.setHeqcomplete(false);
                currentUser.setTqcomplete(false);
                currentUser.setWqcomplete(false);
                currentUser.setDisableFirebase(false);
                Intent intent = new Intent(getActivity(), InitiateCalculator.class);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });

        return view;
    }

}