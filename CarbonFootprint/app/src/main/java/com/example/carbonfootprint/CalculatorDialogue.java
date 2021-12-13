package com.example.carbonfootprint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class CalculatorDialogue extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder calculatorDialogueBuilder = new AlertDialog.Builder(getActivity());
        calculatorDialogueBuilder.setTitle("Information")
                .setMessage("Enter the name you want to use and your household size. Please do not exceed the maximum amount of characters allowed.\n\nA green check mark will appear next to Home Energy, Transportation, and Waste when you complete them. When you complete all of them, you will be able to get your results.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return calculatorDialogueBuilder.create();
    }
}
