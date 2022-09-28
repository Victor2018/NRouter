package com.victor.neuro.router.core

import android.content.Context
import android.content.Intent
import android.text.TextUtils
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

    var routes = HashMap<String,Class<*>>()

    private object Holder {
        val instance = Dendrite()
    }

    companion object {
        val instance: Dendrite by lazy { Holder.instance }
    }

    fun registerRoute(routePath: String,clazz: Class<*>) {
        routes[routePath] = clazz
    }

    fun getNavigation (routePath: String): Class<*>? {
        return routes[routePath]
    }

    fun navigation (context: Context?, clazz: Class<*>?) {
        if (clazz == null) return
        var intent = Intent(context,clazz)
        context?.startActivity(intent)
    }

    fun navigation (signal: Signal?) {
        try {
            var routePath = "${signal?.uri?.scheme}://${signal?.uri?.host}${signal?.uri?.path}"
            if (TextUtils.isEmpty(routePath)) {
                throw IllegalStateException("You must register route first.")
            }
            var intent = Intent(signal?.context,getNavigation(routePath))
            signal?.context?.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}