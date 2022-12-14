package com.victor.neuro.router.core.plugin

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import dalvik.system.DexFile
import java.io.File
import java.io.IOException
import java.util.*
import java.util.regex.Pattern

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

object ClassUtils {
    private const val EXTRACTED_NAME_EXT = ".classes"
    private const val EXTRACTED_SUFFIX = ".zip"

    private val SECONDARY_FOLDER_NAME = "code_cache" + File.separator + "secondary-dexes"

    private const val PREFS_FILE = "multidex.version"
    private const val KEY_DEX_NUMBER = "dex.number"

    private const val VM_WITH_MULTIDEX_VERSION_MAJOR = 2
    private const val VM_WITH_MULTIDEX_VERSION_MINOR = 1

    private fun getMultiDexPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            PREFS_FILE,
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) Context.MODE_PRIVATE else Context.MODE_PRIVATE or Context.MODE_MULTI_PROCESS
        )
    }

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
        for (path in getSourcePaths(context)) {
            var dexfile: DexFile? = null
            try {
                dexfile = if (path.endsWith(EXTRACTED_SUFFIX)) {
                    //NOT use new DexFile(path), because it will throw "permission error in /data/dalvik-cache"
                    DexFile.loadDex(path, "$path.tmp", 0)
                } else {
                    DexFile(path)
                }
                val dexEntries = dexfile!!.entries()
                while (dexEntries.hasMoreElements()) {
                    val className = dexEntries.nextElement()
                    if (className.contains(packageName)) {
                        classNames.add(className)
                    }
                }
            } catch (ignore: Throwable) {
                Log.e("ARouter", "Scan map file in dex files made error.", ignore)
            } finally {
                if (null != dexfile) {
                    try {
                        dexfile.close()
                    } catch (ignore: Throwable) {
                    }
                }
            }
        }
        Log.d(
            "ARouter",
            "Filter " + classNames.size + " classes by packageName <" + packageName + ">"
        )
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
        val sourceApk = File(applicationInfo.sourceDir)
        val sourcePaths: MutableList<String> = ArrayList()
        sourcePaths.add(applicationInfo.sourceDir) //add the default apk path

        //the prefix of extracted file, ie: test.classes
        val extractedFilePrefix = sourceApk.name + EXTRACTED_NAME_EXT

//        如果VM已经支持了MultiDex，就不要去Secondary Folder加载 Classesx.zip了，那里已经么有了
//        通过是否存在sp中的multidex.version是不准确的，因为从低版本升级上来的用户，是包含这个sp配置的
        if (!isVMMultidexCapable()) {
            //the total dex numbers
            val totalDexNumber = getMultiDexPreferences(context).getInt(KEY_DEX_NUMBER, 1)
            val dexDir = File(applicationInfo.dataDir, SECONDARY_FOLDER_NAME)
            for (secondaryNumber in 2..totalDexNumber) {
                //for each dex file, ie: test.classes2.zip, test.classes3.zip...
                val fileName = extractedFilePrefix + secondaryNumber + EXTRACTED_SUFFIX
                val extractedFile = File(dexDir, fileName)
                if (extractedFile.isFile) {
                    sourcePaths.add(extractedFile.absolutePath)
                    //we ignore the verify zip part
                } else {
                    throw IOException("Missing extracted secondary dex file '" + extractedFile.path + "'")
                }
            }
        }
        return sourcePaths
    }

    /**
     * Get instant run dex path, used to catch the branch usingApkSplits=false.
     */
    private fun tryLoadInstantRunDexFile(applicationInfo: ApplicationInfo): List<String>? {
        val instantRunSourcePaths: MutableList<String> = ArrayList()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && null != applicationInfo.splitSourceDirs) {
            // add the splite apk, normally for InstantRun, and newest version.
            instantRunSourcePaths.addAll(Arrays.asList(*applicationInfo.splitSourceDirs))
            Log.d("ARouter", "Found InstantRun support")
        } else {
            try {
                // This man is reflection from Google instant run sdk, he will tell me where the dex files go.
                val pathsByInstantRun = Class.forName("com.android.tools.fd.runtime.Paths")
                val getDexFileDirectory = pathsByInstantRun.getMethod(
                    "getDexFileDirectory",
                    String::class.java
                )
                val instantRunDexPath =
                    getDexFileDirectory.invoke(null, applicationInfo.packageName) as String
                val instantRunFilePath = File(instantRunDexPath)
                if (instantRunFilePath.exists() && instantRunFilePath.isDirectory) {
                    val dexFile = instantRunFilePath.listFiles()
                    for (file in dexFile) {
                        if (null != file && file.exists() && file.isFile && file.name.endsWith(".dex")) {
                            instantRunSourcePaths.add(file.absolutePath)
                        }
                    }
                    Log.d("ARouter", "Found InstantRun support")
                }
            } catch (e: Exception) {
                Log.e("ARouter", "InstantRun support error, " + e.message)
            }
        }
        return instantRunSourcePaths
    }

    /**
     * Identifies if the current VM has a native support for multidex, meaning there is no need for
     * additional installation by this library.
     *
     * @return true if the VM handles multidex
     */
    private fun isVMMultidexCapable(): Boolean {
        var isMultidexCapable = false
        var vmName: String? = null
        try {
            if (isYunOS()) {    // YunOS需要特殊判断
                vmName = "'YunOS'"
                isMultidexCapable =
                    Integer.valueOf(System.getProperty("ro.build.version.sdk")) >= 21
            } else {    // 非YunOS原生Android
                vmName = "'Android'"
                val versionString = System.getProperty("java.vm.version")
                if (versionString != null) {
                    val matcher =
                        Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(versionString)
                    if (matcher.matches()) {
                        try {
                            val major = matcher.group(1).toInt()
                            val minor = matcher.group(2).toInt()
                            isMultidexCapable = (major > VM_WITH_MULTIDEX_VERSION_MAJOR
                                    || (major == VM_WITH_MULTIDEX_VERSION_MAJOR
                                    && minor >= VM_WITH_MULTIDEX_VERSION_MINOR))
                        } catch (ignore: NumberFormatException) {
                            // let isMultidexCapable be false
                        }
                    }
                }
            }
        } catch (ignore: Exception) {
        }
        Log.i(
            "galaxy",
            "VM with name " + vmName + if (isMultidexCapable) " has multidex support" else " does not have multidex support"
        )
        return isMultidexCapable
    }

    /**
     * 判断系统是否为YunOS系统
     */
    private fun isYunOS(): Boolean {
        return try {
            val version = System.getProperty("ro.yunos.version")
            val vmName = System.getProperty("java.vm.name")
            (vmName != null && vmName.lowercase(Locale.getDefault()).contains("lemur")
                    || version != null && version.trim { it <= ' ' }.length > 0)
        } catch (ignore: Exception) {
            false
        }
    }
}