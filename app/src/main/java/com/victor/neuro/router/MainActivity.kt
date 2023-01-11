package com.victor.neuro.router

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.victor.neuro.router.core.NeuroRouter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    fun initialize () {
        mBtnLogin.setOnClickListener(this)
        mBtnHome.setOnClickListener(this)
        mBtnMessage.setOnClickListener(this)
        mBtnMe.setOnClickListener(this)
        mBtnLive.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mBtnLogin -> {
                val routeUrl = "cherry://com.cherry.router/login_act"
                var data = "{\"user_name\":\"victor\",\"password\": \"423099\"}"
                NeuroRouter.instance.navigation(routeUrl, data,this)
            }
            R.id.mBtnHome -> {
                val routeUrl = "cherry://com.cherry.router/home_act"
                NeuroRouter.instance.navigation(routeUrl, this)
            }
            R.id.mBtnMessage -> {
                val routeUrl = "cherry://com.cherry.router/message_act"
                NeuroRouter.instance.navigation(routeUrl, this)
            }
            R.id.mBtnMe -> {
                val routeUrl = "cherry://com.cherry.router/me_act"
                NeuroRouter.instance.navigation(routeUrl, this)
            }
            R.id.mBtnLive -> {
                val routeUrl = "cherry://com.cherry.router/live_act"
                NeuroRouter.instance.navigation(routeUrl, this)
            }
        }
    }

}