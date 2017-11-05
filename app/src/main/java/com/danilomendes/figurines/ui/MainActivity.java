package com.danilomendes.figurines.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;

import com.danilomendes.figurines.R;

public class MainActivity extends AppCompatActivity {
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return super.onRetainCustomNonConfigurationInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The code below is intended to push the fragment container to the top of
        // the coordinator layout. This way the AppBarLayout stays above of it (Z axe).
        AppBarLayout appBarLayout = findViewById(R.id.app_bar_layout);
        NestedScrollView nestedScrollView = findViewById(R.id.nsv_list);
        View topPaddingView = findViewById(R.id.top_padding_view);
        nestedScrollView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        nestedScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        topPaddingView.getLayoutParams().height = appBarLayout.getHeight();
                        nestedScrollView.setTranslationY(-appBarLayout.getHeight());
                        nestedScrollView.getLayoutParams().height = nestedScrollView.getHeight() + appBarLayout.getHeight();
                        appBarLayout.bringToFront();
                    }
                });
    }
}
