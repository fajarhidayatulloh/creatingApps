package com.example.dev.creatingapps.presenter.interfaces

import android.app.Activity

interface SplashInterface {
    fun checkPermission(activity: Activity, permission: Array<String>)

    fun resultPermission(activity: Activity, requestCode: Int, grantResults: IntArray)
}