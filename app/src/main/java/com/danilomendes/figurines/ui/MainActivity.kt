package com.danilomendes.figurines.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewTreeObserver

import com.danilomendes.figurines.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_company_list.*

class MainActivity : AppCompatActivity() {

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return super.onRetainCustomNonConfigurationInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // The code below is intended to push the fragment container to the top of
        // the coordinator layout. This way the AppBarLayout stays above of it (Z axe).
        nsv_list.viewTreeObserver.addOnGlobalLayoutListener(
                object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        nsv_list.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        top_padding_view.layoutParams.height = app_bar_layout.height
                        nsv_list.translationY = (-app_bar_layout.height).toFloat()
                        nsv_list.layoutParams.height = nsv_list.height + app_bar_layout.height
                        app_bar_layout.bringToFront()
                    }
                })
    }
}
