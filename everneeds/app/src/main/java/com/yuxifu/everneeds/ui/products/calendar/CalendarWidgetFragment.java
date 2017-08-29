package com.yuxifu.everneeds.ui.products.calendar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.ui.products.base.BaseProductWidgetFragment;

public class CalendarWidgetFragment extends BaseProductWidgetFragment {
    private static final String ARG_PARAM_IS_COLLAPSED = "isCollapsed";

    private OnFragmentInteractionListener mListener;

    public CalendarWidgetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param isCollapsed
     * @return A new instance of fragment CalendarWidgetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarWidgetFragment newInstance(boolean isCollapsed) {
        CalendarWidgetFragment fragment = new CalendarWidgetFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM_IS_COLLAPSED, isCollapsed);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            collapsed = getArguments().getBoolean(ARG_PARAM_IS_COLLAPSED);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        TextView tv = getBodyView().findViewById(R.id.title);
        tv.setText(getTitleResId());
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    // settings from subclasses
    @Override
    protected int getBodyViewId(){
        return R.layout.widget_calendar;
    }

    @Override
    protected int getIconResId(){
        return R.drawable.icons8_calendar_96;
    }

    @Override
    protected int getTitleResId() {
        return R.string.product_widget_title_calendar;
    }
}
