package com.example.dev.creatingapps.model

data class Login(val grant_type:String, val client_id:String, val client_secret:String, val username:String, val password:String,val scope:String)