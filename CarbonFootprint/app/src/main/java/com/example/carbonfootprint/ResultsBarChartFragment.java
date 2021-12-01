package com.example.carbonfootprint;

import static android.graphics.Color.rgb;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ResultsBarChartFragment extends Fragment implements Serializable {
    BarChart barChart;
    ArrayList<BarEntry> categoryResults;
    BarDataSet barDataSet;
    BarData barData;
    userInfo currentUser;
    float emissionsTotalAfterReduce;
    float estimatedWaste;
    float transportationTotal;
    int householdNumber;
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

    public ResultsBarChartFragment() {
    }

    public static ResultsBarChartFragment newInstance(String param1, String param2) {
        ResultsBarChartFragment fragment = new ResultsBarChartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

        View view = inflater.inflate(R.layout.fragment_results_bar_chart, container, false);

        barChart = view.findViewById(R.id.barChart);

        currentUser = (userInfo) getActivity().getIntent().getSerializableExtra(CURRENT_USER_KEY);

        householdNumber = currentUser.getHouseholdNumber();
        estimatedWaste = (float) currentUser.getWasteTotal();
        emissionsTotalAfterReduce = (float) currentUser.getHomeEnergyTotal()/householdNumber;
        transportationTotal = (float) (currentUser.getTransportationTotal()/householdNumber);

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


//        wasteGreenValue = (int) (((highestWasteColorValue - estimatedWaste)/3)*wasteMultiplier);
//        wasteRedValue = (int) 255 - wasteGreenValue;
//
//
//        if (wasteRedValue < 255) {
//            wasteGreenValue = 255;
//        }


        String [] barString = {"Home Energy", "Transportation", "Waste"};

        LegendEntry legendEntryA = new LegendEntry();
        legendEntryA.label = "Home Energy";
        legendEntryA.formColor = rgb(homeEnergyRedValue,homeEnergyGreenValue,0);

        LegendEntry legendEntryB = new LegendEntry();
        legendEntryB.label = "Transportation";
        legendEntryB.formColor = rgb(transportationRedValue,transportationGreenValue,0);

        LegendEntry legendEntryC = new LegendEntry();
        legendEntryC.label = "Waste";
        legendEntryC.formColor = rgb(wasteRedValue, wasteGreenValue, 0);


        categoryResults = new ArrayList<>();
        categoryResults.add(new BarEntry(0, emissionsTotalAfterReduce));
        categoryResults.add(new BarEntry(1, transportationTotal));
        categoryResults.add(new BarEntry(2, estimatedWaste));
        barChart.getXAxis().setEnabled(true);

        barDataSet = new BarDataSet(categoryResults, "abc");
        barDataSet.setColors(rgb(homeEnergyRedValue,homeEnergyGreenValue,0), rgb(transportationRedValue,transportationGreenValue,0), rgb(wasteRedValue, wasteGreenValue, 0));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setCenterAxisLabels(false);
        barChart.getXAxis().setGranularity(1f);


        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barString));

        barChart.setTouchEnabled(false);
        barChart.setDragEnabled(false);
        barChart.setScaleEnabled(false);

        barData = new BarData(barDataSet);

        barChart.getLegend().setCustom((Arrays.asList(legendEntryA, legendEntryB, legendEntryC)));
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(true);
        barChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        barChart.animateY(1000);

        return view;
    }

}