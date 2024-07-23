package com.example.sapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerFromCurrency: Spinner
    private lateinit var spinnerToCurrency: Spinner
    private lateinit var etAmount: EditText
    private lateinit var btnConvert: Button
    private lateinit var tvResult: TextView

    private val currencies = arrayOf("USD", "EUR", "GBP", "INR", "JPY")
    private val conversionRates = mapOf(
        "USD" to 1.0,
        "EUR" to 0.85,
        "GBP" to 0.75,
        "INR" to 74.0,
        "JPY" to 110.0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerFromCurrency = findViewById(R.id.spinnerFromCurrency)
        spinnerToCurrency = findViewById(R.id.spinnerToCurrency)
        etAmount = findViewById(R.id.etAmount)
        btnConvert = findViewById(R.id.btnConvert)
        tvResult = findViewById(R.id.tvResult)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerFromCurrency.adapter = adapter
        spinnerToCurrency.adapter = adapter

        spinnerFromCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Do something when an item is selected
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do something when nothing is selected
            }
        }

        spinnerToCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Do something when an item is selected
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do something when nothing is selected
            }
        }

        btnConvert.setOnClickListener {
            val fromCurrency = spinnerFromCurrency.selectedItem.toString()
            val toCurrency = spinnerToCurrency.selectedItem.toString()
            val amountStr = etAmount.text.toString()

            if (amountStr.isEmpty()) {
                Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountStr.toDouble()
            val convertedAmount = convertCurrency(fromCurrency, toCurrency, amount)
            tvResult.text = "Converted Amount: %.2f %s".format(convertedAmount, toCurrency)
        }
    }

    private fun convertCurrency(fromCurrency: String, toCurrency: String, amount: Double): Double {
        val fromRate = conversionRates[fromCurrency] ?: 1.0
        val toRate = conversionRates[toCurrency] ?: 1.0
        return (amount / fromRate) * toRate
    }
}
