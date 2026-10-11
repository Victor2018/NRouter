package com.victor.neuro.router

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.victor.neuro.module.message.MessageActivity
import com.victor.neuro.router.core.NeuroRouter
import com.victor.neuro.router.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    fun initialize () {
        binding.mClContent.mBtnLogin.setOnClickListener(this)
        binding.mClContent.mBtnHome.setOnClickListener(this)
        binding.mClContent.mBtnMessage.setOnClickListener(this)
        binding.mClContent.mBtnMe.setOnClickListener(this)
        binding.mClContent.mBtnLive.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mBtnLogin -> {
                val routeUrl = "cherry://com.cherry.router/login_act"
//                val routeUrl = "cherry://com.cherry.router/login_act?user_name=victor&password=423099"
                val map = HashMap<String,String>()
                map["user_name"] = "victor"
                map["password"] = "423099"
                NeuroRouter.instance.navigation(routeUrl,map,this)
            }
            R.id.mBtnHome -> {
                val routeUrl = "cherry://com.cherry.router/home_act"
                NeuroRouter.instance.navigation(routeUrl, this)
            }
            R.id.mBtnMessage -> {
                val routeUrl = "cherry://com.cherry.router/message_act"
//                val routeUrl = "cherry://com.cherry.router/message_act/423099"
                NeuroRouter.instance.navigation(routeUrl, listOf("423099"), this)
            }
            R.id.mBtnMe -> {
                val routeUrl = "cherry://com.cherry.router/me_act"
                NeuroRouter.instance.navigation(routeUrl, this)
            }
            R.id.mBtnLive -> {
                val routeUrl = "cherry://com.cherry.router/live_act"
                val args = Bundle()
                args.putString("live_time","2026-10-11 18:08")
                args.putString("live_content","神经网络路由")
                NeuroRouter.instance.navigation(routeUrl, args,this)
            }
        }
    }
}