package com.example.dev.creatingapps.view.ui

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.internal.SnackbarContentLayout
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.dev.creatingapps.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val TitleBar = findViewById<View>(R.id.toolbar_login) as Toolbar
        setSupportActionBar(TitleBar)

        linkRegistration.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        linkForgotPassword.setOnClickListener {
            var intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener {
            var username = editTextLoginEmail.text.toString()
            var password = editTextLoginPassword.text.toString().trim { it <= ' ' }

            if(username != "fajarhid@gmail.com" && password != "12345678"){

                Snackbar.make(coordinatorLogin, getString(R.string.toast_login_fill),
                Snackbar.LENGTH_SHORT).show()
            } else {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
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
