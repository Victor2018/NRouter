package com.victor.neuro.router.core.data

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: OptWaves
 * Author: Victor
 * Date: 2022/9/19 12:16
 * Description: 
 * -----------------------------------------------------------------
 */

@Suppress("DEPRECATION")
class OptWaves : Waves() {

    fun optStringList(key: String): List<String> = if (containsKey(key)) {
        super.get(key) ?: emptyList()
    } else emptyList()

    fun optLongList(key: String): List<Long> = if (containsKey(key)) {
        try {
            super.get(key)?.map { it.toLong() } ?: emptyList()
        } catch (ex: Exception) {
            emptyList<Long>()
        }
    } else emptyList()

    fun optIntList(key: String): List<Int> = if (containsKey(key)) {
        try {
            super.get(key)?.map { it.toInt() } ?: emptyList()
        } catch (ex: Exception) {
            emptyList<Int>()
        }
    } else emptyList()

    fun optFloatList(key: String): List<Float> = if (containsKey(key)) {
        try {
            super.get(key)?.map { it.toFloat() } ?: emptyList()
        } catch (ex: Exception) {
            emptyList<Float>()
        }
    } else emptyList()

    fun optDoubleList(key: String): List<Double> = if (containsKey(key)) {
        try {
            super.get(key)?.map { it.toDouble() } ?: emptyList()
        } catch (ex: Exception) {
            emptyList<Double>()
        }
    } else emptyList()

    fun optBooleanList(key: String): List<Boolean> = if (containsKey(key)) {
        super.get(key)?.map { !it.equals("false", true) && !it.equals("0", true) } ?: emptyList()
    } else emptyList()
}