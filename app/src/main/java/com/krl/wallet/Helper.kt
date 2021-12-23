package com.krl.wallet

import java.text.DecimalFormat

object Helper {
    fun currencyFormat(amount: String): String? {
        val formatter = DecimalFormat("###,###,##0.00")
        return formatter.format(amount.toDouble())
    }
}