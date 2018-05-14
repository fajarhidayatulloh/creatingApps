package com.example.dev.creatingapps.sys.retrofit

import com.example.dev.creatingapps.sys.config.APIConfig


object UtilsApi {
    val apiService: BaseApiService
        get() = RetrofitClient.getClient(APIConfig.END_POINT).create(BaseApiService::class.java)
}
