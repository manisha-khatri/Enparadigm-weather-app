package com.example.enparadigmweatherapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.enparadigmweatherapp.R
import com.example.enparadigmweatherapp.util.Constants
import kotlinx.android.synthetic.main.activity_weather_dashboard.*

class WeatherDashboardActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_dashboard)
        onSerachBtnClickListener()
    }

    private fun onSerachBtnClickListener() {
        searchBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var userInput: String?= cityName.text.toString()
                if(userInput != null && userInput!="" && isLetters(userInput)){
                    val intent = Intent(this@WeatherDashboardActivity, WeatherDetailsActivity::class.java)
                    intent.putExtra(Constants.CITY_NAME,userInput)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this@WeatherDashboardActivity, "Invalid input!", Toast.LENGTH_SHORT)
                        .show()
            }
        })
    }

    fun isLetters(string: String): Boolean {
        for (c in string) {
            if (c !in 'A'..'Z' && c !in 'a'..'z') {
                return false
            }
        }
        return true
    }
}