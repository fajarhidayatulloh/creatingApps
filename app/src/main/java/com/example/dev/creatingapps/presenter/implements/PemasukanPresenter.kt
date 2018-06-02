package com.example.dev.creatingapps.presenter.implements

import android.app.Activity
import android.util.Log
import com.example.dev.creatingapps.presenter.interfaces.ServerCallback
import com.example.dev.creatingapps.sys.retrofit.BaseApiService
import com.example.dev.creatingapps.sys.retrofit.UtilsApi
import com.example.dev.creatingapps.model.DataPemasukan
import com.example.dev.creatingapps.presenter.interfaces.PemasukanInterface
import com.example.dev.creatingapps.sys.config.APIConfig
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

class PemasukanPresenter : PemasukanInterface {
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

    override fun parsingData(data: String): DataPemasukan {
        var totalPemasukan = ""
        var currency = ""
        try {
            val json = JSONObject(data).getJSONObject("data")
            totalPemasukan = json.getString("totalPemasukan")
            currency = json.getString("currency")
        } catch (e: Exception) {
            Log.d(APIConfig.TAG, "EXCEPTION " + e)
        }
        return DataPemasukan(totalPemasukan, currency)
    }
}