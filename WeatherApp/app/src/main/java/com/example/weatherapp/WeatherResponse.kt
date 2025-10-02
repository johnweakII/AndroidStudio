package com.example.weatherapp

data class WeatherResponse(
    val weather: List<Weather>,
    val main: Main,

    val name: String
)

data class Weather(
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val feels_like: Double,
)

