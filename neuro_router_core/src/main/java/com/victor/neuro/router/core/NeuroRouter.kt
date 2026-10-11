package com.victor.neuro.router.core

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.victor.neuro.router.core.plugin.PluginConfig
import com.victor.neuro.router.core.plugin.PluginCore

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: SimpleNeuro
 * Author: Victor
 * Date: 2022/9/19 12:21
 * Description: 
 * -----------------------------------------------------------------
 */

class NeuroRouter {

    private val TAG = "NeuroRouter"

    private object Holder {
        val instance = NeuroRouter()
    }

    companion object {
        @JvmStatic
        val instance: NeuroRouter by lazy { Holder.instance }
    }

    private val neuro = Neuro()
    private var soma: Soma? = null

    fun init(application: Application) {
        setBase(Uri.parse(PluginConfig.ROUTE_BASE_URI))
        PluginCore.instance.init(application)
    }

    fun setBase(uri: Uri) {
        soma = object : Soma(uri.toString()) {
            override val schemes = uri.scheme?.let { listOf(it) } ?: emptyList()
            override val hosts = uri.host?.let { listOf(it) } ?: emptyList()
            override val ports = if (uri.port == -1) emptyList() else listOf(uri.port)
        }
    }

    fun <T : Any> registerRoute(routeMap: HashMap<String, Class<T>>) {
        routeMap.forEach {
            addPath(it.key, it.value){
                Dendrite.instance.navigation(it)
            }
        }
    }

    fun <T : Any> registerRoute(expression: String,clazz: Class<T>) {
        addPath(expression, clazz){
            Dendrite.instance.navigation(it)
        }
    }

    fun <T : Any> addPath(expression: String,clazz: Class<T>, action: SignalAction) {
        if (soma == null) {
            setBase(Uri.parse(PluginConfig.ROUTE_BASE_URI))
        }
        val lastIndex = expression.lastIndexOf('/')
        val length = expression.length

        // because specific regex might contain regex to accept / and it will make path count might be increased
        val hasPatternedRegex = expression.contains("<")

        // if it has wildcard, it means that the path count might be more than it should be, saved at index minus
        val usedIndex = if (hasPatternedRegex) {
            lastIndex
        } else {
            length
        }

        var newExpression = expression.substring(0,usedIndex)

        var routePath = "${soma?.id}$newExpression"
        Log.e(TAG,"---------------------------------")
        Log.e(TAG,"connect-expression = $expression")
        Log.e(TAG,"connect-hosts = ${soma?.hosts}")
        Log.e(TAG,"connect-ports = ${soma?.ports}")
        Log.e(TAG,"connect-priority = ${soma?.priority}")
        Log.e(TAG,"connect-schemes = ${soma?.schemes}")
        Log.e(TAG,"connect-id = ${soma?.id}")
        Log.e(TAG,"connect-routePath = $routePath")
        Dendrite.instance.registerRoute(routePath,clazz)

        soma?.let { neuro.connect(it, AxonBranch(expression, action)) }
    }

    fun navigation(url: String,arg: HashMap<String, String>, context: Context?) {
        val path = getQueriesPath(arg)
        val routeUrl = "$url$path"
        navigation(routeUrl, context)
    }

    fun navigation(url: String,arg: List<String>, context: Context?) {
        val path = getVariablesPath(arg)
        val routeUrl = "$url$path"
        navigation(routeUrl, context)
    }

    fun navigation(url: String, context: Context?) {
        navigation(url,Bundle(),context)
    }

    fun navigation(url: String,arg: Bundle,context: Context?) {
        var routeUrl = url
        if (!routeUrl.startsWith(PluginConfig.ROUTE_BASE_URI)) {
            routeUrl = "${PluginConfig.ROUTE_BASE_URI}$url"
        }
        neuro.proceed(0,routeUrl, context,null,arg)
    }

    inline fun <reified T : Any> getNavigation (routePath: String): T? {
        var routeUrl = routePath
        if (!routeUrl.startsWith(PluginConfig.ROUTE_BASE_URI)) {
            routeUrl = "${PluginConfig.ROUTE_BASE_URI}$routePath"
        }
        val navigationCls = Dendrite.instance.getNavigation(routeUrl)
        return if (navigationCls != null && T::class.java.isAssignableFrom(navigationCls)) {
            T::class.java.cast(navigationCls.getDeclaredConstructor().newInstance())
        } else {
            null
        }
    }

    fun clearPaths() {
        neuro.clearConnection()
    }


}