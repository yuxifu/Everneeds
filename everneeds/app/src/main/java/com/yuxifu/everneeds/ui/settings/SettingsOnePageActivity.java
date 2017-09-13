package com.yuxifu.everneeds.ui.settings;

import android.os.Bundle;

import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.ui.base.HomeAsUpFragmentActivity;

public class SettingsOnePageActivity extends HomeAsUpFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new SettingsOnePageFragment()).commit();
    }
}
