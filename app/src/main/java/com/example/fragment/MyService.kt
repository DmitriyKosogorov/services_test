package com.example.fragment

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import android.content.res.Configuration
import com.example.fragment.CurrentTaskFragment
import com.example.fragment.FinishTaskFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL
import java.util.*
import com.example.fragment.Weather
import org.json.JSONException
import org.json.JSONObject
import android.view.LayoutInflater
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment.databinding.ActivityMainBinding
import androidx.fragment.app.DialogFragment
import org.json.JSONArray


class MyService : Service() {

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mHandler = Handler()
        mRunnable = Runnable {
            GlobalScope.launch(Dispatchers.IO) {
                loadCurrency()
            }
        }
        mHandler.postDelayed(mRunnable, 3000)
        Log.d("test","Service")
        return START_STICKY
    }

    suspend fun loadCurrency() {

        val namers=arrayOf("BTC","XMR","ETH")
        var prices= mutableListOf<String>()
        for(name in namers) {
            val cryptoURL = "https://min-api.cryptocompare.com/data/price?fsym="+name+"&tsyms=USD";
            try {

                val stream = URL(cryptoURL).getContent() as InputStream

                val data = Scanner(stream).nextLine()
                var obj = JSONObject(data)
                Log.d("compleated", obj.toString())
                val price = obj["USD"]
                Log.d("compleated_price", price.toString())
                prices.add(price.toString())

            } catch (e: Exception) {
                Log.d("compleated", "False2")
            }
        }
        Log.d("array", prices.toString())
        var str=""
        GlobalScope.launch(Dispatchers.Main) {

            if (prices.size > 0) {
                MainActivity.textbtc="BTC: " + (prices[0].toString())
            }
            if (prices.size > 1) {
                MainActivity.textxmr="XMR: " + (prices[1].toString())
            }
            if (prices.size > 2) {
                MainActivity.texteth="ETH: " + (prices[2].toString())
            }
        }
    }
}