package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity implements OnItemSelectedListener, Serializable {
    public static final String CURRENT_USER_KEY = "CurrentUserKey";

    String CC;
    ArrayList<String> xmlcountryname;
    ArrayList<String> xmlcountrycode;
    int indexCC;
    userInfo currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        parseXML();

        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, xmlcountryname);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);

        spinner2.setOnItemSelectedListener(this);

//        currentUser = (userInfo) getIntent().getSerializableExtra(DemoHomeActivity.CURRENT_USER_KEY);

        currentUser = (userInfo) getIntent().getSerializableExtra(CURRENT_USER_KEY);

        currentUser.setCountryCode("JP");

    }

    public void parseXML() {
        XmlPullParserFactory xmlPullParserFactory;

        try {
            xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = xmlPullParserFactory.newPullParser();
            InputStream inputStream = getAssets().open("countries.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);

            xmlcountryname = new ArrayList<>();
            xmlcountrycode = new ArrayList<>();

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String xmlname = null;

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        xmlname = parser.getName();

                        if ("code".equals(xmlname)) {
                            xmlcountrycode.add(parser.nextText());
                        } else if ("name".equals(xmlname)) {
                            xmlcountryname.add(parser.nextText());
                        }
                        break;
                }

                eventType = parser.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        CC = adapterView.getItemAtPosition(i).toString();
        Button button5 = findViewById(R.id.button5);

        if(CC == xmlcountryname.get(0)) {
            button5.setEnabled(false);
        }
        else {
            button5.setEnabled(true);
            currentUser.setLocationCheck(true);
            indexCC = xmlcountryname.indexOf(CC);
            currentUser.setCountryCode(xmlcountrycode.get(indexCC));
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void LocationToHome (View view) {
        Intent intent = new Intent(this, DemoHomeActivity.class);
        intent.putExtra(CURRENT_USER_KEY, currentUser);
        startActivity(intent);
        }
}