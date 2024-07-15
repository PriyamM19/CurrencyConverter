package com.example.currencyconverter

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CountryInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_info)

        // Initialize views
        val countryNameTextView = findViewById<TextView>(R.id.countryName)
        val capitalTextView = findViewById<TextView>(R.id.capital)
        val populationTextView = findViewById<TextView>(R.id.population)
        val infoTextView = findViewById<TextView>(R.id.info)
        val langTextView = findViewById<TextView>(R.id.language)
        val flagImageView = findViewById<ImageView>(R.id.flagImage)

        // Retrieve selected currency from intent
        val selectedCurrency = intent.getStringExtra("selected_currency")

        // Set country info based on selected currency
        when (selectedCurrency) {
            "CAD" -> {
                countryNameTextView.text = getString(R.string.canada)
                capitalTextView.text = getString(R.string.ottawa)
                populationTextView.text = getString(R.string.population_cad)
                flagImageView.setImageResource(R.drawable.flag_cad)
                infoTextView.text = getString(R.string.info_cad)
                langTextView.text = getString(R.string.lang_cad)
            }
            "INR" -> {
                countryNameTextView.text = getString(R.string.india)
                capitalTextView.text = getString(R.string.new_delhi)
                populationTextView.text = getString(R.string.population_india)
                flagImageView.setImageResource(R.drawable.flag_ind)
                infoTextView.text = getString(R.string.info_india)
                langTextView.text = getString(R.string.lang_india)
            }
            "USD" -> {
                countryNameTextView.text = getString(R.string.usa)
                capitalTextView.text = getString(R.string.washington)
                populationTextView.text = getString(R.string.population_usa)
                flagImageView.setImageResource(R.drawable.flag_usa)
                infoTextView.text = getString(R.string.info_usa)
                langTextView.text = getString(R.string.lang_usa)
            }
            "JPY" -> {
                countryNameTextView.text = getString(R.string.japan)
                capitalTextView.text = getString(R.string.tokyo)
                populationTextView.text = getString(R.string.population_japan)
                flagImageView.setImageResource(R.drawable.flag_japan)
                infoTextView.text = getString(R.string.info_japan)
                langTextView.text = getString(R.string.lang_japan)
            }
            "IRR" -> {
                countryNameTextView.text = getString(R.string.iran)
                capitalTextView.text = getString(R.string.tehran)
                populationTextView.text = getString(R.string.population_iran)
                flagImageView.setImageResource(R.drawable.flag_iran)
                infoTextView.text = getString(R.string.info_iran)
                langTextView.text = getString(R.string.lang_iran)
            }
            "GBP" -> {
                countryNameTextView.text = getString(R.string.uk)
                capitalTextView.text = getString(R.string.london)
                populationTextView.text = getString(R.string.population_uk)
                flagImageView.setImageResource(R.drawable.flag_uk)
                infoTextView.text = getString(R.string.info_uk)
                langTextView.text = getString(R.string.lang_uk)
            }
            "KRW" -> {
                countryNameTextView.text = getString(R.string.sk)
                capitalTextView.text = getString(R.string.seoul)
                populationTextView.text = getString(R.string.population_sk)
                flagImageView.setImageResource(R.drawable.flag_sk)
                infoTextView.text = getString(R.string.info_sk)
                langTextView.text = getString(R.string.lang_sk)
            }
            else -> {
                countryNameTextView.text = getString(R.string.country_not_found)
                capitalTextView.text = ""
                populationTextView.text = ""
                flagImageView.setImageDrawable(null)
                infoTextView.text = ""
                langTextView.text = ""
            }
        }
    }
}
