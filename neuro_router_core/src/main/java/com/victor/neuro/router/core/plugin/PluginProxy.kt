package com.victor.neuro.router.core.plugin

import android.app.Application
import android.content.pm.PackageManager
import android.util.Log
import java.io.IOException

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: PluginProxy
 * Author: Victor
 * Date: 2022/7/21 16:49
 * Description: 
 * -----------------------------------------------------------------
 */

object PluginProxy {
    private val TAG = "PluginProxy"

    /**
     * 初始化注册的插件
     * 通过 DexFile 扫描 com.victor.module.route 包下的 IRoute 实现
     * 注解处理器生成的 IRoute 实现类也在此包路径下，会被一并扫描到
     *
     * @param context
     * @param packageName
     */
    fun initPlugins(context: Application, packageName: String?) {
        Log.d(TAG,"initPlugins()......packageName = $packageName")
        val time = System.currentTimeMillis()
        var pluginTables: List<String?>? = null
        try {
            pluginTables = ClassUtils.getFileNameByPackageName(context, packageName ?: "")
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Log.d(TAG,"initPlugins()......pluginTables = $pluginTables")
        Log.d(TAG,"initPlugins()......pluginCount = ${pluginTables?.size}")
        //扫描插件加载
        pluginTables?.forEach {
            Log.d(TAG,"initPlugins()......pluginName = $it")
            registerRoute(it)
        }
        Log.d(TAG,"initPlugins()......耗时${System.currentTimeMillis() - time}毫秒")
    }

    /**
     * 注册路由
     * @param pluginName
     */
    private fun registerRoute(pluginName: String?) {
        try {
            Log.d(TAG, "Loading route class: $pluginName")
            val temp = Class.forName(pluginName) as Class<IRoute>
            val route = temp.newInstance()
            //注册路由
            route.register()
            Log.d(TAG, "Successfully loaded route: $pluginName")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to load route class: $pluginName, error: ${e.javaClass.simpleName}: ${e.message}", e)
        }
    }

}
