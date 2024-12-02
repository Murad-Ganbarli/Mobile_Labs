package com.example.lab2_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val firstNameEditText: EditText = findViewById(R.id.firstNameEditText)
        val phoneEditText: EditText = findViewById(R.id.phoneEditText)
        val emailEditText: EditText = findViewById(R.id.emailEditText)

        val sendButton: Button = findViewById(R.id.sendButton)
        val clearButton: Button = findViewById(R.id.clearButton)

        // Set up the "Send" button click listener
        sendButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val firstName = firstNameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val email = emailEditText.text.toString()

            // Check if all fields are filled
            if (name.isNotEmpty() && firstName.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty()) {
                // Create an Intent to send data to SecondActivity
                val intent = Intent(this, SecondActivity::class.java).apply {
                    putExtra("NAME", name)
                    putExtra("FIRST_NAME", firstName)
                    putExtra("PHONE", phone)
                    putExtra("EMAIL", email)
                }
                startActivity(intent)
            }
        }

        // Set up the "Clear" button click listener
        clearButton.setOnClickListener {
            nameEditText.text.clear()
            firstNameEditText.text.clear()
            phoneEditText.text.clear()
            emailEditText.text.clear()
        }
    }
}
