package com.victor.neuro.module.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.victor.neuro.module.login.databinding.ActivityLoginBinding
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

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
    }

    fun initData () {
        var data = intent.getStringExtra(Constant.SCHEMA_QUERIES_KEY)

        var json = JSONObject(data)
        var userName = json.optString("user_name")
        var password = json.optString("password")
        binding.mTvQueries.text = "userName = $userName \npassword = $password"

    }
}