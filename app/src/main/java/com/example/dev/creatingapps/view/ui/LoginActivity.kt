package com.example.dev.creatingapps.view.ui

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import android.util.Log
import com.example.dev.creatingapps.R
import com.example.dev.creatingapps.presenter.interfaces.ServerCallback
import com.example.dev.creatingapps.presenter.implements.LoginPresenter
import com.example.dev.creatingapps.sys.config.APIConfig
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var grant_type: String? = null
    private var client_id: String? = null
    private var client_secret: String? = null
    private var scope: String? = null
    private var username: String? = null
    private var password: String? = null
    private var loading: ProgressDialog? = null
    private val presenter: LoginPresenter = LoginPresenter()
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
            grant_type = "bareksa_password"
            client_id = "4"
            client_secret = "jCbTlxE2XrT56IG7KWQvz1H2k3xfRYDxFNN4kkdm"
            username = editTextLoginEmail.text.toString()
            password = editTextLoginPassword.text.toString().trim { it <= ' ' }
            scope = "*"
            if (presenter.checkPassword(username!!, password!!)) {
                Snackbar.make(coordinatorLogin, getString(R.string.toast_login_fill), Snackbar.LENGTH_SHORT).show()
            } else {
                loading = ProgressDialog.show(this, getString(R.string.progress_loading),
                        getString(R.string.progress_login), false, false)
                presenter.performLogin(this,  grant_type!!, client_id!!, client_secret!!, username!!, password!!, scope!!, object : ServerCallback {

                    override fun onSuccess(response: String) {
                        Log.d(APIConfig.TAG,"HASIL REQUEST "+response)
                        if (presenter.isSuccess(response)) {
                            hideDialog()
                            if (!presenter.saveSession(this@LoginActivity, response)) {
                                Toast.makeText(this@LoginActivity, getString(R.string.toast_login_failed_session),
                                        Toast.LENGTH_SHORT).show()
                            } else {
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                                finish()
                            }
                        } else {
                            hideDialog()
                            Toast.makeText(this@LoginActivity, presenter.parsingMessage(response),
                                    Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailed(isFailed: String) {
                        Log.d(APIConfig.TAG,"HASIL FAILED ")
                        hideDialog()
                        editTextLoginEmail.text.clear()
                        editTextLoginPassword.text.clear()
                        Toast.makeText(this@LoginActivity, presenter.parsingMessage(isFailed),
                                Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(throwable: Throwable) {
                        Log.d(APIConfig.TAG,"HASIL FAILURE ")
                        hideDialog()
                        Toast.makeText(this@LoginActivity, getString(R.string.toast_login_connection),
                                Toast.LENGTH_LONG).show()
                    }
                })
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

    private fun hideDialog() {
        if (loading!!.isShowing)
            loading!!.dismiss()
    }
}
