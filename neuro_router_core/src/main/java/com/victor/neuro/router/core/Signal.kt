package com.victor.neuro.router.core

import android.content.Context
import android.net.Uri
import android.os.Bundle
import com.victor.neuro.router.core.data.OptWave
import com.victor.neuro.router.core.data.OptWaves

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: Signal
 * Author: Victor
 * Date: 2022/9/19 12:12
 * Description: 
 * -----------------------------------------------------------------
 */

open class Signal(
    val context: Context?,
    var routeType: Int,
    val uri: Uri,
    val url: String,
    val variables: OptWave,
    val queries: OptWaves,
    val fragment: String?,
    val args: Bundle?
)