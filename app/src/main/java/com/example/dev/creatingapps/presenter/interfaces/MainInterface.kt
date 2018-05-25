package com.example.dev.creatingapps.presenter.interfaces

import android.app.Activity
import com.example.dev.creatingapps.presenter.interfaces.ServerCallback
import com.example.dev.creatingapps.model.DataHome

interface MainInterface {
    fun getData(context: Activity, token: String, callback: ServerCallback)
    fun parsingData(respon: String): ArrayList<DataHome>
}