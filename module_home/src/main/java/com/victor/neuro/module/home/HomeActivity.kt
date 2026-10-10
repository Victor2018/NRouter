package com.victor.neuro.module.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.victor.neuro.module.home.databinding.ActivityHomeBinding
import com.victor.neuro.router.annotation.Route

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: HomeActivity
 * Author: Victor
 * Date: 2022/9/28 15:27
 * Description: 
 * -----------------------------------------------------------------
 */

@Route(path = "/home_act")
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}