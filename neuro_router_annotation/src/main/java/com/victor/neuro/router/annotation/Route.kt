package com.victor.neuro.router.annotation

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: Route
 * Author: Victor
 * Date: 2022/9/19
 * Description: 路由注解，标注在 Activity 上实现自动注册
 * -----------------------------------------------------------------
 */

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class Route(
    val path: String,
    val exported: Boolean = true
)
