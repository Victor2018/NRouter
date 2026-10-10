package com.victor.neuro.router.core

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: SomaFallback
 * Author: Victor
 * Date: 2022/9/19 14:45
 * Description: 
 * -----------------------------------------------------------------
 */

abstract class SomaFallback : SomaOnly(ID) {

    final override val schemes = super.schemes
    final override val hosts = super.hosts
    final override val ports = super.ports
    final override val priority: Int = Int.MAX_VALUE

    companion object {
        const val ID = "*"
    }
}