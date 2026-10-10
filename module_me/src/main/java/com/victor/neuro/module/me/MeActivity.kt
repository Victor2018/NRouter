package com.victor.neuro.module.me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.victor.neuro.module.me.databinding.ActivityMeBinding

class MeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}