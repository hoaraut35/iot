package com.hoarauthomas.myiot

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.hoarauthomas.myiot.api.Nodemcu
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

    //private lateinit var retrofit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonOn = findViewById<TextView>(R.id.on_btn)
        val buttonOff = findViewById<TextView>(R.id.off_btn)

       val  retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.23/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(Nodemcu::class.java)


        buttonOn.setOnClickListener {
            setOnDevice(retrofit)
        }


        buttonOff.setOnClickListener {
            setOffDevice(retrofit)
        }


    }

    fun setOnDevice(retrofit: Nodemcu) {
        retrofit.enableLed().enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.i("[NODEMCU]", "Réponse MCU : " + response.body().toString())

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("[NODEMCU]", "Fail")
            }
        })

    }

    fun setOffDevice(retrofit: Nodemcu) {
        retrofit.disableLed().enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.i("[NODEMCU]", "Réponse MCU : " + response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("[NODEMCU]", "Fail")
            }
        })

    }
}