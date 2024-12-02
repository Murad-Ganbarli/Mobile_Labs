package com.example.lab2_1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Retrieve the data from the Intent
        val name = intent.getStringExtra("NAME")
        val firstName = intent.getStringExtra("FIRST_NAME")
        val phone = intent.getStringExtra("PHONE")
        val email = intent.getStringExtra("EMAIL")

        // Find the TextViews to display the data
        val nameTextView: TextView = findViewById(R.id.nameTextView)
        val firstNameTextView: TextView = findViewById(R.id.firstNameTextView)
        val phoneTextView: TextView = findViewById(R.id.phoneTextView)
        val emailTextView: TextView = findViewById(R.id.emailTextView)

        // Set the TextViews to display the data
        nameTextView.text = name
        firstNameTextView.text = firstName
        phoneTextView.text = phone
        emailTextView.text = email
    }
}
