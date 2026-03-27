package com.example.ledgefy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ReportsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)

        val calendarBtn = findViewById<ImageButton>(R.id.btn_calendar)
        val homeBtn = findViewById<ImageButton>(R.id.btn_home)
        val addBtn = findViewById<ImageButton>(R.id.btn_add)
        val settingsFragment = supportFragmentManager.findFragmentById(R.id.settings_fragment_container)

        val settingsButton = findViewById<ImageButton>(R.id.btn_settings)
        settingsButton.setOnClickListener {
            val container = findViewById<View>(R.id.settings_fragment_container)
            if (container.visibility == View.VISIBLE) {
                container.visibility = View.GONE
            } else {
                container.visibility = View.VISIBLE
            }
        }


        calendarBtn.setOnClickListener {
            val intent = Intent(this, ReportsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }

        homeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }

        addBtn.setOnClickListener {
            startActivity(Intent(this, TransactionActivity::class.java))
        }
    }
}

