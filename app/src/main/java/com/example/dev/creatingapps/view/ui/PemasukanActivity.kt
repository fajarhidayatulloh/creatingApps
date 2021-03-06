package com.example.dev.creatingapps.view.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.example.dev.creatingapps.R
import kotlinx.android.synthetic.main.activity_main.*

class PemasukanActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener false
            }
            R.id.navigation_dashboard -> {
                val intent = Intent(this, PemasukanActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val intent = Intent(this, PemasukanActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener false
            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemasukan)
        val TitleBar = findViewById<View>(R.id.toolbar_pemasukan) as Toolbar
        setSupportActionBar(TitleBar)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
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
