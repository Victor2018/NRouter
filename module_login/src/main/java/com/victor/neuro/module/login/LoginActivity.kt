package com.victor.neuro.module.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.victor.neuro.module.login.databinding.ActivityLoginBinding
import com.victor.neuro.router.annotation.Route
import com.victor.neuro.router.core.util.Constant
import org.json.JSONObject

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: LoginActivity
 * Author: Victor
 * Date: 2022/9/16 17:24
 * Description: 
 * -----------------------------------------------------------------
 */

@Route(path = "/login_act")
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
    }

    fun initData () {
        val userName = intent.getStringExtra("user_name")
        val password = intent.getStringExtra("password")

        binding.mTvQueries.text = "userName = $userName \npassword = $password"

    }
}