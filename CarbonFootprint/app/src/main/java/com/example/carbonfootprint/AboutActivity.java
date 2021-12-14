package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    TextView aboutTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aboutTextView = findViewById(R.id.aboutTextView);
        setupHyperlink();
    }
    private void setupHyperlink()
    {
        TextView linkTextView = findViewById(R.id.sourceLink);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView.setLinkTextColor(Color.BLACK);

        TextView linkTextView2 = findViewById(R.id.gitLink);
        linkTextView2.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView2.setLinkTextColor(Color.BLACK);
    }

}
