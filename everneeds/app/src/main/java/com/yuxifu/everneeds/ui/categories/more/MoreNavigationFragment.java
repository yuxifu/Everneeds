package com.yuxifu.everneeds.ui.categories.more;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.system.CategoryMoreItem;
import com.yuxifu.everneeds.system.CategoryMoreItems;
import com.yuxifu.everneeds.ui.categories.base.NavigationFragment;
import com.yuxifu.everneeds.ui.custom_views.ColorOptionsView;
import com.yuxifu.everneeds.ui.custom_views.Divider;
import com.yuxifu.everneeds.ui.custom_views.ImageTitleImageListItemView;

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

    LinearLayout linearLayout;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

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

        //get container layout
        linearLayout = view.findViewById(R.id.item_container);

        //insert a divider at the top
        linearLayout.addView(Divider.getFullSpanDividerThick(getContext()), 0);

        //top view
        ColorOptionsView cov = view.findViewById(R.id.color_option);
        cov.setOnClickListener(this);

        //add more items
        for (CategoryMoreItem item : CategoryMoreItems.getCategoryMoreItems()) {
            //divider
            if (item.isNewSection()) {   //wide divider
                linearLayout.addView(Divider.getFullSpanDividerThick(getContext()));
            } else { //thin divider
                linearLayout.addView(Divider.getDividerThin(getContext(), 64));
            }

            //item
            ImageTitleImageListItemView itemView = new ImageTitleImageListItemView(linearLayout.getContext());
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                    (int) getContext().getResources().getDimension(R.dimen.image_title_image_list_item_height));
            itemView.setLayoutParams(layoutParams);
            itemView.setId(item.getViewId());
            itemView.setTitle(item.getTitleId());
            itemView.setFirstImageId(item.getFirstImageId());
            if (item.getSecondImageId() != null) {
                itemView.setSecondImage(true, item.getSecondImageId());
            }
            itemView.setClickable(true);
            itemView.setFocusable(true);
            TypedValue outValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground,
                    outValue, true)) {
                itemView.setBackgroundResource(outValue.resourceId);
            }
            itemView.setOnClickListener(this);

            //
            linearLayout.addView(itemView);
        }

        //
        updateNightModeItemTitle();

        //add a divider at the bottom
        linearLayout.addView(Divider.getFullSpanDividerThick(getContext()));

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
            case R.id.list_item_night_mode:
                setNightMode();
                break;
            case R.id.color_option:
                Toast.makeText(getActivity(), "Color Option", Toast.LENGTH_SHORT).show();
                break;
            default:
                CategoryMoreItem item = CategoryMoreItems.getById(view.getId());
                if (item != null) {
                    String text = getContext().getResources().getString(item.getTitleId());
                    Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private int getCurrentNightModeIndex() {
        int currentModeIndex = 0;
        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
                currentModeIndex = 0;
                break;
            case AppCompatDelegate.MODE_NIGHT_AUTO:
                currentModeIndex = 1;
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                currentModeIndex = 2;
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                currentModeIndex = 3;
                break;
        }
        return currentModeIndex;
    }

    private int getCurrentNightModeTitleResId() {
        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
                return R.string.night_mode_follow_system_title;
            case AppCompatDelegate.MODE_NIGHT_AUTO:
                return R.string.night_mode_auto_title;
            case AppCompatDelegate.MODE_NIGHT_NO:
                return R.string.night_mode_day_title;
            case AppCompatDelegate.MODE_NIGHT_YES:
                return R.string.night_mode_night_title;
        }
        return R.string.night_mode_auto_title;
    }

    private void updateNightModeItemTitle() {
        int titleResId = getCurrentNightModeTitleResId();
        CharSequence title = getMainActivity().getResources().getString(R.string.night_mode_dialog_title) + ": "
                + getMainActivity().getResources().getString(titleResId);
        ImageTitleImageListItemView itemView = linearLayout.findViewById(R.id.list_item_night_mode);
        if(itemView!=null) {
            itemView.setTitle(title);
        }
    }

    private void setNightMode() {
        int currentModeIndex = getCurrentNightModeIndex();
        new MaterialDialog.Builder(getMainActivity())
                .title(R.string.night_mode_dialog_title)
                .items(R.array.night_mode)
                .itemsCallbackSingleChoice(
                        currentModeIndex,
                        (dialog, view, which, text) -> {
                            int nightMode = 0;
                            switch (which) {
                                case 0:
                                    nightMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                                    break;
                                case 1:
                                    nightMode = AppCompatDelegate.MODE_NIGHT_AUTO;
                                    break;
                                case 2:
                                    nightMode = AppCompatDelegate.MODE_NIGHT_NO;
                                    break;
                                case 3:
                                    nightMode = AppCompatDelegate.MODE_NIGHT_YES;
                                    break;
                            }
                            getMainActivity().setNightMode(nightMode);
                            updateNightModeItemTitle();
                            return true; // allow selection
                        })
                .positiveText(R.string.dialog_select)
                .show();
    }


}
