package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import com.example.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiKey = "8894fd8dfe6e70e3717222a32f857757"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        fetchWeather()
    }

    private fun fetchWeather() {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getWeather("Cebu", apiKey)

                binding.tvCity.text = response.name
                binding.tvWeatherMain.text = response.weather[0].main
                binding.tvWeatherDesc.text = response.weather[0].description
                val iconUrl = "https://openweathermap.org/img/wn/${response.weather[0].icon}@2x.png"
                Glide.with(this@MainActivity).load(iconUrl).into(binding.imgWeatherIcon)

            } catch (e: Exception) {
                binding.tvCity.text = "Error fetching weather"
                e.printStackTrace()


                android.util.Log.e("WeatherApp", "Error fetching weather", e)
            }
        }
    }


}
