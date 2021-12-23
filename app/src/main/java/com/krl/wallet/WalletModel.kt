package com.krl.wallet

data class WalletModel(
    var image: String,
    var number: String,
    var cvv: String,
    var balance: BalanceModel,
    var pendingBalance: BalanceModel
)

data class BalanceModel(
    var value: String,
    var currency: String,
)