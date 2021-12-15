package com.example.carbonfootprint;

import static android.graphics.Color.rgb;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.io.Serializable;
import java.util.ArrayList;

public class ResultsPieChartFragment extends Fragment implements OnChartValueSelectedListener, Serializable {

    PieChart pieChart;
    PieDataSet pieDataSet;
    PieData pieData;
    ArrayList<PieEntry> pieChartCategoryResults;
    TextView pieChartTextView;
    float emissionsTotalAfterReduce;
    float estimatedWaste;
    float transportationTotal;
    int householdNumber;
    float demoTotalNumber;
    userInfo currentUser;
    int homeEnergyGreenValue;
    int homeEnergyRedValue;
    float highestHomeEnergyColorValue;
    float lowestHomeEnergyColorValue;
    float middleHomeEnergyColorValue;
    float homeEnergyMultiplier;
    int wasteGreenValue;
    int wasteRedValue;
    float highestWasteColorValue;
    float lowestWasteColorValue;
    float middleWasteColorValue;
    float wasteMultiplier;
    int transportationGreenValue;
    int transportationRedValue;
    float highestTransportationColorValue;
    float lowestTransportationColorValue;
    float middleTransportationColorValue;
    float transportationMultiplier;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static ResultsPieChartFragment newInstance(String param1, String param2) {
        ResultsPieChartFragment fragment = new ResultsPieChartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ResultsPieChartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results_pie_chart, container, false);

        currentUser = (userInfo) getActivity().getIntent().getSerializableExtra(CURRENT_USER_KEY);

        householdNumber = currentUser.getHouseholdNumber();
        estimatedWaste = (float) currentUser.getWasteTotal();
        emissionsTotalAfterReduce = (float) currentUser.getHomeEnergyTotal()/householdNumber;
        transportationTotal = (float) (currentUser.getTransportationTotal()/householdNumber);
        demoTotalNumber = (float) (emissionsTotalAfterReduce + estimatedWaste);

        highestHomeEnergyColorValue = (float) 12;
        lowestHomeEnergyColorValue = (float) 2;
        middleHomeEnergyColorValue = (float) 7;
        homeEnergyMultiplier = (255/((highestHomeEnergyColorValue - lowestHomeEnergyColorValue)/2));
        if (emissionsTotalAfterReduce < middleHomeEnergyColorValue) {
            homeEnergyGreenValue = 255;
            homeEnergyRedValue = (int) (255 - (((highestHomeEnergyColorValue - emissionsTotalAfterReduce)/2)*homeEnergyMultiplier));
        }
        else {
            homeEnergyRedValue = 255;
            if (emissionsTotalAfterReduce > highestHomeEnergyColorValue) {
                homeEnergyGreenValue = 0;
            }
            else if (emissionsTotalAfterReduce < lowestHomeEnergyColorValue) {
                homeEnergyGreenValue = 255;
                homeEnergyRedValue = 0;
            }
            else {
                homeEnergyGreenValue = (int) ((((highestHomeEnergyColorValue - emissionsTotalAfterReduce)/3)*homeEnergyMultiplier));
            }
        }

        highestWasteColorValue = (float) 0.35;
        lowestWasteColorValue = (float) 0.20;
        middleWasteColorValue = (float) 0.275;
        wasteMultiplier = (255/((highestWasteColorValue - lowestWasteColorValue)/2));
        if (estimatedWaste < middleWasteColorValue) {
            wasteGreenValue = 255;
            wasteRedValue = (int) (255 - (((highestWasteColorValue - estimatedWaste)/2)*wasteMultiplier));
        }
        else {
            wasteRedValue = 255;
            wasteGreenValue = (int) ((((highestWasteColorValue - estimatedWaste)/3)*wasteMultiplier));
        }

        highestTransportationColorValue = (float) 12;
        lowestTransportationColorValue = (float) 2;
        middleTransportationColorValue = (float) 7;
        transportationMultiplier = (255/((highestTransportationColorValue - lowestTransportationColorValue)/2));
        if (transportationTotal < middleTransportationColorValue) {
            transportationGreenValue = 255;
            transportationRedValue = (int) (255 - (((highestTransportationColorValue - transportationTotal)/2)*transportationMultiplier));
        }
        else {
            transportationRedValue = 255;
            if (transportationTotal > highestTransportationColorValue) {
                transportationGreenValue = 0;
            }
            else if (transportationTotal < lowestTransportationColorValue) {
                transportationGreenValue = 255;
                transportationRedValue = 0;
            }
            else {
                transportationGreenValue = (int) ((((highestTransportationColorValue - transportationTotal)/3)*transportationMultiplier));
            }
        }


        pieChart = view.findViewById(R.id.pieChart);
        pieChartTextView = view.findViewById(R.id.pieChartTextView);
        pieChartTextView.setText("Your household's total emissions average is " + String.format("%.2f", demoTotalNumber) + " metric tons");

        pieChartCategoryResults = new ArrayList<>();
        pieChartCategoryResults.add(new PieEntry(emissionsTotalAfterReduce, "Home Energy"));
        pieChartCategoryResults.add(new PieEntry(transportationTotal, "Transportation"));
        pieChartCategoryResults.add(new PieEntry(estimatedWaste, "Waste"));

        pieDataSet = new PieDataSet(pieChartCategoryResults, "");
        pieDataSet.setColors(rgb(homeEnergyRedValue,homeEnergyGreenValue,0), rgb(transportationRedValue,transportationGreenValue,0), rgb(wasteRedValue, wasteGreenValue, 0));
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setSliceSpace(5f);

        pieData = new PieData(pieDataSet);

        pieData.setValueFormatter(new PercentFormatter(pieChart));

        pieChart.setExtraOffsets(25f, 25f, 25f, 25f);
        pieChart.setEntryLabelTextSize(7.5f);
        pieChart.setUsePercentValues(true);
        pieChart.setOnChartValueSelectedListener(this);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Category Results (%)");
        pieChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

        pieChart.setData(pieData);
        pieChart.animate();
        pieChart.animateY(1000);

        return view;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
//        Toast.makeText(ResultsPieChart.this, e + "", Toast.LENGTH_LONG).show();

        if (e == pieDataSet.getEntryForIndex(0)) {
            pieChartTextView.setText("Your household's home energy emissions average is " + String.format("%.2f", emissionsTotalAfterReduce) + " metric tons");
        }
        else if (e == pieDataSet.getEntryForIndex(1)) {
            pieChartTextView.setText("Your household's transportation emissions average is " + String.format("%.2f", transportationTotal) + " metric tons");
        }
        else if (e == pieDataSet.getEntryForIndex(2)) {
            pieChartTextView.setText("Your household's waste emissions average is " + String.format("%.2f", estimatedWaste) + " metric tons");
        }

    }

    @Override
    public void onNothingSelected() {
        pieChartTextView.setText("Your household's total emissions average is " + String.format("%.2f", demoTotalNumber) + " metric tons");
    }

}