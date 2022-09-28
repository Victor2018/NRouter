package com.victor.neuro.router.core

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: Soma
 * Author: Victor
 * Date: 2022/9/19 14:44
 * Description: 
 * -----------------------------------------------------------------
 */

abstract class Soma(id: String) : Nucleus(id) {
    internal val noBranchAction: AxonBranch by lazy {
        AxonBranch(EXPRESSION_NO_BRANCH) {
            onProcessNoBranch(it)
        }
    }
    internal val noBranchWithSlashAction: AxonBranch by lazy {
        AxonBranch(EXPRESSION_NO_BRANCH_WITH_SLASH) {
            onProcessNoBranch(it)
        }
    }
    internal val otherBranchAction: AxonBranch by lazy {
        AxonBranch(EXPRESSION_OTHER_BRANCH) {
            onProcessOtherBranch(it)
        }
    }

    // do return false if you want to forward action to AxonBranch
    open fun onSomaProcess(signal: Signal): Boolean = false

    // onSomaProcess must return false to be processed here
    open fun onProcessNoBranch(signal: Signal) = Unit

    // onSomaProcess must return false to be processed here
    open fun onProcessOtherBranch(signal: Signal) = Unit

    companion object {
        const val EXPRESSION_NO_BRANCH = ""
        const val EXPRESSION_NO_BRANCH_WITH_SLASH = "/"
        const val EXPRESSION_OTHER_BRANCH = "/<path:.+>"
    }
}