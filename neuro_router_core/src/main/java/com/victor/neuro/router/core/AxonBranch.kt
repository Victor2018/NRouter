package com.victor.neuro.router.core

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: AxonBranch
 * Author: Victor
 * Date: 2022/9/19 12:19
 * Description: 
 * -----------------------------------------------------------------
 */

class AxonBranch private constructor(
    private val id: String?,
    private val priority: Int,
    val expression: String,
    val action: SignalAction?
) : Comparable<AxonBranch> {

    constructor(
        expression: String,
        action: SignalAction?
    ) : this(null, DEFAULT_PRIORITY, expression, action)

    constructor(
        id: String,
        expression: String,
        priority: Int = DEFAULT_PRIORITY,
        action: SignalAction?
    ) : this(id, priority, expression, action)

    private val comparedPattern: String by lazy {

        // because space's ascii number is smaller than all supported character in URL
        val char = " "
        expression.replace(ANY_VARIABLE, char)
    }

    private val pattern: Regex by lazy {
        Regex(expression.toPattern())
    }

    internal fun isMatch(path: String?): Boolean {
        val cleanPath = path ?: ""

        // if pattern is longer than path, it's an imposible match
        if (comparedPattern.length > cleanPath.length) return false

        return pattern.matches(cleanPath)
    }

    /**
     * Priorities:
     * #1 lowest priority number
     * #2 longest pattern length
     * #3 alphabetical by pattern
     * #4 alphabetical by id
     */
    override fun compareTo(other: AxonBranch): Int {
        val priority1 = priority
        val priority2 = other.priority

        return if (priority1 == priority2) {
            val patternLength1 = comparedPattern.length
            val patternLength2 = other.comparedPattern.length

            if (patternLength1 == patternLength2) {
                val pattern1 = comparedPattern
                val pattern2 = other.comparedPattern

                if (pattern1 == pattern2) {
                    val id1 = id
                    val id2 = other.id

                    if (id1 != null && id2 != null) {
                        id1.compareTo(id2)
                    } else {
                        id2.orEmpty().compareTo(id1.orEmpty())
                    }
                } else pattern1.compareTo(pattern2)
            } else patternLength2 - patternLength1
        } else priority1 - priority2
    }

    override fun toString(): String {
        return if (id != null) {
            "$expression ($id)"
        } else expression
    }

    companion object {
        const val DEFAULT_PRIORITY = 100
    }
}