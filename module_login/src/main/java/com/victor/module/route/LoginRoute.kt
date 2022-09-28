package com.victor.module.route

import com.victor.neuro.module.login.LoginActivity
import com.victor.neuro.router.core.NeuroRouter
import com.victor.neuro.router.core.plugin.IRoute

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: LoginRoute
 * Author: Victor
 * Date: 2022/9/28 15:30
 * Description: 
 * -----------------------------------------------------------------
 */

class LoginRoute : IRoute {
    override fun register() {
        NeuroRouter.instance.registerRoute("/login_act", LoginActivity::class.java)
    }
}