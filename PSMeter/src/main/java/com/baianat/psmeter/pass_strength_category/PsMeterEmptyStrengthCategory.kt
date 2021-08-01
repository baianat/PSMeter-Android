package com.baianat.psmeter.pass_strength_category

import com.baianat.psmeter.R
import com.baianat.psmeter.pass_strength_decorator.PsMeterStateDecorator

class PsMeterEmptyStrengthCategory(override var psMeterStateDecorator: PsMeterStateDecorator) :
    PsMeterStrengthCategory() {
    override fun strengthRatio(): Int = 0

    override fun strengthMessage(): Int {
        return R.string.empty
    }

    override fun strengthPhase(): PsMeterStrengthCategoryEnum {
        return PsMeterStrengthCategoryEnum.EMPTY
    }
}