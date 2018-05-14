package com.example.dev.creatingapps.presenter.interfaces

interface ServerCallback {
    fun onSuccess(response: String)

    fun onFailed(isFailed: String)

    fun onFailure(throwable: Throwable)
}