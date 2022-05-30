package com.example.fragment

import android.content.Intent
import android.content.res.Configuration
import com.example.fragment.CurrentTaskFragment
import com.example.fragment.FinishTaskFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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


class MainActivity : AppCompatActivity() {

    var names= mutableListOf<String>("BTC","XMR","ETH")
    var isactive=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("test","built")
    }


    public fun onClick(v: View) {
        if(isactive)
        {
            intent = Intent(this, MyService::class.java)
            val viewbtc=findViewById<TextView>(R.id.btc)
            val vieweth=findViewById<TextView>(R.id.eth)
            val viewxmr=findViewById<TextView>(R.id.xmr)
            viewbtc.text= textbtc
            vieweth.text=texteth
            viewxmr.text= textxmr

        }
        else
        {
            intent = Intent(this, MyService::class.java)
            startService(intent)
            isactive = true
        }
    }

    companion object{
        var textbtc="BTC: "
        var texteth="ETH: "
        var textxmr="XMR: "
    }

}
