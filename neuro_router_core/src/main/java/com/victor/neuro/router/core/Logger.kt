package com.victor.neuro.router.core

import android.util.Log

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: Logger
 * Author: Victor
 * Date: 2022/9/19 12:20
 * Description: 
 * -----------------------------------------------------------------
 */

interface Logger {

    fun onRoutingUrl(url: String) {
        Log.i(Neuro.TAG, "Routing url $url")
    }

    fun onUrlHasResult(url: String, nucleus: Nucleus, branch: AxonBranch?) {
        Log.i(Neuro.TAG, "Routing via $nucleus and $branch")
    }

    fun onUrlHasNoResult(url: String) {
        Log.e(Neuro.TAG, "Url $url has no result")
    }

    fun onNucleusReturnedFalse(url: String) {
        Log.i(Neuro.TAG, "Nucleus transporter returned false")
    }

    fun onFindRouteStarted(url: String) = Unit

    fun onFindRouteFinished(url: String) = Unit

    companion object {
        val DEFAULT: Logger = object : Logger {}
    }
}