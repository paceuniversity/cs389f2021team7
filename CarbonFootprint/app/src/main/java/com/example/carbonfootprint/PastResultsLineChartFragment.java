package com.example.carbonfootprint;

import static com.example.carbonfootprint.DemoHomeActivity.databaseHelper;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class PastResultsLineChartFragment extends Fragment implements OnChartValueSelectedListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    LineChart lineChart;
    ArrayList<Entry> lineGraphPastResults;
    LineDataSet lineDataSet;
    ArrayList<ILineDataSet> lineDataSetArrayList;
    LineData lineData;
    userInfo currentUser;
    CardViewPastResults cardViewPastResultsLineChart;
    String dateAndTime;
    String lineGraphName;
    String lineGraphText;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    private String mParam1;
    private String mParam2;

    public PastResultsLineChartFragment() {
    }

    public static PastResultsLineChartFragment newInstance(String param1, String param2) {
        PastResultsLineChartFragment fragment = new PastResultsLineChartFragment();
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

        View view = inflater.inflate(R.layout.fragment_past_results_line_chart, container, false);

        currentUser = (userInfo) getActivity().getIntent().getSerializableExtra(CURRENT_USER_KEY);

        ArrayList<String> lineChartStringArrayList = new ArrayList<>();


        for (int i = 0; i < databaseHelper.getEverything().size(); i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa\nMMM dd, yyyy");
            Date date = null;
            try {
                date = sdf.parse(databaseHelper.getEverything().get(i).getCardBottomText());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sdf.applyPattern("hh:mm aa\nMM/dd/yy");
            dateAndTime = sdf.format(date);
            lineGraphName = databaseHelper.getEveryone().get(i).getName();
            lineGraphText = lineGraphName + "\n" + dateAndTime;
            lineChartStringArrayList.add(lineGraphText);
        }

        String [] lineChartString = lineChartStringArrayList.toArray(new String[0]);

//        Toast.makeText(getActivity(), PastResultsListFragment.databaseHelper.getEverything().get(0).getCardBottomText(), Toast.LENGTH_LONG).show();



        lineChart = view.findViewById(R.id.lineChart);
        lineGraphPastResults = new ArrayList<>();
//        lineGraphPastResults.add(new Entry(0, 10));
//        lineGraphPastResults.add(new Entry(1, 7));
//        lineGraphPastResults.add(new Entry(2, 20));
//        lineGraphPastResults.add(new Entry(3, 13));
//        lineGraphPastResults.add(new Entry(4, 8));

        for (int i = 0; i < lineChartString.length; i++) {
            lineGraphPastResults.add(new Entry(i, Float.valueOf(databaseHelper.getEveryone().get(i).getTotal())));
        }


        lineDataSet = new LineDataSet(lineGraphPastResults, "Past Results");
        lineDataSet.setCircleColor(Color.BLACK);
        lineDataSet.setColor(Color.GRAY);
        lineDataSet.setCircleHoleColor(Color.BLACK);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillColor(Color.RED);
        lineDataSet.setValueTextSize(10f);
        lineDataSetArrayList = new ArrayList<>();
        lineDataSetArrayList.add(lineDataSet);
        lineData = new LineData(lineDataSetArrayList);
        lineChart.getXAxis().setGranularity(1f);
        lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(lineChartString));
        lineChart.getLegend().setEnabled(false);
        lineChart.getDescription().setEnabled(false);
//        lineChart.getXAxis().setLabelRotationAngle(-30f);
        lineChart.setXAxisRenderer(new CustomXAxisRenderer(lineChart.getViewPortHandler(), lineChart.getXAxis(), lineChart.getTransformer(YAxis.AxisDependency.LEFT), lineChartString.length));
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.setExtraBottomOffset(50);
        lineChart.setOnChartValueSelectedListener(this);
        lineChart.setMaxHighlightDistance(50);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setData(lineData);
        lineChart.animateX(0);
        return view;
    }


    @Override
    public void onResume(){
        super.onResume();

        ArrayList<String> lineChartStringArrayList = new ArrayList<>();

        for (int i = 0; i < databaseHelper.getEverything().size(); i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa\nMMM dd, yyyy");
            Date date = null;
            try {
                date = sdf.parse(databaseHelper.getEverything().get(i).getCardBottomText());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sdf.applyPattern("hh:mm aa\nMM/dd/yy");
            dateAndTime = sdf.format(date);
            lineGraphName = databaseHelper.getEveryone().get(i).getName();
            lineGraphText = lineGraphName + "\n" + dateAndTime;
            lineChartStringArrayList.add(lineGraphText);
        }

        String [] lineChartString = lineChartStringArrayList.toArray(new String[0]);

        lineGraphPastResults = new ArrayList<>();

        for (int i = 0; i < lineChartString.length; i++) {
            lineGraphPastResults.add(new Entry(i, Float.valueOf(databaseHelper.getEveryone().get(i).getTotal())));
        }
        lineDataSet = new LineDataSet(lineGraphPastResults, "Past Results");
        lineDataSet.setCircleColor(Color.BLACK);
        lineDataSet.setColor(Color.GRAY);
        lineDataSet.setCircleHoleColor(Color.BLACK);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillColor(Color.RED);
        lineDataSet.setValueTextSize(10f);
        lineDataSetArrayList = new ArrayList<>();
        lineDataSetArrayList.add(lineDataSet);
        lineData = new LineData(lineDataSetArrayList);

        lineChart.getXAxis().setGranularity(1f);
        lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(lineChartString));
        lineChart.getLegend().setEnabled(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.setXAxisRenderer(new CustomXAxisRenderer(lineChart.getViewPortHandler(), lineChart.getXAxis(), lineChart.getTransformer(YAxis.AxisDependency.LEFT), lineChartString.length));
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.setExtraBottomOffset(50);
        lineChart.setOnChartValueSelectedListener(this);
        lineChart.setMaxHighlightDistance(50);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setData(lineData);
        lineChart.animateX(0);



    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {


        for (int i = 0; i < databaseHelper.getEverything().size(); i++) {
            if (e == lineDataSet.getEntryForIndex(i)) {
                pastResultsDetails(i);
//                Toast.makeText(getActivity(), "clicked " + i, Toast.LENGTH_LONG).show();
            }
        }

//        if (e == lineDataSet.getEntryForIndex(0)) {
//            Toast.makeText(getActivity(), "clicked 1", Toast.LENGTH_LONG).show();
//        }
//        else if (e == lineDataSet.getEntryForIndex(1)) {
//            Toast.makeText(getActivity(), "clicked 2", Toast.LENGTH_LONG).show();
//        }
//        else if (e == lineDataSet.getEntryForIndex(2)) {
//            Toast.makeText(getActivity(), "clicked 3", Toast.LENGTH_LONG).show();
//        }
//        else if (e == lineDataSet.getEntryForIndex(3)) {
//            Toast.makeText(getActivity(), "clicked 4", Toast.LENGTH_LONG).show();
//        }
//        else if (e == lineDataSet.getEntryForIndex(4)) {
//            Toast.makeText(getActivity(), "clicked 5", Toast.LENGTH_LONG).show();
//        }
//        else if (e == lineDataSet.getEntryForIndex(5)) {
//            Toast.makeText(getActivity(), "clicked 6", Toast.LENGTH_LONG).show();
//        }
//        else if (e == lineDataSet.getEntryForIndex(6)) {
//            Toast.makeText(getActivity(), "clicked 7", Toast.LENGTH_LONG).show();
//        }
//        else if (e == lineDataSet.getEntryForIndex(7)) {
//            Toast.makeText(getActivity(), "clicked 8", Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public void onNothingSelected() {

    }

    public void pastResultsDetails(int position) {
        currentUser.setPastResultsName(databaseHelper.getEveryone().get(position).getName());
        currentUser.setPastResultsTime(databaseHelper.getEveryone().get(position).getTime());
        currentUser.setPastResultsLocation(databaseHelper.getEveryone().get(position).getLocation());
        currentUser.setPastResultsHousehold(databaseHelper.getEveryone().get(position).getHousehold());
        currentUser.setPastResultsHomeEnergy(databaseHelper.getEveryone().get(position).getHomeEnergy());
        currentUser.setPastResultsTransportation(databaseHelper.getEveryone().get(position).getTransportation());
        currentUser.setPastResultsWaste(databaseHelper.getEveryone().get(position).getWaste());
        currentUser.setPastResultsTotal(databaseHelper.getEveryone().get(position).getTotal());
        currentUser.setPastResultsLocationAvg(databaseHelper.getEveryone().get(position).getTotal());


        Intent intent = new Intent(getActivity(), PastResultsDetailsActivity.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
    }
}