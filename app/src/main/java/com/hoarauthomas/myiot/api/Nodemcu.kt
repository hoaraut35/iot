package com.hoarauthomas.myiot.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Nodemcu {
    @GET("LED=OFF")
    fun enableLed(): Call<ResponseBody>

    @GET("LED=ON")
    fun disableLed(): Call<ResponseBody>
}