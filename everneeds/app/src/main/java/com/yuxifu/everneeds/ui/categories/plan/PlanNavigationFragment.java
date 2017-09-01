package com.yuxifu.everneeds.ui.categories.plan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuxifu.everneeds.R;
import com.yuxifu.everneeds.data.model.Product;
import com.yuxifu.everneeds.data.model.ProductCategory;
import com.yuxifu.everneeds.ui.categories.base.ProductNavigationFragment;
import com.yuxifu.everneeds.ui.categories.base.ProductUI;
import com.yuxifu.everneeds.ui.products.calendar.CalendarWidgetFragment;
import com.yuxifu.everneeds.ui.products.todolist.TodoWidgetFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlanNavigationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlanNavigationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanNavigationFragment extends ProductNavigationFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PlanNavigationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlanNavigationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlanNavigationFragment newInstance(String param1, String param2) {
        PlanNavigationFragment fragment = new PlanNavigationFragment();
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
        return super.onCreateView(inflater, container, savedInstanceState);
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
    protected List<ProductUI> getProducts() {
        List<ProductUI> productUis = new ArrayList<ProductUI>();

        //products
        Product calendar = new Product("Calendar", ProductCategory.Plan, "");
        Product todoList = new Product("Todo List", ProductCategory.Plan, "");
   /*     Product wishList = new Product("Wish List", ProductCategory.Plan, "");
        Product dates = new Product("Important Dates", ProductCategory.Plan, "");
        Product weight = new Product("Weight", ProductCategory.Plan, "");
        Product notepad = new Product("Notes", ProductCategory.Plan, "");
*/
        //UIs
        productUis.add(new ProductUI(calendar, R.drawable.icons8_calendar_96,
                new CalendarWidgetFragment(),
                R.id.widget_container_calendar, null));

        productUis.add(new ProductUI(todoList, R.drawable.icons8_to_do_96,
                new TodoWidgetFragment(),
                R.id.widget_container_todo, null));
/*
        productUis.add(new ProductUI(wishList, R.drawable.wish_list_color,
                SampleProductCompactFragment.newInstance(wishList.getTitle(), wishList.getType().toString()),
                R.id.widget_container_wish, null));
*/
        /*productUis.add(new ProductUI(dates, R.drawable.icons8_wedding_day_96,
                SampleProductCompactFragment.newInstance(dates.getTitle(), dates.getType().toString()),
                R.id.widget_container_dates, null));

        productUis.add(new ProductUI(weight, R.drawable.icons8_scale_96,
                SampleProductCompactFragment.newInstance(weight.getTitle(), weight.getType().toString()),
                R.id.widget_container_weight, null));

        productUis.add(new ProductUI(notepad, R.drawable.icons8_note_96,
                SampleProductCompactFragment.newInstance(notepad.getTitle(), notepad.getType().toString()),
                R.id.widget_container_notepad, null));*/

        //
        return productUis;
    }
}
