package com.baianat.psmeter.pass_estimator

import com.baianat.psmeter.pass_strength_category.PsMeterStrengthCategoryEnum


interface PsMeterEstimator {
    fun estimatePassword(pass: String): PsMeterStrengthCategoryEnum
}