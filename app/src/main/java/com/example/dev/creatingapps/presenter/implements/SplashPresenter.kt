package com.example.dev.creatingapps.presenter.implements

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.example.dev.creatingapps.sys.util.SessionManager
import com.example.dev.creatingapps.R
import com.example.dev.creatingapps.presenter.interfaces.SplashInterface
import com.example.dev.creatingapps.sys.config.APIConfig
import com.example.dev.creatingapps.view.ui.LoginActivity
import com.example.dev.creatingapps.view.ui.MainActivity

class SplashPresenter : SplashInterface {

    private val splash_timeout = 3000

    override fun checkPermission(activity: Activity, arrayPermissions: Array<String>) {
        if (Build.VERSION.SDK_INT >= 23) {
            Log.d(APIConfig.TAG, "IN IF Build.VERSION.SDK_INT >= 23")

            if (!hasPermissions(activity, *arrayPermissions)) {
                Log.d(APIConfig.TAG, "IN IF hasPermissions")
                ActivityCompat.requestPermissions(activity, arrayPermissions, APIConfig.REQUEST_PERMISSION)
            } else {
                Log.d(APIConfig.TAG, "IN ELSE hasPermissions")
                splashLanding(activity)
            }
        } else {
            Log.d(APIConfig.TAG, "IN ELSE  Build.VERSION.SDK_INT >= 23")
            splashLanding(activity)
        }
    }

    override fun resultPermission(activity: Activity, requestCode: Int, grantResults: IntArray) {
        when (requestCode) {
            APIConfig.REQUEST_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(APIConfig.TAG, "PERMISSIONS grant")
                    splashLanding(activity)
                } else {
                    Log.d(APIConfig.TAG, "PERMISSIONS Denied")
                    val alert = android.support.v7.app.AlertDialog.Builder(activity)
                    alert.setTitle(activity.getString(R.string.dialog_warning))
                    alert.setMessage(activity.getString(R.string.dialog_permission))
                    alert.setCancelable(false)
                    alert.setPositiveButton(activity.getString(R.string.dialog_yes)) { _, _ ->
                        // TODO Auto-generated method stub
                        activity.finish()
                        activity.startActivity(activity.intent)
                    }
                    alert.show()
                }
            }
        }
    }

    private fun splashLanding(activity: Activity) {
        val session = SessionManager(activity)
        Log.d(APIConfig.TAG, "Bearer " + session.isSession)

        if (session.isSession) {
            Handler().postDelayed(object : Thread() {
                override fun run() {
                    val intent = Intent(activity, MainActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()
                }
            }, splash_timeout.toLong())
        } else {
            Handler().postDelayed(object : Thread() {
                override fun run() {
                    val intent = Intent(activity, LoginActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()
                }
            }, splash_timeout.toLong())
        }
    }

    private fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null) {
            permissions
                    .filter { ActivityCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED }
                    .forEach { return false }
        }
        return true
    }
}
