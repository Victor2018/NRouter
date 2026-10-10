package com.victor.neuro.router.core

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: SomaOnly
 * Author: Victor
 * Date: 2022/9/19 14:45
 * Description: 
 * -----------------------------------------------------------------
 */

abstract class SomaOnly(id: String) : Nucleus(id) {

    abstract fun onSomaProcess(signal: Signal)
}