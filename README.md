# NRouter
神经网络路由-你的组件化从这里开始
- ![image](https://s2.loli.net/2022/09/28/5wABJtvS1QDWxZi.jpg)

Usage

- Step 1. Add the JitPack repository to your build file
    ```
    allprojects {
        repositories {
        ...
        maven { url "https://jitpack.io" }
        }
    }
    ```

- Step 2. Add the dependency
  ```
  dependencies {
    implementation 'com.github.Victor2018:NRouter:latestVersion'
  }
  ```

- Step 3
  init sdk in application for this:
  ```
  NeuroRouter.instance.init(this)
  ```
  
- Step 4
  create Route  at package com.victor.module.route for this:

    ```
    class LoginRoute : IRoute {
        override fun register() {
            NeuroRouter.instance.registerRoute("/login_act", LoginActivity::class.java)
        }
    }
    ```

- Step 5
  navigation url for this:

    ```
    val routeUrl = "cherry://com.cherry.router/login_act"
    var data = "{\"user_name\":\"victor\",\"password\": \"423099\"}"
    NeuroRouter.instance.navigation(routeUrl, data,this)
    ```
- Step 6
receive queries for this:
    ```
    var data = intent.getStringExtra(Constant.SCHEMA_QUERIES_KEY)
    var json = JSONObject(data)
    var userName = json.optString("user_name")
    var password = json.optString("password")
    ```
  
## NRouter 神经网络路由实现组件化最佳实践：
https://github.com/Victor2018/Cherry

## 2023/01/11-v2.0.0
- 1，支持有参路由

## 2022/09/28-v1.0.0
- 1，支持无参路由

# 关注开发者：
- 邮箱： victor423099@gmail.com
- 新浪微博
- ![image](https://github.com/Victor2018/AppUpdateLib/raw/master/SrceenShot/sina_weibo.jpg)

## License

Copyright (c) 2017 Victor

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
