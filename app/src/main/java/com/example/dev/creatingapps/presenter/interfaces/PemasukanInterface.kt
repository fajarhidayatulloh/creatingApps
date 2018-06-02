package com.example.dev.creatingapps.presenter.interfaces

import android.app.Activity
import com.example.dev.creatingapps.presenter.interfaces.ServerCallback
import com.example.dev.creatingapps.model.DataPemasukan

interface PemasukanInterface {
    fun getData(context: Activity, token: String, callback: ServerCallback)
    fun parsingData(data: String): DataPemasukan
}