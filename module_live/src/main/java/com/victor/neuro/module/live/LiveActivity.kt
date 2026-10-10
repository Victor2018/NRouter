package com.victor.neuro.module.live

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.victor.neuro.module.live.databinding.ActivityLiveBinding
import com.victor.neuro.router.annotation.Route

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: LiveActivity
 * Author: Victor
 * Date: 2022/9/28 15:57
 * Description: 
 * -----------------------------------------------------------------
 */

@Route(path = "/live_act")
class LiveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}