package com.yuxifu.everneeds.ui.categories.more;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.ui.categories.base.NavigationFragment;
import com.yuxifu.everneeds.ui.custom_views.ColorOptionsView;
import com.yuxifu.everneeds.ui.custom_views.ImageTitleImageListItemView;
import com.yuxifu.everneeds.ui.custom_views.ImageTitleSwitchListItemView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoreNavigationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoreNavigationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreNavigationFragment extends NavigationFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    LinearLayout linearLayout;
    private Switch nightModeSwitch;

    public MoreNavigationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreNavigationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreNavigationFragment newInstance(String param1, String param2) {
        MoreNavigationFragment fragment = new MoreNavigationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_navigation, container, false);

        //night mode controls
        ImageTitleSwitchListItemView nightModeItem = view.findViewById(R.id.night_mode_item);
        nightModeSwitch = nightModeItem.findViewById(R.id.on_off);
        nightModeSwitch.setChecked(getMainActivity().isNightModeOn());
        nightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getMainActivity().ResetNightModeOn(isChecked);
            }
        });
        nightModeItem.setOnClickListener(this);

        //other click handlers
        ColorOptionsView colorOptions = view.findViewById(R.id.color_option);
        ImageTitleImageListItemView statistics = view.findViewById(R.id.statistics_item);
        ImageTitleImageListItemView favorites = view.findViewById(R.id.favorites_item);
        ImageTitleImageListItemView settings = view.findViewById(R.id.settings_item);
        colorOptions.setOnClickListener(this);
        statistics.setOnClickListener(this);
        favorites.setOnClickListener(this);
        settings.setOnClickListener(this);

        //
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

    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.color_option:
                doColorOptions();
                break;
            case R.id.statistics_item:
                doStatistics();
                break;
            case R.id.favorites_item:
                doFavorites();
                break;
            case R.id.night_mode_item:
                doNightMode();
                break;
            case R.id.settings_item:
                doSettings();
                break;
            default:
                break;
        }
    }

    private void doColorOptions() {
        getMainActivity().showSnackbarShortNotImplementedIdMessage("Color option");
    }

    private void doStatistics() {
        getMainActivity().showSnackbarShortNotImplementedIdMessage("Statistics");
    }

    private void doFavorites() {
        getMainActivity().showSnackbarShortNotImplementedIdMessage("My Favorites");
    }

    private void doNightMode() {
        nightModeSwitch.setChecked(!nightModeSwitch.isChecked());
    }

    private void doSettings() {
        getMainActivity().showSnackbarShortNotImplementedIdMessage("Settings");
    }

}
