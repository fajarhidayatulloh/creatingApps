package com.example.dev.creatingapps.view.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.support.design.widget.BottomNavigationView
import android.widget.Toast
import com.example.dev.creatingapps.R
import android.app.ProgressDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.dev.creatingapps.presenter.interfaces.ServerCallback
import com.example.dev.creatingapps.sys.util.SessionManager
import com.example.dev.creatingapps.model.DataHome
import com.example.dev.creatingapps.presenter.implements.MainPresenter
import com.example.dev.creatingapps.view.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false
    private var adapter: MainAdapter? = null
    private var layoutManager: LinearLayoutManager? = null
    private var loading: ProgressDialog? = null

    private val presenter = MainPresenter()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val intent = Intent(this, PemasukanActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val intent = Intent(this, PemasukanActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TitleBar = findViewById<View>(R.id.toolbar_main) as Toolbar
        setSupportActionBar(TitleBar)

        value_of_tabungan.isEnabled = false

        val sessionManager = SessionManager(this@MainActivity)
        loading = ProgressDialog.show(this, getString(R.string.progress_loading),
                getString(R.string.progress_getting), false, false)
        presenter.getData(this@MainActivity, "Bearer " + sessionManager.accessToken, object : ServerCallback {
            override fun onSuccess(response: String) {
                hideDialog()
                val data = presenter.parsingData(response)
                value_of_tabungan.text = data.currency + ". " + data.totalTabungan
            }

            override fun onFailed(isFailed: String) {
                hideDialog()
                Toast.makeText(this@MainActivity, getString(R.string.toast_data_load_failed),
                        Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(throwable: Throwable) {
                hideDialog()
                Toast.makeText(this@MainActivity, getString(R.string.toast_data_load_failed),
                        Toast.LENGTH_SHORT).show()
            }
        })

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

    private fun hideDialog() {
        if (loading!!.isShowing)
            loading!!.dismiss()
    }

}