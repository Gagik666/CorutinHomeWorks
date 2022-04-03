package com.example.corutinhomework.AppleTask

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.corutinhomework.R
import kotlinx.coroutines.*

class AppleResultFragment : Fragment() {
    private val arg: AppleResultFragmentArgs by navArgs()

    lateinit var progressBar: ProgressBar
    lateinit var pbCircle: ProgressBar

    lateinit var tvInfo: TextView
    lateinit var tvCorrent: TextView
    lateinit var tvP: TextView
    lateinit var tvRNum: TextView

    lateinit var btnAdd: Button
    lateinit var btnRed: Button
    lateinit var btnReset: Button

    lateinit var imgRevresh: ImageView
    lateinit var img1: ImageView
    lateinit var img2: ImageView
    lateinit var img3: ImageView

    var num = 0
    var maxNum = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_apple_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAdd = view.findViewById(R.id.btnAdd)
        btnRed = view.findViewById(R.id.btnRed)
        btnReset = view.findViewById(R.id.btnReset)

        progressBar = view.findViewById(R.id.progressBar)
        pbCircle = view.findViewById(R.id.pbCircle)

        num = arg.num
        maxNum = arg.maxNum

        img1 = view.findViewById(R.id.img1)
        img2 = view.findViewById(R.id.img2)
        img3 = view.findViewById(R.id.img3)
        imgRevresh = view.findViewById(R.id.imgReversh)

        tvRNum = view.findViewById(R.id.tvRNum)
        tvCorrent = view.findViewById(R.id.tvCurrent)
        tvInfo = view.findViewById(R.id.tvInfo)
        tvP = view.findViewById(R.id.tvP)
        tvCorrent.text = num.toString()
        tvRNum.text = num.toString()
        pbCircle.max = maxNum
        btnAdd.setOnClickListener {
            GlobalScope.launch(Dispatchers.Default) {
                loading(1)
            }
        }

        btnRed.setOnClickListener {
            GlobalScope.launch(Dispatchers.Default) {
                loading(2)
            }
        }

        btnReset.setOnClickListener {
            GlobalScope.launch(Dispatchers.Default) {
                loading(3)
            }
        }

    }

    suspend fun  loading(click: Int){
        var i = 0
        withContext(Dispatchers.Main) {
            btnAdd.isClickable = false
            btnRed.isClickable = false
            tvInfo.visibility = View.VISIBLE
            imgRevresh.visibility = View.VISIBLE
            tvCorrent.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
            tvP.visibility = View.VISIBLE
            pbCircle.progress = num
            when(click) {
                1 -> num++
                2 -> num--
                3 -> num = arg.num
                else -> {}
            }
        }
        while (i <= 100) {
            delay(50)
            withContext(Dispatchers.Main) {
                if (i == 2 || i == 60) {
                    img1.visibility = View.VISIBLE
                } else {
                    img1.visibility = View.INVISIBLE
                }
                if (i == 20 || i == 80 )
                    img2.visibility = View.VISIBLE
                else
                    img2.visibility = View.INVISIBLE
                if (i == 40 || i == 99 )
                    img3.visibility = View.VISIBLE
                else
                    img3.visibility = View.INVISIBLE
                when(click) {
                    1 -> tvInfo.text = "Adds apples . . ."
                    2 -> tvInfo.text = "Reduces apples . . ."
                    3 -> tvInfo.text = "Refreshes the box . . ."
                }

                tvP.text = "$i %"
                progressBar.progress = i
                i++
            }
        }
        withContext(Dispatchers.Main) {
            btnAdd.isClickable = true
            btnRed.isClickable = true
            tvInfo.visibility = View.INVISIBLE
            imgRevresh.visibility = View.INVISIBLE


            if (num ==  maxNum || num == 0) {
                btnReset.visibility = View.VISIBLE
                btnAdd.visibility = View.GONE
                btnRed.visibility = View.GONE
            } else {
                btnReset.visibility = View.GONE
                btnAdd.visibility = View.VISIBLE
                btnRed.visibility = View.VISIBLE
            }

            tvCorrent.text = num.toString()
            tvCorrent.visibility = View.VISIBLE
            pbCircle.progress = num
            progressBar.visibility = View.INVISIBLE
            tvP.visibility = View.INVISIBLE
        }
    }

}