package com.example.corutinhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var btn: Button
    lateinit var progressBar: ProgressBar
    lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        btn = findViewById(R.id.btnDownload)
//        progressBar = findViewById(R.id.progressBar)
//        tv = findViewById(R.id.tv)
//
//        btn.setOnClickListener {
//            GlobalScope.launch(Dispatchers.Main) {
//                download()
//            }
//        }
    }

    suspend fun download() {
        withContext(Dispatchers.Main) {
            tv.text = "Preparing"
            btn.isEnabled = false
        }


        for (i in 1 .. 100) {
            delay(100)
            withContext(Dispatchers.Main) {
                tv.text = "$i % DownLoad .."
                progressBar.progress = i
            }
        }

        withContext(Dispatchers.Main) {
            tv.text = "Download Complated"
            btn.isEnabled = true
        }
    }
}