package com.example.dev.creatingapps.presenter.implements

import android.app.Activity
import com.example.dev.creatingapps.presenter.interfaces.LoginInterface
import com.example.dev.creatingapps.presenter.interfaces.ServerCallback
import com.example.dev.creatingapps.sys.retrofit.BaseApiService
import com.example.dev.creatingapps.sys.retrofit.UtilsApi
import com.example.dev.creatingapps.sys.util.SessionManager
import com.example.dev.creatingapps.model.Login
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

class LoginPresenter : LoginInterface {

    override fun parsingMessage(response: String): String {
        return try {
            val jObj = JSONObject(response)
            jObj.getString("message")
        } catch (e: Exception) {
            "Failed Parsing!"
        }
    }

    override fun checkPassword(username: String, password: String): Boolean {
        return (username.isEmpty() || password.isEmpty())
    }

    override fun isSuccess(response: String): Boolean {
        return try {
            val jObj = JSONObject(response)
            jObj.has("access_token")
        } catch (e: Exception) {
            false
        }
    }

    override fun saveSession(activity: Activity, response: String): Boolean {
        return try {
            val jObj = JSONObject(response)
            val accessToken = jObj.getString("access_token")
            val session = SessionManager(activity)
            session.startSession(accessToken)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun performLogin(context: Activity, grant_type: String, client_id:String, client_secret:String, username: String, password: String, scope:String,
                              callback: ServerCallback) {
        val mApiService: BaseApiService = UtilsApi.apiService

        mApiService.makeLogin("application/json", Login(grant_type,client_id,client_secret,username, password,scope))
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: retrofit2.Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            callback.onSuccess(response.body()!!.string())
                        }else{
                            callback.onFailed(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        callback.onFailure(t)
                    }

                })
    }

}