package com.example.carbonfootprint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SuggestionsDialogue extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder suggestionsDialogueBuilder = new AlertDialog.Builder(getActivity());
        suggestionsDialogueBuilder.setTitle("Information")
                .setMessage("Suggestions will appear depending on the data your entered in the calculator. It is possible that there are no suggestions.\n\nCheck the checkboxes to see how your results would change with certain suggestions.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return suggestionsDialogueBuilder.create();
    }
}