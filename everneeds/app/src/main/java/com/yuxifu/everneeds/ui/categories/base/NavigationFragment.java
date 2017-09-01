package com.yuxifu.everneeds.ui.categories.base;


import android.app.Activity;
import android.support.v4.app.Fragment;

import com.yuxifu.everneeds.ui.main.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment {

    public NavigationFragment() {
        // Required empty public constructor
    }

    public MainActivity getMainActivity() {
        Activity activity = getActivity();
        return (activity instanceof MainActivity) ? (MainActivity) activity : null;
    }

}
