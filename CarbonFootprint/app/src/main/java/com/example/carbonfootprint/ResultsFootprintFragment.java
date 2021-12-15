package com.example.carbonfootprint;

import static android.graphics.Color.rgb;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.location.Address;
import android.location.LocationRequest;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResultsFootprintFragment extends Fragment implements AdapterView.OnItemSelectedListener, Serializable {

    private static final int PERMISSIONS_FINE_LOCATION = 88;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    Button button;
    TextView textView;
    TextView textView2;
    TextView resultsText;
    TextView secondText;
    String countryCode;
    List<Address> addresses;
    String url;
    ImageView footprint;
    ImageView footprint2;
    String countryNameWB;
    String dateWB;
    String avgValueWB;
    String test;
    userInfo currentUser;
    float demoTotalNumber;
    TextView textView5;
    float footprintScale;
    float footprintScale2;
    float footprintScale3;
    int footprintScaleDivisor;
    TextView textView3;
    private DocumentReference documentReference = FirebaseFirestore.getInstance().document("CarbonFootprint/carbon_footprint_document");
    Map<String, Object> dataToSave;
    Object avgArray2;
    ArrayList<Double> avgArray1;
    ArrayList<String> resultsSpinnerArray;
    Button button3;
    ArrayList<Double> testArray3;
    double appTotal;
    double appAverageValue;
    double doubleResult;
    String stringResult;
    double appAvgValue;
    boolean avgSpinnerCheck = false;
    double emissionsTotalAfterReduce, estimatedWaste;
    int householdNumber;
    double transportationTotal;
    float scaleChangeInitial;
    float numberChange = 0;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ResultsFootprintFragment() {
    }


    public static ResultsFootprintFragment newInstance(String param1, String param2) {
        ResultsFootprintFragment fragment = new ResultsFootprintFragment();
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

        View view = inflater.inflate(R.layout.fragment_results_footprint, container, false);

        button3 = view.findViewById(R.id.button3);

        textView2 = view.findViewById(R.id.textView2);

        textView5 = view.findViewById(R.id.textView5);

        button = view.findViewById(R.id.homeenergybtn);
        textView = view.findViewById(R.id.textView);

        resultsText = view.findViewById(R.id.resultsText);
        secondText = view.findViewById(R.id.secondText);

        scaleChangeInitial = 0;

        currentUser = (userInfo) getActivity().getIntent().getSerializableExtra(CURRENT_USER_KEY);

//        Toast.makeText(getActivity(), currentUser.getLocationAvgBottom(), Toast.LENGTH_LONG).show();


        estimatedWaste = currentUser.getWasteTotal();
        emissionsTotalAfterReduce = currentUser.getHomeEnergyTotal();
        transportationTotal = currentUser.getTransportationTotal();


        householdNumber = currentUser.getHouseholdNumber();


        demoTotalNumber = (float) (((emissionsTotalAfterReduce + transportationTotal)/householdNumber) + estimatedWaste);

        footprintScaleDivisor = 21;

        if(!currentUser.isDisableFirebase()) {
            button3.setEnabled(true);
        }


        footprint2 = view.findViewById(R.id.footprint2);
        footprint2.setVisibility(View.INVISIBLE);

        footprint = view.findViewById(R.id.footprint);
        footprint.setVisibility(View.INVISIBLE);

        if(currentUser.isRetrieveCheck()) {
            button3.setEnabled(false);
        }


        if(householdNumber == 0) {
            footprint2.setImageResource(R.drawable.bigfoot);
            footprint2.setVisibility(View.VISIBLE);
            textView.setTextSize(14);
            DecimalFormat df2 = new DecimalFormat("0.00");

            resultsText.setText(df2.format(demoTotalNumber) + "");
            textView.setText("Bigfoot?");
            footprint2.setScaleX(1);
            footprint2.setScaleY(1);
            button3.setEnabled(false);
        }

        else if (demoTotalNumber > 21) {
            footprint2.setVisibility(View.VISIBLE);
            textView.setTextSize(10);
            startCountAnimation(resultsText, 0.00f, demoTotalNumber);
            textView.setText("Your household's total CO2 emissions average (metric tons per capita)\nNote: The footprint is at maximum size and cannot be enlarged any further.");
            footprint2.setScaleX(0);
            footprint2.setScaleY(0);
            ObjectAnimator scaleChangeX = ObjectAnimator.ofFloat(footprint2, "scaleX", 1);
            ObjectAnimator scaleChangeY = ObjectAnimator.ofFloat(footprint2, "scaleY", 1);
            scaleChangeX.setDuration(1000);
            scaleChangeY.setDuration(1000);
            AnimatorSet scaleChange = new AnimatorSet();
            scaleChange.play(scaleChangeX).with(scaleChangeY);
            scaleChange.start();
        }
        else {
            footprint2.setVisibility(View.VISIBLE);
            startCountAnimation(resultsText, 0.00f, demoTotalNumber);
            textView.setText("Your household's total CO2 emissions average (metric tons per capita)");
            footprintScale2 = (demoTotalNumber/footprintScaleDivisor);
            footprint2.setScaleX(0);
            footprint2.setScaleY(0);
            ObjectAnimator scaleChangeX = ObjectAnimator.ofFloat(footprint2, "scaleX", footprintScale2);
            ObjectAnimator scaleChangeY = ObjectAnimator.ofFloat(footprint2, "scaleY", footprintScale2);
            scaleChangeX.setDuration(1000);
            scaleChangeY.setDuration(1000);
            AnimatorSet scaleChange = new AnimatorSet();
            scaleChange.play(scaleChangeX).with(scaleChangeY);
            scaleChange.start();

//            footprint2.setScaleX(footprintScale2);
//            footprint2.setScaleY(footprintScale2);

        }


        resultsSpinnerArray = new ArrayList<String>();
        resultsSpinnerArray.add("Location Average");
        resultsSpinnerArray.add("App Average");

        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, resultsSpinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        appAvgValue = currentUser.getAppAvgValue();


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button3.setEnabled(false);
                currentUser.setDisableFirebase(true);
                dataToSave = new HashMap<String, Object>();
                avgArray1 = new ArrayList<Double>();
                avgArray2 = new ArrayList<Double>();
                avgArray2 = documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            avgArray2 = documentSnapshot.get("App Average Array");
                            avgArray1 = (ArrayList<Double>) avgArray2;
                            stringResult = String.format("%.2f", demoTotalNumber);
                            doubleResult = Double.valueOf(stringResult);
                            avgArray1.add(doubleResult);
                            dataToSave.put("App Average Array", avgArray1);
                            documentReference.set(dataToSave);

                            if (avgSpinnerCheck ==  true) {
                                appAvgValue = (appAverage(avgArray1));
                                startCountAnimation(secondText, numberChange, (float) appAvgValue);
                                numberChange = (float) appAvgValue;
                                textView2.setText("The app average emissions (metric tons per capita)");
                                footprintScale3 = (float) (appAvgValue/footprintScaleDivisor);
                                footprint.setScaleX(scaleChangeInitial);
                                footprint.setScaleY(scaleChangeInitial);
                                ObjectAnimator scaleChangeX = ObjectAnimator.ofFloat(footprint, "scaleX", footprintScale3);
                                ObjectAnimator scaleChangeY = ObjectAnimator.ofFloat(footprint, "scaleY", footprintScale3);
                                scaleChangeX.setDuration(1000);
                                scaleChangeY.setDuration(1000);
                                AnimatorSet scaleChange = new AnimatorSet();
                                scaleChange.play(scaleChangeX).with(scaleChangeY);
                                scaleChange.start();
                                scaleChangeInitial = footprintScale3;

//                        footprint.setScaleX(footprintScale3);
//                        footprint.setScaleY(footprintScale3);
                            }

                        }
                    }
                });
            }
        });





        return view;

    }
//    public void ResultstoDemoCalculator(View view) {
//        Intent intent = new Intent(getActivity(), DemoCalculatorActivity.class);
//        intent.putExtra(CURRENT_USER_KEY, currentUser);
//        startActivity(intent);
//    }
//
//    public void FirebaseAppAverage(View view) {
////        button3.setEnabled(false);
//        dataToSave = new HashMap<String, Object>();
//        avgArray1 = new ArrayList<Double>();
//        avgArray2 = new ArrayList<Double>();
//        avgArray2 = documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if (documentSnapshot.exists()) {
//                    avgArray2 = documentSnapshot.get("App Average Array");
//                    avgArray1 = (ArrayList<Double>) avgArray2;
//                    stringResult = String.format("%.2f", demoTotalNumber);
//                    doubleResult = Double.valueOf(stringResult);
//                    avgArray1.add(doubleResult);
//                    dataToSave.put("App Average Array", avgArray1);
//                    documentReference.set(dataToSave);
//
//                    if (avgSpinnerCheck ==  true) {
//                        appAvgValue = (appAverage(avgArray1));
//                        textView2.setText("The app average emissions (metric tons per capita) is " + String.format("%.2f", appAvgValue));
//                        footprintScale3 = (float) (appAvgValue/footprintScaleDivisor);
//                        footprint.setScaleX(scaleChangeInitial);
//                        footprint.setScaleY(scaleChangeInitial);
//                        ObjectAnimator scaleChangeX = ObjectAnimator.ofFloat(footprint, "scaleX", footprintScale3);
//                        ObjectAnimator scaleChangeY = ObjectAnimator.ofFloat(footprint, "scaleY", footprintScale3);
//                        scaleChangeX.setDuration(1000);
//                        scaleChangeY.setDuration(1000);
//                        AnimatorSet scaleChange = new AnimatorSet();
//                        scaleChange.play(scaleChangeX).with(scaleChangeY);
//                        scaleChange.start();
//                        scaleChangeInitial = footprintScale3;
//
////                        footprint.setScaleX(footprintScale3);
////                        footprint.setScaleY(footprintScale3);
//                    }
//
//                }
//            }
//        });
//    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String test2 = adapterView.getItemAtPosition(i).toString();

        if (currentUser.getAvgValueWB() != null) {


            if (test2 == resultsSpinnerArray.get(1)) {

                avgSpinnerCheck = true;
                textView2.setText("Loading...");
                textView5.setText("App Average");
                footprint.setVisibility(View.INVISIBLE);
                avgArray2 = new ArrayList<Double>();
                avgArray2 = documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {


                            avgArray2 = documentSnapshot.get("App Average Array");
                            avgArray1 = (ArrayList<Double>) avgArray2;


                            appAvgValue = appTotal / avgArray1.size();

                            appAvgValue = (appAverage(avgArray1));

                        }

                        footprintScale3 = (float) (appAvgValue / footprintScaleDivisor);
                        footprint.setVisibility(View.VISIBLE);
                        if (demoTotalNumber < (float) appAvgValue) {
                            resultsText.setTextColor(rgb(0, 128, 0));
                            secondText.setTextColor(rgb(255, 0, 0));
                        } else {
                            resultsText.setTextColor(rgb(255, 0, 0));
                            secondText.setTextColor(rgb(0, 128, 0));
                        }
                        startCountAnimation(secondText, numberChange, (float) appAvgValue);
                        numberChange = (float) appAvgValue;
                        textView2.setText("The app average emissions (metric tons per capita)");
                        textView5.setText("App Average");
                        footprint.setScaleX(scaleChangeInitial);
                        footprint.setScaleY(scaleChangeInitial);
                        ObjectAnimator scaleChangeX = ObjectAnimator.ofFloat(footprint, "scaleX", footprintScale3);
                        ObjectAnimator scaleChangeY = ObjectAnimator.ofFloat(footprint, "scaleY", footprintScale3);
                        scaleChangeX.setDuration(1000);
                        scaleChangeY.setDuration(1000);
                        AnimatorSet scaleChange = new AnimatorSet();
                        scaleChange.play(scaleChangeX).with(scaleChangeY);
                        scaleChange.start();
                        scaleChangeInitial = footprintScale3;
                    }
                });
            } else if (test2 == resultsSpinnerArray.get(0)) {
                avgSpinnerCheck = false;
                footprint.setVisibility(View.VISIBLE);
                if (demoTotalNumber < Float.parseFloat(currentUser.getAvgValueWB())) {
                    resultsText.setTextColor(rgb(0, 128, 0));
                    secondText.setTextColor(rgb(255, 0, 0));
                } else {
                    resultsText.setTextColor(rgb(255, 0, 0));
                    secondText.setTextColor(rgb(0, 128, 0));
                }
                if (Float.parseFloat(currentUser.getAvgValueWB()) > 21) {
                    startCountAnimation(secondText, numberChange, Float.parseFloat(currentUser.getAvgValueWB()));
                    numberChange = Float.parseFloat(currentUser.getAvgValueWB());
                    textView5.setText(currentUser.getLocationAvgTop());
                    textView2.setTextSize(10);
                    textView2.setText(currentUser.getLocationAvgBottom() + "\nNote: The footprint is at maximum size and cannot be enlarged any further.");
                    footprint.setScaleX(scaleChangeInitial);
                    footprint.setScaleY(scaleChangeInitial);
                    ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(footprint, "scaleX", 1);
                    ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(footprint, "scaleY", 1);
                    scaleUpX.setDuration(1000);
                    scaleUpY.setDuration(1000);
                    AnimatorSet scaleUp = new AnimatorSet();
                    scaleUp.play(scaleUpX).with(scaleUpY);
                    scaleUp.start();
                    scaleChangeInitial = 1;
                } else {
                    startCountAnimation(secondText, numberChange, Float.parseFloat(currentUser.getAvgValueWB()));
                    numberChange = Float.parseFloat(currentUser.getAvgValueWB());
                    textView5.setText(currentUser.getLocationAvgTop());
                    textView2.setText(currentUser.getLocationAvgBottom());
                    footprintScale = (Float.parseFloat(currentUser.getAvgValueWB()) / footprintScaleDivisor);
                    footprint.setScaleX(scaleChangeInitial);
                    footprint.setScaleY(scaleChangeInitial);
                    ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(footprint, "scaleX", footprintScale);
                    ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(footprint, "scaleY", footprintScale);
                    scaleUpX.setDuration(1000);
                    scaleUpY.setDuration(1000);
                    AnimatorSet scaleUp = new AnimatorSet();
                    scaleUp.play(scaleUpX).with(scaleUpY);
                    scaleUp.start();
                    scaleChangeInitial = footprintScale;
//                footprint.setScaleX(footprintScale);
//                footprint.setScaleY(footprintScale);
                }
            }
        }
        else {
            button3.setEnabled(false);
            textView2.setTextSize(10);
            textView2.setText("Please check your internet connection. Restart the app and try again. If your device has no location, please select a country manually in the settings.");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public double appAverage(ArrayList<Double> arrayList) {
        appTotal = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            appTotal = appTotal + arrayList.get(i);
        }
        appAverageValue = appTotal/arrayList.size();

        return appAverageValue;
    }

    public void startCountAnimation(TextView textView, float value1, float value2) {
        DecimalFormat df = new DecimalFormat("0.00");
        ValueAnimator animator = ValueAnimator.ofFloat(value1, value2);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(df.format(animation.getAnimatedValue()));
            }
        });
        animator.setDuration(1000);

        animator.start();
    }


}