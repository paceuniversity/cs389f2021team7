package com.example.carbonfootprint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class WasteDialogue extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder wasteDialogueBuilder = new AlertDialog.Builder(getActivity());
        wasteDialogueBuilder.setTitle("Information")
                .setMessage("Check the checkboxes that apply to you to proceed to Home Energy or Transportation. There is no minimum required checks.\n\nSave and Return will return you to the initial calculator page without losing your progress.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return wasteDialogueBuilder.create();
    }
}
