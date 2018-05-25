package com.example.dev.creatingapps.presenter.implements

import android.app.Activity
import android.util.Log
import com.example.dev.creatingapps.presenter.interfaces.ServerCallback
import com.example.dev.creatingapps.sys.retrofit.BaseApiService
import com.example.dev.creatingapps.sys.retrofit.UtilsApi
import com.example.dev.creatingapps.model.DataHome
import com.example.dev.creatingapps.presenter.interfaces.MainInterface
import com.example.dev.creatingapps.sys.config.APIConfig
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback

class MainPresenter : MainInterface {
    override fun getData(context: Activity, token: String, callback: ServerCallback) {
        val mApiService: BaseApiService = UtilsApi.apiService
        mApiService.getList(token)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: retrofit2.Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            callback.onSuccess(response.body()!!.string())
                        }else {
                            callback.onFailed(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        callback.onFailure(t)
                    }
                })
    }

    override fun parsingData(respon: String): ArrayList<DataHome> {
        var arrayList = ArrayList<DataHome>()
        try {
            val json = JSONArray(respon)
            (0 until json.length())
                    .map { json.getJSONObject(it) }
                    .forEach {
                        val id = it.getInt("id")
                        val thumbnail = it.getString("thumbnail_url")
                        if (it.has("summary")){
                            val summary = it.getString("summary")
                            arrayList.add(DataHome(id))
                        }else{
                            arrayList.add(DataHome(id))
                        }
                    }
            Log.d(APIConfig.TAG, "SIZE " + arrayList.size + " ARRAYLIST " + arrayList)
        } catch (e: Exception) {
            Log.d(APIConfig.TAG, "EXCEPTION " + e)
        }
        return arrayList
    }
}