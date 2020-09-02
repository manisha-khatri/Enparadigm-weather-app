package com.example.enparadigmweatherapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.enparadigmweatherapp.R
import com.example.enparadigmweatherapp.util.Constants
import kotlinx.android.synthetic.main.activity_initial.*

class IntialActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        onSerachBtnClickListener()
    }

    private fun onSerachBtnClickListener() {
        searchBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var city: String?= cityName.text.toString()
                if(city != null && city!=""){
                    val intent = Intent(this@IntialActivity, WeatherDashboardActivity::class.java)
                    intent.putExtra(Constants.CITY_NAME,city)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this@IntialActivity, "Please enter city name!", Toast.LENGTH_SHORT)
                        .show()
            }
        })
    }
}