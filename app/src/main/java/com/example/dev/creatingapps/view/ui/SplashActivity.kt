package com.example.dev.creatingapps.view.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.dev.creatingapps.R

class SplashActivity : AppCompatActivity() {

    private val splash_timeout = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(object : Runnable {
            public override fun run() {
                startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
                finish()
            }
        },splash_timeout.toLong())
    }
}
