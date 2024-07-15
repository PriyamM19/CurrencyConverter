package com.example.currencyconverter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // initializing variables

    private lateinit var amountInput: EditText     //declaring a non-null variable that will be initialized later.
    private lateinit var currencyFromSpinner: Spinner
    private lateinit var currencyToSpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var convertedAmount: TextView
    private lateinit var countryInfoButton: Button
    private var fromCurrency: String = ""
    private var toCurrency: String = ""

    //setting up the user interface and initializes the variables with their corresponding views from the XML layout.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountInput = findViewById(R.id.amountInput)
        currencyFromSpinner = findViewById(R.id.currencyFromSpinner)
        currencyToSpinner = findViewById(R.id.currencyToSpinner)
        convertButton = findViewById(R.id.convertButton)
        convertedAmount = findViewById(R.id.convertedAmount)
        countryInfoButton = findViewById(R.id.countryInfoButton)

        // Set up currency spinners
        ArrayAdapter.createFromResource(    //setting up an ArrayAdapter for the Spinner views using a string array resource.
            this,
            R.array.currency_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)    //sets the layout resource for the dropdown views of the spinner
            currencyFromSpinner.adapter = adapter   //adapter to display its items.
            currencyToSpinner.adapter = adapter
        }

        // Currency selection listeners
        currencyFromSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                fromCurrency = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        currencyToSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                toCurrency = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Convert button click listener that convert currency values
        convertButton.setOnClickListener {
            updateCurrencyConversion()
        }

        // Country info button click listener
        countryInfoButton.setOnClickListener {
            val selectedCurrency = toCurrency

            val intent = Intent(this, CountryInfoActivity::class.java)
            intent.putExtra("selected_currency", selectedCurrency)
            startActivity(intent)
        }
    }

    private fun updateCurrencyConversion() {
        val amountText = amountInput.text.toString()
        if (amountText.isNotEmpty() && fromCurrency.isNotEmpty() && toCurrency.isNotEmpty()) {
            val amount = amountText.toDouble()
            val convertedValue = convertCurrency(amount, fromCurrency, toCurrency)
            convertedAmount.text = "Converted Amount: $convertedValue $toCurrency"
        } else {
            convertedAmount.text = getString(R.string.please_enter_valid_amount_and_currency)
        }
    }

    private fun convertCurrency(amount: Double, fromCurrency: String, toCurrency: String): Double {
        // hardcoded values for currency conversion from predefined countries
        val conversionRates = mapOf(
            "INR" to mapOf("INR" to 1.0, "CAD" to 0.016, "USD" to 0.012, "JPY" to 1.90, "IRR" to 503.93, "GBP" to 0.0094, "KRW" to 16.47),
            "CAD" to mapOf("INR" to 61.20, "CAD" to 1.0, "USD" to 0.73, "JPY" to 115.67, "IRR" to 30841.24, "GBP" to 0.58, "KRW" to 1007.66),
            "USD" to mapOf("INR" to 0.73, "CAD" to 1.36, "USD" to 1.0, "JPY" to 157.86, "IRR" to 42087.50, "GBP" to 0.79, "KRW" to 1375.10),
            "JPY" to mapOf("INR" to 0.0086, "CAD" to 0.0086, "USD" to 0.0063, "JPY" to 1.0, "IRR" to 266.62, "GBP" to 0.0050, "KRW" to 8.71),
            "IRR" to mapOf("INR" to 0.000032, "CAD" to 0.000032, "USD" to 0.000024, "JPY" to 0.0038, "IRR" to 1.0, "GBP" to 0.000019, "KRW" to 0.0033),
            "GBP" to mapOf("INR" to 1.73, "CAD" to 1.73, "USD" to 1.72, "JPY" to 200.50, "IRR" to 53457.40, "GBP" to 1.0, "KRW" to 1746.65),
            "KRW" to mapOf("INR" to 0.00099, "CAD" to 0.00099, "USD" to 0.00073, "JPY" to 0.11, "IRR" to 30.61, "GBP" to 0.00057, "KRW" to 1.0)
        )

        // Retrieving the conversion rate from the map
        val rate = conversionRates[fromCurrency]?.get(toCurrency) ?: 1.0
        return amount * rate
    }
}
