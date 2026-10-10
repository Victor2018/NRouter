package com.victor.neuro.router.core.data

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: Waves
 * Author: Victor
 * Date: 2022/9/19 12:15
 * Description: 
 * -----------------------------------------------------------------
 */

@Suppress("DEPRECATION")
open class Waves : OptWave() {

    fun getStringList(key: String): List<String>? = if (containsKey(key)) {
        super.get(key)
    } else null

    fun getLongList(key: String): List<Long>? = if (containsKey(key)) {
        try {
            super.get(key)?.map { it.toLong() }
        } catch (ex: Exception) {
            emptyList<Long>()
        }
    } else null

    fun getIntList(key: String): List<Int>? = if (containsKey(key)) {
        try {
            super.get(key)?.map { it.toInt() }
        } catch (ex: Exception) {
            emptyList<Int>()
        }
    } else null

    fun getFloatList(key: String): List<Float>? = if (containsKey(key)) {
        try {
            super.get(key)?.map { it.toFloat() }
        } catch (ex: Exception) {
            emptyList<Float>()
        }
    } else null

    fun getDoubleList(key: String): List<Double>? = if (containsKey(key)) {
        try {
            super.get(key)?.map { it.toDouble() }
        } catch (ex: Exception) {
            emptyList<Double>()
        }
    } else null

    fun getBooleanList(key: String): List<Boolean>? = if (containsKey(key)) {
        super.get(key)?.map { !it.equals("false", true) && !it.equals("0", true) } ?: emptyList()
    } else null
}
