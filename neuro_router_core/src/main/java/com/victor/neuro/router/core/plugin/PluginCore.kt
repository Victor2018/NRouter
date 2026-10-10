package com.victor.neuro.router.core.plugin

import android.app.Application
import android.net.Uri
import com.victor.neuro.router.core.NeuroRouter

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: PluginCore
 * Author: Victor
 * Date: 2022/7/21 16:54
 * Description: 
 * -----------------------------------------------------------------
 */

class PluginCore {
    private var application: Application? = null

    private object Holder { val instance = PluginCore() }

    companion object {
        val  instance: PluginCore by lazy { Holder.instance }
    }

    /**
     * 初始化操作
     *
     * @param application
     */
    fun init(application: Application) {
        //扫描插件映射缓存起来
        PluginProxy.initPlugins(application, PluginConfig.PLUGIN_PACKAGE)
        this.application = application
    }

}