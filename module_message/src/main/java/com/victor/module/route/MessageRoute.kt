package com.victor.module.route

import com.victor.neuro.module.message.MessageActivity
import com.victor.neuro.router.core.NeuroRouter
import com.victor.neuro.router.core.plugin.IRoute

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: MessageRoute
 * Author: Victor
 * Date: 2022/9/28 15:31
 * Description: 
 * -----------------------------------------------------------------
 */

class MessageRoute : IRoute {
    override fun register() {
        NeuroRouter.instance.registerRoute("/message_act", MessageActivity::class.java)
    }
}