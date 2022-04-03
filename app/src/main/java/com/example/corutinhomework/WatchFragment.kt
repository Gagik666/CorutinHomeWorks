package com.example.corutinhomework

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

import kotlinx.coroutines.*

class WatchFragment : Fragment() {
   lateinit var pbMinute: ProgressBar
   lateinit var pbSeconds: ProgressBar
   lateinit var pbMiSeconds: ProgressBar
   lateinit var tvMin: TextView
   lateinit var tvSec: TextView
   lateinit var tvMiSec: TextView
   lateinit var btnStart: Button
   lateinit var btnStop: Button
   lateinit var job1: Job
   lateinit var job2: Job
   lateinit var job3: Job

    var min = 0
    var sec = 0
    var miSec = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_watch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pbSeconds = view.findViewById(R.id.pbSeconds)
        pbMiSeconds = view.findViewById(R.id.pbMISecund)
        pbMinute =  view.findViewById(R.id.pbMinute)
        tvMin = view.findViewById(R.id.tvMin)
        tvSec = view.findViewById(R.id.tvSeconds)
        tvMiSec = view.findViewById(R.id.tvMiSeconds)
        btnStart = view.findViewById(R.id.btnStr)
        btnStop = view.findViewById(R.id.btnStop)

        btnStart.setOnClickListener {

             job1 = GlobalScope.launch(Dispatchers.Default) {
                seconds()
            }

             job2 = GlobalScope.launch(Dispatchers.Default) {
                minute()
            }

            job3 = GlobalScope.launch(Dispatchers.Default) {
                miSeconds()
            }

            btnStart.visibility = View.INVISIBLE
            btnStop.visibility = View.VISIBLE
        }

       btnStop.setOnClickListener {
           job1.cancel()
           job2.cancel()
           job3.cancel()

           btnStart.visibility = View.VISIBLE
           btnStop.visibility = View.INVISIBLE
       }
    }

    suspend fun miSeconds() {
        while (true) {
            delay(10)
            withContext(Dispatchers.Main) {
                miSec++
                pbMiSeconds.progress = miSec
                tvMiSec.text = miSec.toString()
                if (miSec == 60) miSec = 0
            }
        }
    }

    suspend fun seconds() {

        while (true) {
            delay(1000)
            withContext(Dispatchers.Main) {
                sec++
                pbSeconds.progress = sec
                tvSec.text = sec.toString()
                if (sec == 60) sec = 0
            }
        }


    }

    suspend fun minute() {
        while (true) {
            delay(60000)
            withContext(Dispatchers.Main) {
                min++
                pbMinute.progress = min
                tvMin.text = min.toString()
                if (min == 60) min = 0
            }
        }
    }

}