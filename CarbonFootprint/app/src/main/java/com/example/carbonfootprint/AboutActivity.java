package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    TextView aboutTextView;
    ImageView aboutInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_about);

        aboutInfo = findViewById(R.id.aboutInfo);
        aboutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutDialog();
            }
        });

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

        TextView linkTextView3 = findViewById(R.id.WBsourceLink);
        linkTextView3.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView3.setLinkTextColor(Color.BLACK);

        TextView linkTextView4 = findViewById(R.id.MaxMindsourceLink);
        linkTextView4.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView4.setLinkTextColor(Color.BLACK);

        TextView linkTextView5 = findViewById(R.id.MPAndroidChartsourceLink);
        linkTextView5.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView5.setLinkTextColor(Color.BLACK);
    }

    public void openAboutDialog() {
        AboutDialogue aboutDialogue = new AboutDialogue();
        aboutDialogue.show(getSupportFragmentManager(), "About Dialogue");
    }
}
