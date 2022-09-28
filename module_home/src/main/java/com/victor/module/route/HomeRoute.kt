package com.victor.module.route

import com.victor.neuro.module.home.HomeActivity
import com.victor.neuro.router.core.NeuroRouter
import com.victor.neuro.router.core.plugin.IRoute

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: HomeRoute
 * Author: Victor
 * Date: 2022/9/28 15:29
 * Description: 
 * -----------------------------------------------------------------
 */

class HomeRoute : IRoute {
    override fun register() {
        NeuroRouter.instance.registerRoute("/home_act", HomeActivity::class.java)
    }
}