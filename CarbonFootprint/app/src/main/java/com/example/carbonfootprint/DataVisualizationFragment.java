package com.example.carbonfootprint;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.Serializable;


public class DataVisualizationFragment extends Fragment implements Serializable {
    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_data_visualization, container, false);

//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading...");
//        progressDialog.setCancelable(false);

        webView = view.findViewById(R.id.webView);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/MapDataVisualization.html");
//        if (webView.getProgress() < 100) {
//            progressDialog.show();
//        }
//        else {
//            progressDialog.dismiss();
//        }


        return view;
    }

}