package com.victor.neuro.module.message

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.victor.neuro.module.message.databinding.ActivityMessageBinding

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

class MessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}