
package com.example.ledgefy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ledgefy.R
import com.example.ledgefy.model.Transaction

class ReportAdapter(transactions: List<Transaction>) :
    RecyclerView.Adapter<ReportAdapter.ViewHolder>() {

    private val transactions = transactions.toMutableList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_transaction_title)
        val amount: TextView = itemView.findViewById(R.id.tv_transaction_amount)
        val date: TextView = itemView.findViewById(R.id.tv_transaction_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.report_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.title.text = transaction.description
        holder.amount.text = if (transaction.amount >= 0) "+ $${transaction.amount}" else "- $${-transaction.amount}"
        holder.date.text = transaction.date

        val green = ContextCompat.getColor(holder.itemView.context, R.color.secondaryGreen)
        val red = ContextCompat.getColor(holder.itemView.context, android.R.color.holo_red_dark)
        val color = if (transaction.amount >= 0) green else red

        holder.amount.setTextColor(color)
        holder.date.setTextColor(color)
    }

    override fun getItemCount(): Int = transactions.size

    fun updateList(newList: List<Transaction>) {
        transactions.clear()
        transactions.addAll(newList)
        notifyDataSetChanged()
    }
}
