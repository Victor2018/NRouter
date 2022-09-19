package com.victor.neuro.router.core.data

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: OptWave
 * Author: Victor
 * Date: 2022/9/19 12:14
 * Description: 
 * -----------------------------------------------------------------
 */

@Suppress("DEPRECATION")
open class OptWave : Wave() {

    fun optString(key: String): String = if (containsKey(key)) {
        super.get(key)?.firstOrNull() ?: ""
    } else ""

    fun optLong(key: String): Long = if (containsKey(key)) {
        try {
            super.get(key)?.firstOrNull()?.toLong() ?: 0L
        } catch (ex: Exception) {
            0L
        }
    } else 0L

    fun optInt(key: String): Int = if (containsKey(key)) {
        try {
            super.get(key)?.firstOrNull()?.toInt() ?: 0
        } catch (ex: Exception) {
            0
        }
    } else 0

    fun optFloat(key: String): Float = if (containsKey(key)) {
        try {
            super.get(key)?.firstOrNull()?.toFloat() ?: 0.0F
        } catch (ex: Exception) {
            0.0F
        }
    } else 0.0F

    fun optDouble(key: String): Double = if (containsKey(key)) {
        try {
            super.get(key)?.firstOrNull()?.toDouble() ?: 0.0
        } catch (ex: Exception) {
            0.0
        }
    } else 0.0

    fun optBoolean(key: String): Boolean = if (containsKey(key)) {
        val value = super.get(key)?.firstOrNull()
        !value.equals("false", true) && !value.equals("0", true)
    } else false
}
