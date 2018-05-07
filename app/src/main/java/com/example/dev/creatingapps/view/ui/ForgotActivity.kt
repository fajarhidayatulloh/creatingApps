package com.example.dev.creatingapps.view.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.dev.creatingapps.R

class ForgotActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        val TitleBar = findViewById<View>(R.id.toolbar_forgot) as Toolbar
        setSupportActionBar(TitleBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }
}
