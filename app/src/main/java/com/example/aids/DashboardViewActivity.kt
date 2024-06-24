package com.example.aids

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aids.ui.Dataset
import com.example.aids.ui.Fitur
import com.example.aids.ui.Model
import com.example.aids.ui.Profile

class DashboardViewActivity : AppCompatActivity() {

    private lateinit var profile: CardView
    private lateinit var dataset: CardView
    private lateinit var fitur: CardView
    private lateinit var model: CardView
    private lateinit var simulasi: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_view)

        profile = findViewById(R.id.cardProfile)
        dataset = findViewById(R.id.cardDataset)
        fitur = findViewById(R.id.cardfitur)
        model = findViewById(R.id.cardModel)
        simulasi = findViewById(R.id.cardSimulasi)

        profile.setOnClickListener {
            showToast("Profil")
            val intent = Intent(this@DashboardViewActivity, Profile::class.java)
            startActivity(intent)
        }
        dataset.setOnClickListener {
            showToast("Dataset")
            val intent = Intent(this@DashboardViewActivity, Dataset::class.java)
            startActivity(intent)
        }
        fitur.setOnClickListener {
            showToast("Fitur")
            val intent = Intent(this@DashboardViewActivity, Fitur::class.java)
            startActivity(intent)
        }
        model.setOnClickListener {
            showToast("Model")
            val intent = Intent(this@DashboardViewActivity, Model::class.java)
            startActivity(intent)
        }
        simulasi.setOnClickListener {
            showToast("Simulasi")
            val intent = Intent(this@DashboardViewActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
