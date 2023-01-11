package com.victor.neuro.module.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.victor.neuro.router.core.util.Constant
import kotlinx.android.synthetic.main.activity_login.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initData()
    }

    fun initData () {
        var data = intent.getStringExtra(Constant.SCHEMA_QUERIES_KEY)

        var json = JSONObject(data)
        var userName = json.optString("user_name")
        var password = json.optString("password")
        mTvQueries.text = "userName = $userName \npassword = $password"

    }
}