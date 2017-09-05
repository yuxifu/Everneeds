package com.yuxifu.everneeds.ui.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.yuxifu.everneeds.R;

public class ImageTitleSwitchListItemView extends LinearLayout {

    private ImageView firstImage;

    public ImageView getFirstImage() {
        return firstImage;
    }

    public TextView getTitle() {
        return title;
    }

    private TextView title;

    public Switch getSwitch_on_off() {
        return switch_on_off;
    }

    private Switch switch_on_off;

    public ImageTitleSwitchListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //set LinearLayout properties
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        //inflate layout
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null) {
            return;
        }
        View view = inflater.inflate(R.layout.view_image_title_switch_list_item, this, true);

        //get views
        title = view.findViewById(R.id.title);
        firstImage = view.findViewById(R.id.first_image);
        switch_on_off = view.findViewById(R.id.on_off);

        //apply attrs
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.ImageTitleSwitchListItemView, 0, 0);
            String titleText = a.getString(R.styleable.ImageTitleSwitchListItemView_titleText);
            Integer firstImageResId = a.getResourceId(R.styleable.ImageTitleSwitchListItemView_firstImageSrc,
                    R.drawable.icons8_under_construction_96);
            Boolean switchOn = a.getBoolean(R.styleable.ImageTitleSwitchListItemView_switchOn,
                    false);
            a.recycle();
            setTitle(titleText);
            setFirstImageId(firstImageResId);
            setSwitchOn(switchOn);
        }

        //other attrs: set in the container
        /*setClickable(true);
        setFocusable(true);
        TypedValue outValue = new TypedValue();
        if (getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground,
                outValue, true)) {
            setBackgroundResource(outValue.resourceId);
        }*/
    }

    public ImageTitleSwitchListItemView(Context context) {
        this(context, null);
    }

    public void setTitle(CharSequence title) {
        this.title.setText(title);
    }

    public void setTitle(int resId) {
        this.title.setText(resId);
    }

    public void setFirstImageId(int resId) {
        firstImage.setImageResource(resId);
    }

    public void setSwitchOn(boolean on) {
        switch_on_off.setChecked(on);
    }
}
