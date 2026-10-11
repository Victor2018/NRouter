package com.victor.neuro.router.core.plugin

import android.app.Application
import android.util.Log
import java.util.ServiceLoader

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
        private const val TAG = "PluginCore"
    }

    /**
     * 初始化操作
     *
     * @param application
     */
    fun init(application: Application) {
        // 1. 优先使用 ServiceLoader 发现 KSP 生成的路由类（更可靠）
        loadViaServiceLoader()
        // 2. 扫描手动注册的路由插件（DexFile 方式）
        PluginProxy.initPlugins(application, PluginConfig.PLUGIN_PACKAGE)
        // 3. 扫描注解处理器生成的路由类（DexFile 方式，作为兆底）
        PluginProxy.initPlugins(application, PluginConfig.GENERATED_PACKAGE)
        this.application = application
    }

    /**
     * 通过 ServiceLoader 发现 IRoute 实现类
     * 依赖 KSP 生成的 META-INF/services/com.victor.neuro.router.core.plugin.IRoute 文件
     */
    private fun loadViaServiceLoader() {
        try {
            Log.d(TAG, "Loading routes via ServiceLoader...")
            val serviceLoader = ServiceLoader.load(IRoute::class.java)
            var count = 0
            for (route in serviceLoader) {
                try {
                    route.register()
                    count++
                    Log.d(TAG, "ServiceLoader loaded: ${route.javaClass.name}")
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to register route from ServiceLoader: ${route.javaClass.name}", e)
                }
            }
            Log.d(TAG, "ServiceLoader loaded $count route plugins")
        } catch (e: Exception) {
            Log.e(TAG, "ServiceLoader discovery failed: ${e.javaClass.simpleName}: ${e.message}")
        }
    }

}