package com.example.carbonfootprint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class PastResultsDialogue extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder pastResultsDialogueBuilder = new AlertDialog.Builder(getActivity());
        pastResultsDialogueBuilder.setTitle("Information")
                .setMessage("List: Tap each saved past result to view their details. The footprint icon is green if your emissions are less than your saved location's emissions and red if your emissions are more than your saved location's emissions.\n\nLine Chart: Tap the points on the line chart to view their details. The larger the result, the larger the red space.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return pastResultsDialogueBuilder.create();
    }
}
