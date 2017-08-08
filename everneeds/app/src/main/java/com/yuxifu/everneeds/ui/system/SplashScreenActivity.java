package com.yuxifu.everneeds.ui.system;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.yuxifu.everneeds.ui.bottom_navigation.HomeActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}