package com.example.dev.creatingapps

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login.*
import com.example.dev.creatingapps.R.id.toolbar



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Login Toolbar
        val TitleBar = findViewById<View>(R.id.toolbar_login) as Toolbar
        setSupportActionBar(TitleBar)

        val toolbarText = findViewById<View>(R.id.toolbar_text) as TextView
        if (toolbarText != null && TitleBar != null) {
            toolbarText.text = title
            setSupportActionBar(TitleBar)
        }

        linkRegistration.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        linkForgotPassword.setOnClickListener {
            var intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
        }
    }
}
