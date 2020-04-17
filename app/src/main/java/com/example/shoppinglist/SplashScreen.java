package com.example.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_activity);


        // Launch main activity post delayed
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // This method will execute once the timer is over
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                finish();

            }
        }, 1000);

    }


}
