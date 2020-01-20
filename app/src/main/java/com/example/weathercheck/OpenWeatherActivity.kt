package com.example.weathercheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_open_weather.*

class OpenWeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_weather)
        setting.setOnClickListener { startActivity(Intent(this, AccountSettingActivity::class.java)) }
    }
}
