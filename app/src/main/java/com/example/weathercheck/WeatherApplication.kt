package com.example.weathercheck

import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApplication : Application(){

    var service:Service?=null
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        setupRetrofit()
    }

    private fun setupRetrofit(){
        val httpClient= OkHttpClient.Builder()
        httpClient.addNetworkInterceptor(StethoInterceptor())
        val client=httpClient.build()

        val retrofit=Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service=retrofit.create(Service::class.java)
    }

    fun requestService():Service?{

        return service
    }

}