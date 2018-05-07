package com.example.dev.creatingapps.view.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.example.dev.creatingapps.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        val TitleBar = findViewById<View>(R.id.toolbar_register) as Toolbar
        setSupportActionBar(TitleBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }
}