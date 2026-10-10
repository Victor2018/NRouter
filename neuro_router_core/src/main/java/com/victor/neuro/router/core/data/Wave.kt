package com.victor.neuro.router.core.data

import java.util.*

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: Wave
 * Author: Victor
 * Date: 2022/9/19 12:13
 * Description: 
 * -----------------------------------------------------------------
 */

open class Wave : HashMap<String, List<String>>() {

    @Deprecated("Don't use default's for preventing null value", ReplaceWith("super.get(key)", "java.util.HashMap"))
    override fun get(key: String): List<String>? = super.get(key)

    fun put(key: String, value: String): List<String>? {
        return super.put(key, Arrays.asList(value))
    }

    fun getString(key: String): String? = if (containsKey(key)) {
        super.get(key)?.firstOrNull()
    } else null

    fun getLong(key: String): Long? = if (containsKey(key)) {
        try {
            super.get(key)?.firstOrNull()?.toLong()
        } catch (ex: Exception) {
            0L
        }
    } else null

    fun getInt(key: String): Int? = if (containsKey(key)) {
        try {
            super.get(key)?.firstOrNull()?.toInt()
        } catch (ex: Exception) {
            0
        }
    } else null

    fun getFloat(key: String): Float? = if (containsKey(key)) {
        try {
            super.get(key)?.firstOrNull()?.toFloat()
        } catch (ex: Exception) {
            0.0F
        }
    } else null

    fun getDouble(key: String): Double? = if (containsKey(key)) {
        try {
            super.get(key)?.firstOrNull()?.toDouble()
        } catch (ex: Exception) {
            0.0
        }
    } else null

    fun getBoolean(key: String): Boolean? = if (containsKey(key)) {
        val value = super.get(key)?.firstOrNull()
        !value.equals("false", true) && !value.equals("0", true)
    } else null
}