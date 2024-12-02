package com.example.lab1calculator

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab1calculator.R

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput: String = ""
    private var previousResult: Double = 0.0
    private var currentOperation: String? = null
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize display and shared preferences
        display = findViewById(R.id.display)
        sharedPreferences = getSharedPreferences("CalculatorApp", MODE_PRIVATE)

        // Restore the last calculation (if any)
        restoreLastCalculation()

        // List of button IDs
        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot,
            R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide,
            R.id.btnClear, R.id.btnClearEntry, R.id.btnEquals
        )

        // Attach click listeners to buttons
        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener { handleButtonPress(it as Button) }
        }
    }

    private fun handleButtonPress(button: Button) {
        when (val text = button.text.toString()) {
            "C" -> resetAll() // Clear all
            "CE" -> clearEntry() // Clear current entry
            "+", "-", "*", "/" -> setOperation(text) // Set operation
            "=" -> calculateResult() // Perform calculation
            "." -> addDot() // Add decimal point
            else -> addDigit(text) // Add digit to input
        }
    }

    private fun resetAll() {
        currentInput = ""
        previousResult = 0.0
        currentOperation = null
        display.text = "0"
    }

    private fun clearEntry() {
        currentInput = ""
        display.text = "0"
    }

    private fun setOperation(op: String) {
        if (currentInput.isNotEmpty()) {
            previousResult = currentInput.toDouble()
            currentInput = ""
        }
        currentOperation = op
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && currentOperation != null) {
            val secondOperand = currentInput.toDoubleOrNull() ?: 0.0
            previousResult = when (currentOperation) {
                "+" -> previousResult + secondOperand
                "-" -> previousResult - secondOperand
                "*" -> previousResult * secondOperand
                "/" -> {
                    if (secondOperand != 0.0) previousResult / secondOperand else {
                        display.text = "Error"
                        return
                    }
                }
                else -> 0.0
            }
            display.text = previousResult.toString().removeSuffix(".0")
            currentInput = ""
            currentOperation = null
            saveLastCalculation()
        }
    }

    private fun addDigit(digit: String) {
        currentInput += digit
        display.text = currentInput
    }

    private fun addDot() {
        if (!currentInput.contains(".")) {
            currentInput += "."
            display.text = currentInput
        }
    }

    private fun restoreLastCalculation() {
        display.text = sharedPreferences.getString("lastCalculation", "0")
    }

    private fun saveLastCalculation() {
        sharedPreferences.edit()
            .putString("lastCalculation", display.text.toString())
            .apply()
    }
}
