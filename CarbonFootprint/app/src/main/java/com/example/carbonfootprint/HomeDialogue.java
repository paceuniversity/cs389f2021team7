package com.example.carbonfootprint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class HomeDialogue extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder homeDialogueBuilder = new AlertDialog.Builder(getActivity());
        homeDialogueBuilder.setTitle("Information")
                .setMessage("Welcome to Carbon Footprint.\n\nTap the calculator if you want to begin calculating your CO2 emissions.\n\nTap the icons on the bottom navigation menu to go to Past Results, Data Visualization, or Settings.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return homeDialogueBuilder.create();
    }
}
