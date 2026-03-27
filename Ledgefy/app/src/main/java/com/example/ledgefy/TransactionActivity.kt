package com.example.ledgefy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ledgefy.model.Transaction
import com.example.ledgefy.viewmodel.TransactionViewModel

class TransactionActivity : AppCompatActivity() {

    private lateinit var amountEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var addButton: ImageButton
    private lateinit var subtractButton: ImageButton

    private val transactionViewModel: TransactionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        amountEditText = findViewById(R.id.edit_amount)
        dateEditText = findViewById(R.id.edit_date)
        descriptionEditText = findViewById(R.id.edit_description)

        addButton = findViewById(R.id.btn_plus)
        subtractButton = findViewById(R.id.btn_subtract)

        addButton.setOnClickListener {
            handleTransaction(isAddition = true)
        }

        subtractButton.setOnClickListener {
            handleTransaction(isAddition = false)
        }

        val calendarBtn = findViewById<ImageButton>(R.id.btn_calendar)
        val homeBtn = findViewById<ImageButton>(R.id.btn_home)
        val addBtn = findViewById<ImageButton>(R.id.btn_add_nav)

        val settingsButton = findViewById<ImageButton>(R.id.btn_settings)
        settingsButton.setOnClickListener {
            val container = findViewById<View>(R.id.settings_fragment_container)
            container.visibility = if (container.visibility == View.VISIBLE) View.GONE else View.VISIBLE
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
            val intent = Intent(this, TransactionActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
    }

    private fun handleTransaction(isAddition: Boolean) {
        val amountText = amountEditText.text.toString()
        val dateText = dateEditText.text.toString()
        val descriptionText = descriptionEditText.text.toString()

        if (amountText.isBlank() || dateText.isBlank() || descriptionText.isBlank()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountText.toDoubleOrNull()
        if (amount == null) {
            Toast.makeText(this, "Amount must be a valid number", Toast.LENGTH_SHORT).show()
            return
        }

        val finalAmount = if (isAddition) amount else -amount
        val newTransaction = Transaction(
            description = descriptionText,
            amount = finalAmount,
            date = dateText
        )

        transactionViewModel.insertTransaction(newTransaction)

        Toast.makeText(this, "Transaction ${if (isAddition) "added" else "subtracted"}", Toast.LENGTH_SHORT).show()
        finish() // Return to previous screen
    }
}
