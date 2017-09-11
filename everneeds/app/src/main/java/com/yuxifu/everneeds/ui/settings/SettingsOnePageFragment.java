package com.yuxifu.everneeds.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.yuxifu.everneeds.R;

/**
 * Created by Yuxi on 9/11/17.
 */

public class SettingsOnePageFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_one_page);
        Preference pref = findPreference("about");
        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent about = new Intent(getActivity(), AboutActivity.class);
                startActivity(about);
                return true;
            }
        });
    }
}
