package com.example.dev.creatingapps.sys.retrofit

import com.example.dev.creatingapps.model.Login
import com.example.dev.creatingapps.sys.config.APIConfig
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface BaseApiService {

    @POST(APIConfig.AUTH)
    fun makeLogin(@Header("Content-Type") type: String,
                  @Body data: Login): Call<ResponseBody>

    @GET(APIConfig.DATA)
    fun getList(@Header("Access-Token") token: String): Call<ResponseBody>
}