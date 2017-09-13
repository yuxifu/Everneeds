package com.yuxifu.everneeds.ui.base;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yuxifu.everneeds.R;

public abstract class HomeAsUpFragmentActivity extends AppCompatActivity {
    public static final String SUPPORT_ACTION_BAR_HIDE_ON_SCROLL = "SUPPORT_ACTION_BAR_HIDE_ON_SCROLL";

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_home_as_up_fragment);

        //
        appBarLayout = findViewById(R.id.main_appbar);

        // Toolbar
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //
        Bundle bundle = getIntent().getExtras();
        boolean supportActionBarHideOnScroll = false; // or other values
        if (bundle != null) {
            supportActionBarHideOnScroll
                    = bundle.getBoolean(SUPPORT_ACTION_BAR_HIDE_ON_SCROLL, false);
        }
        enableActionBarHideOnScroll(supportActionBarHideOnScroll, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //
    public void enableActionBarHideOnScroll(boolean enable, boolean requestLayout) {
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        if (enable) {
            int newFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP;
            if (params.getScrollFlags() != newFlags) {
                params.setScrollFlags(newFlags);
                if (requestLayout) {
                    appBarLayout.requestLayout();
                }
            }
        } else {
            if (params.getScrollFlags() != 0) {
                params.setScrollFlags(0);
                if (requestLayout) {
                    appBarLayout.requestLayout();
                }
            }
        }
    }

    //
    public void enableActionBarHideOnScroll(boolean enable) {
        enableActionBarHideOnScroll(enable, true);
    }

}
