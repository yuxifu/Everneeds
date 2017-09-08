package com.yuxifu.everneeds.ui.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.yuxifu.everneeds.ui.main.MainActivity;

/**
 * Created by Yuxi on 9/8/17.
 */

public abstract class ContextFragment extends Fragment {
    protected Context context;
    protected Activity activity;
    protected MainActivity mainActivity;

    public ContextFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        }
    }

    @Override
    public void onDetach() {
        if (context != null) context = null;
        if (activity != null) activity = null;
        if (mainActivity != null) mainActivity = null;
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        if (context != null) context = null;
        if (activity != null) activity = null;
        if (mainActivity != null) mainActivity = null;
        super.onDestroy();
    }
}
