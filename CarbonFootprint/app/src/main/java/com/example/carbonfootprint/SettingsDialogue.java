package com.example.carbonfootprint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SettingsDialogue extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder settingsDialogueBuilder = new AlertDialog.Builder(getActivity());
        settingsDialogueBuilder.setTitle("Information")
                .setMessage("About: Tap to go to app information.\n\nChange Country: Tap to change country. This will change the country you compare your results to at the end of the calculator.\n\nChange Units: Tap to change preferred units in the calculator. This can only be changed if \"Set Units based on Location\" is unchecked.\n\nSet Units based on Location: Changes whether units are based on location or not.\n\nSave Past Results upon Exit: Changes whether you will be asked to save results at the end of the calculator or not.\n\nDelete All Past Results: Tap to delete all past results. \n\nClear All My Data and Exit the App: Tap to erase all data from the app and exit the app. All user data will be deleted.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return settingsDialogueBuilder.create();
    }
}
