package com.victor.neuro.router.core

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import com.victor.neuro.router.core.util.Constant
import java.lang.Exception

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: Dendrite
 * Author: Victor
 * Date: 2022/9/19 14:41
 * Description: 树突(位于神经元末端的细分支，接收其他神经元传来的信号)
 * -----------------------------------------------------------------
 */

class Dendrite {
    private val TAG = "Dendrite"

    var routes = HashMap<String,Class<*>?>()
    var signals = HashMap<String,Signal?>()

    private object Holder {
        val instance = Dendrite()
    }

    companion object {
        val instance: Dendrite by lazy { Holder.instance }
    }

    fun <R : Any> registerRoute(routePath: String,clazz: Class<R>) {
        routes[routePath] = clazz
    }

    fun registerSignal(routePath: String,signal: Signal?) {
        signals[routePath] = signal
    }

    fun getNavigation (routePath: String): Class<*>? {
        return routes[routePath]
    }

    fun getSignal (routePath: String): Signal? {
        return signals[routePath]
    }

    fun navigation (context: Context?, clazz: Class<*>?) {
        if (clazz == null) return
        val intent = Intent(context,clazz)
        context?.startActivity(intent)
    }

    fun navigation (signal: Signal?) {
        try {
            val uri = signal?.uri ?: return
            val actualUrl = "${uri.scheme}://${uri.host}${uri.path}"
            if (TextUtils.isEmpty(actualUrl)) {
                throw IllegalStateException("You must register route first.")
            }

            val routeKey = routes.keys.find { key ->
                actualUrl == key || actualUrl.startsWith("$key/")
            }

            val clazz = routeKey?.let { routes[it] }
            if (clazz == null) {
                Log.e(TAG, "Route not found for path: $actualUrl")
                Log.e(TAG, "Registered routes: ${routes.keys}")
                return
            }
            val intent = Intent(signal?.context, clazz)

            // put all variables into intent
            signal?.variables?.forEach { (key, values) ->
                values.firstOrNull()?.let { intent.putExtra(key, it) }
            }

            // put all queries into intent
            signal?.queries?.forEach { (key, values) ->
                values.firstOrNull()?.let { intent.putExtra(key, it) }
            }

            // put all bundle into intent
            signal?.args?.let { intent.putExtras(it) }

            signal?.context?.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Navigation failed: ${e.message}", e)
        }

    }
}