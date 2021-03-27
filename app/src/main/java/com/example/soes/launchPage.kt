package com.example.soes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class launchPage : AppCompatActivity() {
    lateinit var instButton: Button
    lateinit var studentButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launch2)

        instButton = findViewById(R.id.instructor_sign_Button)
        instButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}