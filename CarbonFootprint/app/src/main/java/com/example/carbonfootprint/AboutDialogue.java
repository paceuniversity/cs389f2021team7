package com.example.carbonfootprint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AboutDialogue extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder aboutDialogueBuilder = new AlertDialog.Builder(getActivity());
        aboutDialogueBuilder.setTitle("Information")
                .setMessage("For more information on the app, please visit the Github link.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return aboutDialogueBuilder.create();
    }
}
