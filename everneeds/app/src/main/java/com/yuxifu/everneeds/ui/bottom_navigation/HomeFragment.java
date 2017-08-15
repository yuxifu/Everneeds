package com.yuxifu.everneeds.ui.bottom_navigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.data._exp.Cheeses;
import com.yuxifu.everneeds.ui._exp.CheeseRecyclerViewAdapter;
import com.yuxifu.everneeds.util.CollectionHelper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseNavigationFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        final View rootView = super.onCreateView(inflater, container, savedInstanceState);

        // RecyclerView
        RecyclerView rv = rootView.findViewById(R.id.recycler_view);
        setupRecyclerView(rv);

        // close image button
        ImageView iv = rootView.findViewById(R.id.close_what_s_new);
        iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CardView cv = rootView.findViewById(R.id.home_welcome_card);
                cv.setVisibility(View.GONE);
            }
        });

        return rootView;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new CheeseRecyclerViewAdapter(getActivity(),
                CollectionHelper.getRandomSublist(Cheeses.sCheeseStrings, 50)));
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

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected int getNavigationViewMenuId() {
        return R.menu.drawer_view_home;
    }

    @Override
    protected int getOptionsMenuId() {
        return R.menu.menu_home_actions;
    }

    @Override
    protected ViewPager getViewPager() {
        return null;
    }

    @Override
    protected String getFragmentTitle() {
        return getActivity().getString(R.string.bottombar_home_title);
    }

    @Override
    protected boolean useDrawerNavigation() {
        return true;
    }

    @Override
    protected boolean useSlidingTabs() {
        return false;
    }

    @Override
    protected boolean useOptionsMenu() {
        return true;
    }

    @Override
    public void doPrepareOptionsMenu(Menu menu){
        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
                menu.findItem(R.id.menu_night_mode_system).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_AUTO:
                menu.findItem(R.id.menu_night_mode_auto).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                menu.findItem(R.id.menu_night_mode_night).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                menu.findItem(R.id.menu_night_mode_day).setChecked(true);
                break;
        }
    }

    @Override
    public boolean doOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_night_mode_system:
                getMainActivity().setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                return true;
            case R.id.menu_night_mode_day:
                getMainActivity().setNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                return true;
            case R.id.menu_night_mode_night:
                getMainActivity().setNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                return true;
            case R.id.menu_night_mode_auto:
                getMainActivity().setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                return true;
        }
        return false;
    }
}

