package com.baianat.psmeter.pass_strength_handler

import com.baianat.psmeter.pass_estimator.PsMeterEstimator
import com.baianat.psmeter.pass_strength_category.*
import com.baianat.psmeter.pass_strength_decorator.PsMeterDecoratorBuilder

object PsMeterStrengthHandler {

    fun getProperPassStrength(
        pass: String,
        psMeterDecoratorBuilder: PsMeterDecoratorBuilder,
        psEstimator: PsMeterEstimator
    ): PsMeterStrengthCategory {
        return when (psEstimator.estimatePassword(pass)) {
            PsMeterStrengthCategoryEnum.EMPTY -> {
                PsMeterEmptyStrengthCategory(psMeterDecoratorBuilder.emptyStateDecorator)
            }
            PsMeterStrengthCategoryEnum.VERY_WEAK -> {
                PsMeterVeryWeakStrengthCategory(psMeterDecoratorBuilder.veryWeakStateDecorator)
            }
            PsMeterStrengthCategoryEnum.Weak -> {
                PsMeterWeakStrengthCategory(psMeterDecoratorBuilder.weakStateDecorator)
            }
            PsMeterStrengthCategoryEnum.FAIR -> {
                PsMeterFairStrengthCategory(psMeterDecoratorBuilder.fairStateDecorator)
            }
            PsMeterStrengthCategoryEnum.STRONG -> {
                PsMeterStrongStrengthCategory(psMeterDecoratorBuilder.strongStateDecorator)
            }
            else -> {
                PsMeterVeryStrongStrengthCategory(psMeterDecoratorBuilder.veryStrongStateDecorator)
            }
        }
    }
}