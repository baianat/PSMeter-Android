package com.baianat.psmeter.pass_estimator

import com.baianat.psmeter.pass_strength_category.PsMeterStrengthCategoryEnum
import me.gosimple.nbvcxz.Nbvcxz

class PsMeterInitialEstimator : PsMeterEstimator {
    override fun estimatePassword(pass: String): PsMeterStrengthCategoryEnum {
        return if (pass.isEmpty()) {
            PsMeterStrengthCategoryEnum.EMPTY
        } else {
            val score = Nbvcxz().estimate(pass).basicScore + 1
            PsMeterStrengthCategoryEnum.values()[score]
        }
    }
}