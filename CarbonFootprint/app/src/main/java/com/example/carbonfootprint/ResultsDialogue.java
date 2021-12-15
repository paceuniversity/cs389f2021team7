package com.example.carbonfootprint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ResultsDialogue extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder resultsDialogueBuilder = new AlertDialog.Builder(getActivity());
        resultsDialogueBuilder.setTitle("Information")
                .setMessage("Footprint: A footprint changes size depending on your results and another footprint changes size depending on the average of your location or the app average. Your results are calculated by dividing the total of all three categories by your household size.\n\nBar Chart: Your results are displayed by category in the form of a bar chart. The color of the bar will change depending on the total of each category. If the color is close to green, then the emissions are low. If the color is close to red, then the emissions are high.\n\nPie Chart: Your results are displayed by category in the form of a pie chart. The color of the slices will change depending on the total of each category. If the color is close to green, then the emissions are low. If the color is close to red, then the emissions are high. You can tap the slices on the pie chart to display details and you can also spin the pie chart.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return resultsDialogueBuilder.create();
    }
}
