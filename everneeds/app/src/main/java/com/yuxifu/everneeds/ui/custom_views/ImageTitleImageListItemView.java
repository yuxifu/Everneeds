package com.yuxifu.everneeds.ui.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuxifu.everneeds.R;

public class ImageTitleImageListItemView extends LinearLayout {

    private ImageView firstImage;

    private ImageView secondImage;

    public ImageView getFirstImage() {
        return firstImage;
    }

    public ImageView getSecondImage() {
        return secondImage;
    }

    public TextView getTitle() {
        return title;
    }

    private TextView title;

    public ImageTitleImageListItemView(Context context, AttributeSet attrs) {
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
        View view = inflater.inflate(R.layout.view_image_title_image_list_item, this, true);

        //get views
        title = view.findViewById(R.id.title);
        firstImage = view.findViewById(R.id.first_image);
        secondImage = view.findViewById(R.id.second_iamge);

        //apply attrs
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.ImageTitleImageListItemView, 0, 0);
            String titleText = a.getString(R.styleable.ImageTitleImageListItemView_titleText);
            Integer firstImageResId = a.getResourceId(R.styleable.ImageTitleImageListItemView_firstImageSrc,
                    R.drawable.icons8_under_construction_96);
            Integer secondImageResId = a.getResourceId(R.styleable.ImageTitleImageListItemView_secondImageSrc,
                    R.drawable.icons8_under_construction_96);
            Boolean secondImageOn = a.getBoolean(R.styleable.ImageTitleImageListItemView_secondImageOn,
                    false);
            a.recycle();
            setTitle(titleText);
            setFirstImageId(firstImageResId);
            setSecondImage(secondImageOn, secondImageResId);
        }
    }

    public ImageTitleImageListItemView(Context context) {
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

    public void setSecondImage(boolean on, int resId) {
        secondImage.setVisibility(on ? View.VISIBLE : View.GONE);
        if (on) {
            secondImage.setImageResource(resId);
        }
    }
}
