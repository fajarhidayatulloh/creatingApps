package com.example.dev.creatingapps

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        val TitleBar = findViewById<View>(R.id.toolbar_register) as Toolbar
        setSupportActionBar(TitleBar)
        TitleBar.setLogoDescription(resources.getString(R.string.app_name))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val toolbarText = findViewById<View>(R.id.toolbar_text) as TextView
        if (toolbarText != null && TitleBar != null) {
            toolbarText.text = title
            setSupportActionBar(TitleBar)
        }
    }
}
