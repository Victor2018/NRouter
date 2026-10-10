package com.victor.neuro.lib.common

import android.app.Application
import com.victor.neuro.router.core.NeuroRouter

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: App
 * Author: Victor
 * Date: 2022/9/28 15:14
 * Description: 
 * -----------------------------------------------------------------
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        NeuroRouter.instance.init(this)
    }
}