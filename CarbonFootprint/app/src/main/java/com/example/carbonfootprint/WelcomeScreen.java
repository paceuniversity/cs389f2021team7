package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import java.io.Serializable;
import android.os.Bundle;

public class WelcomeScreen extends AppCompatActivity implements Serializable{

    Button beginbtn; //declaring the begin button
    userInfo currentUser;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        beginbtn = findViewById(R.id.button);

        //when the button is clicked, the user is brought to DemoHomeActivity
        beginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeScreen.this, DemoHomeActivity.class);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }
        });
    }

}