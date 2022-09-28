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
            val temp = Class.forName(pluginName) as Class<IRoute>
            val route = temp.newInstance()
            //注册路由
            route.register()
            Log.d(TAG, "load plugin $route")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}