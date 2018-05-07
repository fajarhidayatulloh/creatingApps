package com.example.dev.creatingapps.view.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.dev.creatingapps.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TitleBar = findViewById<View>(R.id.toolbar_main) as Toolbar
        setSupportActionBar(TitleBar)
    }
}