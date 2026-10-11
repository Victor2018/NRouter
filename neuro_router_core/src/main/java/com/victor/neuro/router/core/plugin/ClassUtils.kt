package com.victor.neuro.router.core.plugin

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import dalvik.system.DexFile
import java.io.IOException
import java.util.*

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: ClassUtils
 * Author: Victor
 * Date: 2022/7/21 16:51
 * Description: 
 * -----------------------------------------------------------------
 */

@Suppress("DEPRECATION")
object ClassUtils {
    private const val TAG = "ClassUtils"

    /**
     * 通过指定包名，扫描包下面包含的所有的ClassName
     *
     * @param context     U know
     * @param packageName 包名
     * @return 所有class的集合
     */
    @Throws(PackageManager.NameNotFoundException::class, IOException::class)
    fun getFileNameByPackageName(context: Context, packageName: String): List<String>? {
        val classNames: MutableList<String> = ArrayList()
        val sourcePaths = getSourcePaths(context)
        Log.d(TAG, "Scanning ${sourcePaths.size} source paths for package: $packageName")

        for (path in sourcePaths) {
            var dexFile: DexFile? = null
            try {
                dexFile = DexFile(path)
                val entries = dexFile.entries()
                while (entries.hasMoreElements()) {
                    val className = entries.nextElement()
                    if (className.contains(packageName)) {
                        classNames.add(className)
                    }
                }
                Log.d(TAG, "Successfully scanned path: $path, found ${classNames.size} classes so far")
            } catch (e: Throwable) {
                Log.e(TAG, "Failed to scan path: $path, error: ${e.javaClass.simpleName}: ${e.message}")
            } finally {
                try {
                    dexFile?.close()
                } catch (ignore: Throwable) {
                }
            }
        }

        Log.d(TAG, "Filter ${classNames.size} classes by packageName <$packageName>")
        classNames.forEach { Log.d(TAG, "  Found class: $it") }
        return classNames
    }

    /**
     * get all the dex path
     *
     * @param context the application context
     * @return all the dex path
     * @throws PackageManager.NameNotFoundException
     * @throws IOException
     */
    @Throws(PackageManager.NameNotFoundException::class, IOException::class)
    fun getSourcePaths(context: Context): List<String> {
        val applicationInfo = context.packageManager.getApplicationInfo(context.packageName, 0)
        val sourcePaths: MutableList<String> = ArrayList()
        sourcePaths.add(applicationInfo.sourceDir)

        if (applicationInfo.splitSourceDirs != null) {
            Collections.addAll(sourcePaths, *applicationInfo.splitSourceDirs)
        }

        Log.d(TAG, "Source paths: $sourcePaths")
        return sourcePaths
    }
}
