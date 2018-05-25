package com.example.dev.creatingapps.view.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.dev.creatingapps.R
import com.example.dev.creatingapps.presenter.implements.SplashPresenter

class SplashActivity : AppCompatActivity() {

    private var resume = false
    private var presenter: SplashPresenter? = null


    private val permissions = arrayOf(android.Manifest.permission.INTERNET)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter()
        presenter!!.checkPermission(this, permissions)
        /*Handler().postDelayed(object : Runnable {
            public override fun run() {
                startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
                finish()
            }
        },splash_timeout.toLong())*/
    }

    public override fun onResume() {
        super.onResume()
        if (resume) {
            presenter!!.checkPermission(this, permissions)
        }
        resume = true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        presenter!!.resultPermission(this, requestCode, grantResults)
    }

    override fun onBackPressed() {}
}
