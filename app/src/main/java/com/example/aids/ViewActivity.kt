package com.example.aids

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view)

        val secondActbutton = findViewById<Button>(R.id.button)
        secondActbutton.setOnClickListener {
            val Intent = Intent(this, DashboardViewActivity::class.java)
            startActivity(Intent)


        }
    }
    }