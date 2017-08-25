package com.yuxifu.everneeds.ui.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.ui.adapters.HomeSectionedRecyclerViewAdapter;
import com.yuxifu.everneeds.util.ResourceHelper;

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

    boolean mShowEmptySections = false;
    boolean mShowFooters = true;
    HomeSectionedRecyclerViewAdapter mRecyclerViewAdapter;

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

        // hide home welcome card view
        CardView cv = rootView.findViewById(R.id.home_welcome_card);
        cv.setVisibility(View.GONE);
        if (cv.getVisibility() == View.VISIBLE) {
            ImageView iv = rootView.findViewById(R.id.close_what_s_new);
            iv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CardView cv = rootView.findViewById(R.id.home_welcome_card);
                    cv.setVisibility(View.GONE);
                }
            });
        }

        return rootView;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        //recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        //recyclerView.setAdapter(new CheeseRecyclerViewAdapter(getActivity(),
        //        CollectionHelper.getRandomSublist(Cheeses.sCheeseStrings, 50)));
        mRecyclerViewAdapter = new HomeSectionedRecyclerViewAdapter();
        GridLayoutManager manager =
                new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.grid_span));
        recyclerView.setLayoutManager(manager);
        mRecyclerViewAdapter.setLayoutManager(manager);
        mRecyclerViewAdapter.shouldShowHeadersForEmptySections(mShowEmptySections);
        mRecyclerViewAdapter.shouldShowFooters(mShowFooters);
        mRecyclerViewAdapter.expandAllSections();
        recyclerView.setAdapter(mRecyclerViewAdapter);
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
        return R.menu.nav_home_options;
    }

    @Override
    protected ViewPager getViewPager() {
        return null;
    }

    @Override
    protected String getFragmentTitle() {
        return getActivity().getString(R.string.nav_home_title);
    }

    @Override
    protected boolean useDrawerNavigation() {
        return false;
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
        menu.findItem(R.id.home_options_show_empty_sections).setChecked(mShowEmptySections);
        menu.findItem(R.id.home_options_show_footers).setChecked(mShowFooters);
        super.doPrepareOptionsMenu(menu);
    }

    @Override
    public boolean doOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.home_options_filter:
                showFilteringPopUpMenu();
                return true;
            case R.id.home_options_search:
                //not implemented
                getMainActivity().showSnackbarShortNotImplementedIdMessage(R.id.home_options_search);
                return false;
            case R.id.home_options_note:
                //not implemented
                getMainActivity().showSnackbarShortNotImplementedIdMessage(R.id.home_options_note);
                return false;
            case R.id.home_options_expand_all_sections:
                mRecyclerViewAdapter.expandAllSections();
                return true;
            case R.id.home_options_collapse_all_sections:
                mRecyclerViewAdapter.collapseAllSections();
                mRecyclerViewAdapter.shouldShowHeadersForEmptySections(mShowEmptySections);
                return true;
            case R.id.home_options_show_empty_sections:
                mShowEmptySections = !mShowEmptySections;
                mRecyclerViewAdapter.shouldShowHeadersForEmptySections(mShowEmptySections);
                item.setChecked(mShowEmptySections);
                return true;
            case R.id.home_options_show_footers:
                mShowFooters = !mShowFooters;
                mRecyclerViewAdapter.shouldShowFooters(mShowFooters);
                item.setChecked(mShowFooters);
                return true;

            default:
                getMainActivity().showSnackbarShortMessage(
                        ResourceHelper.idToName(getMainActivity(), item.getItemId()));

        }
        return false;
    }

    public void showFilteringPopUpMenu() {
        PopupMenu popup = new PopupMenu(getContext(), getActivity().findViewById(R.id.home_options_filter));
        popup.getMenuInflater().inflate(R.menu.nav_home_filters, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_filters_all:
                        getMainActivity().showSnackbarShortNotImplementedIdMessage(R.id.home_filters_all);
                        return false;
                    case R.id.home_filters_reminders:
                        getMainActivity().showSnackbarShortNotImplementedIdMessage(R.id.home_filters_reminders);
                        return false;
                    case R.id.home_filters_calendar:
                        getMainActivity().showSnackbarShortNotImplementedIdMessage(R.id.home_filters_calendar);
                        return false;
                    case R.id.home_filters_todo:
                        getMainActivity().showSnackbarShortNotImplementedIdMessage(R.id.home_filters_todo);
                        return false;
                    case R.id.home_filters_important_dates:
                        getMainActivity().showSnackbarShortNotImplementedIdMessage(R.id.home_filters_important_dates);
                        return false;
                }
                return false;
            }
        });

        popup.show();
    }


}

