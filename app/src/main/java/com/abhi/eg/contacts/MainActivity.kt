package com.abhi.eg.contacts

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val handler = Handler()

        handler.postDelayed(Runnable {
            clSplash.visibility=View.GONE
            flFrag.visibility=View.VISIBLE

        }, 1000)


    }

    override fun onBackPressed() {
        finish()
    }


}
