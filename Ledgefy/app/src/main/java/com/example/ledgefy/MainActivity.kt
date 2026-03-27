package com.example.ledgefy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ledgefy.viewmodel.TransactionViewModel

class MainActivity : AppCompatActivity() {

    private val transactionViewModel: TransactionViewModel by viewModels()
    private lateinit var balanceTextView: TextView
    private var currentBalance: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        balanceTextView = findViewById(R.id.tv_balance_amount)

        transactionViewModel.allTransactions.observe(this) { transactions ->
            currentBalance = transactions.sumOf { it.amount }
            balanceTextView.text = "$${String.format("%.2f", currentBalance)}"
        }

        val balanceEditButton = findViewById<ImageButton>(R.id.btn_balance_options)
        balanceEditButton.setOnClickListener {
            showEditBalanceDialog()
        }

        val calendarBtn = findViewById<ImageButton>(R.id.btn_calendar)
        val homeBtn = findViewById<ImageButton>(R.id.btn_home)
        val addBtn = findViewById<ImageButton>(R.id.btn_add)
        val settingsButton = findViewById<ImageButton>(R.id.btn_settings)

        settingsButton.setOnClickListener {
            val container = findViewById<View>(R.id.settings_fragment_container)
            container.visibility = if (container.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        calendarBtn.setOnClickListener {
            startActivity(Intent(this, ReportsActivity::class.java))
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

    private fun showEditBalanceDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit Balance")

        val input = EditText(this)
        input.inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        input.setText(currentBalance.toString())
        builder.setView(input)

        builder.setPositiveButton("OK") { dialog, _ ->
            val newBalanceText = input.text.toString()
            val newBalance = newBalanceText.toDoubleOrNull()
            if (newBalance != null) {
                currentBalance = newBalance
                balanceTextView.text = "$${String.format("%.2f", newBalance)}"
            } else {
                Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }
}
