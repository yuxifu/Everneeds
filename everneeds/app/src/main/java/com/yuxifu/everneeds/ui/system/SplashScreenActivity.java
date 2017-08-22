package com.yuxifu.everneeds.ui.system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yuxifu.everneeds.ui.bottom_navigation.Main2Activity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
        finish();
    }
}