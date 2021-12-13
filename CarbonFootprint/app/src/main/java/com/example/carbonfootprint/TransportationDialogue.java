package com.example.carbonfootprint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class TransportationDialogue extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder transportationDialogueBuilder = new AlertDialog.Builder(getActivity());
        transportationDialogueBuilder.setTitle("Information")
                .setMessage("Choose the number of vehicles you possess then complete all the fields to proceed to Home Energy or Waste.\n\nSave and Return will return you to the initial calculator page without losing your progress.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return transportationDialogueBuilder.create();
    }
}
