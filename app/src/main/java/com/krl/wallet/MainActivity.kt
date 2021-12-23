package com.krl.wallet

import android.os.Bundle
import android.os.CountDownTimer
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.krl.wallet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var wallets: List<WalletModel> = listOf()
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        getData()
        setAdapter()
        startTimer()
    }

    private fun initView() {
        binding.ivRefresh.setOnClickListener {
            timer?.cancel()
            getData()
            setAdapter()
            startTimer()
        }

        binding.vpWallet.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                var currentWallet = wallets[position]

                binding.tvBalance.text = "₺" + Helper.currencyFormat(currentWallet.balance.value)
                binding.tvPendingBalance.text =
                    "₺" + Helper.currencyFormat(currentWallet.pendingBalance.value)
            }
        })
    }

    private fun setAdapter() {
        var adapter = WalletAdapter(wallets, this)
        binding.vpWallet.adapter = adapter
        binding.vpWallet.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.dotsIndicator.setViewPager2(binding.vpWallet)
    }

    private fun getData() {
        wallets = getJsonData()
    }

    private fun startTimer() {
        var time = 0
        timer = object : CountDownTimer(1000, 1000) {
            override fun onTick(l: Long) {
                binding.tvElapsedTime.text = "$time seconds ago"
                time += 1
            }

            override fun onFinish() {
                start()
            }
        }

        timer?.start()
    }

    private fun getJsonData(): List<WalletModel> = readRawJson(R.raw.wallet)

    private inline fun <reified T> readRawJson(@RawRes rawResId: Int): T {
        resources.openRawResource(rawResId).bufferedReader().use {
            return Gson().fromJson<T>(it, object : TypeToken<T>() {}.type)
        }
    }
}