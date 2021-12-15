package com.example.carbonfootprint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DataVisualizationDialogue extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dataVisualizationDialogueBuilder = new AlertDialog.Builder(getActivity());
        dataVisualizationDialogueBuilder.setTitle("Information")
                .setMessage("Tap the drop-down menu to choose which data you want visualized.\n\nZoom in and tap countries on the map to view their details.\n\nThis feature requires a working internet connection.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return dataVisualizationDialogueBuilder.create();
    }
}
