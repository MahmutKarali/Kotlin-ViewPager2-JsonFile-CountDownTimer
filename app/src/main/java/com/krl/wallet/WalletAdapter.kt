package com.krl.wallet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WalletAdapter(private val list: List<WalletModel>, var context: Context) :
    RecyclerView.Adapter<WalletAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivWallet: ImageView = itemView.findViewById(R.id.iv_wallet)
        val tvCardNo: TextView = itemView.findViewById(R.id.tv_card_number)
        val tvCvvNo: TextView = itemView.findViewById(R.id.tv_cvv_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        return Pager2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.wallet_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        var wallet = list[position]
        //Picasso.with(context).load(wallet.image).into(holder.ivWallet)
        if (wallet.number.length == 16) {
            holder.tvCardNo.text =
                wallet.number.substring(0, 4) + " " + wallet.number.substring(4, 8) +
                        " " + wallet.number.substring(8, 12) + " " + wallet.number.substring(12, 16)
        }
        holder.tvCvvNo.text = "CVV " + wallet.cvv
    }
}