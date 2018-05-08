package com.example.dev.creatingapps.view.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.example.dev.creatingapps.R

class ForgotActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        val TitleBar = findViewById<View>(R.id.toolbar_forgot) as Toolbar
        setSupportActionBar(TitleBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, getString(R.string.app_login_double_back), Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}
