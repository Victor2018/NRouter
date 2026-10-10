package com.victor.module.route

import com.victor.neuro.module.me.MeActivity
import com.victor.neuro.router.core.NeuroRouter
import com.victor.neuro.router.core.plugin.IRoute

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: RoutePlugin
 * Author: Victor
 * Date: 2022/9/28 14:28
 * Description: 会被自动扫描加载
 * -----------------------------------------------------------------
 */

class MeRoute : IRoute {

    override fun register() {
        NeuroRouter.instance.registerRoute("/me_act",MeActivity::class.java)
    }

}