package com.victor.module.route

import com.victor.neuro.module.live.LiveActivity
import com.victor.neuro.router.core.NeuroRouter
import com.victor.neuro.router.core.plugin.IRoute

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: LiveRoute
 * Author: Victor
 * Date: 2022/9/28 15:58
 * Description: 
 * -----------------------------------------------------------------
 */

class LiveRoute : IRoute {
    override fun register() {
        NeuroRouter.instance.registerRoute("/live_act", LiveActivity::class.java)
    }
}