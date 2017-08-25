package com.yuxifu.everneeds.ui.products.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuxifu.everneeds.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SampleProductCompactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SampleProductCompactFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_TITLE = "title";
    private static final String ARG_PARAM_TYPE = "type";

    // TODO: Rename and change types of parameters
    private String mTitle;
    private String mType;


    public SampleProductCompactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter title.
     * @param type Parameter type.
     * @return A new instance of fragment SampleProductCompactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SampleProductCompactFragment newInstance(String title, String type) {
        SampleProductCompactFragment fragment = new SampleProductCompactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_TITLE, title);
        args.putString(ARG_PARAM_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_PARAM_TITLE);
            mType = getArguments().getString(ARG_PARAM_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sample_product_compact, container, false);
        TextView tv = v.findViewById(R.id.welcome_message);
        tv.setText(mTitle + " - " + mType);
        return v;
    }

}
