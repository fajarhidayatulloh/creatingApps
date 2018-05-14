package com.example.dev.creatingapps.presenter.interfaces

import android.app.Activity

interface LoginInterface {

    fun checkPassword(email: String, password: String): Boolean
    fun performLogin(context: Activity,  grant_type: String, client_id:String, client_secret:String, username: String, password: String, scope:String,
                     callback: ServerCallback)

    fun saveSession(activity: Activity, response: String) : Boolean
    fun isSuccess(response: String): Boolean

    fun parsingMessage(response: String): String
}