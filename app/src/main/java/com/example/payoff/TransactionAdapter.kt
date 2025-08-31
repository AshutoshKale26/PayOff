package com.example.payoff

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

data class Transaction(
    val profilePicResId: Int,   // Resource ID for profile picture
    val name: String,
    val date: String,
    val amount: String,
    val isSent: Boolean // true if money sent, false if received
)

class TransactionAdapter(private val transactionList: List<Transaction>) :
RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profilePic: ImageView = itemView.findViewById(R.id.user_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvDate: TextView = itemView.findViewById(R.id.tv_date)
        val tvAmount: TextView = itemView.findViewById(R.id.tv_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionList[position]
        // Set profile picture (using a static resource here, can be dynamic)
        holder.profilePic.setImageResource(transaction.profilePicResId)

        // Set name
        holder.tvName.text = transaction.name

        // Set date
        holder.tvDate.text = transaction.date

        // Set amount and change color based on whether it's sent or received
        if (transaction.isSent) {
            holder.tvAmount.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.colorError))
            holder.tvAmount.text = "-₹${transaction.amount}"
        } else {
            holder.tvAmount.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.Green))
            holder.tvAmount.text = "+₹${transaction.amount}"
        }
    }

    override fun getItemCount(): Int = transactionList.size

}