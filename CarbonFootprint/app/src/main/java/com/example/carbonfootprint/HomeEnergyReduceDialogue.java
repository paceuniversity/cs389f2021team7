package com.example.carbonfootprint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class HomeEnergyReduceDialogue extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder homeEnergyReduceDialogueBuilder = new AlertDialog.Builder(getActivity());
        homeEnergyReduceDialogueBuilder.setTitle("Information")
                .setMessage("Complete all the fields to proceed to Transportation or Waste.\n\nSave and Return will return you to the initial calculator page without losing your progress.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return homeEnergyReduceDialogueBuilder.create();
    }
}
