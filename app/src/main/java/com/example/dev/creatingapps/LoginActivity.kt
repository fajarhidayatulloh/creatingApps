package com.example.dev.creatingapps

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        val ToolBarAtas2 = findViewById<View>(R.id.toolbar_login) as Toolbar
        setSupportActionBar(ToolBarAtas2)
        ToolBarAtas2.setLogoDescription(resources.getString(R.string.app_name))
    }
}
