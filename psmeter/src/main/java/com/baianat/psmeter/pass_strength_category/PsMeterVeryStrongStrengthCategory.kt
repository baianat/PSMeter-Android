package com.baianat.psmeter.pass_strength_category

import com.baianat.psmeter.pass_strength_decorator.PsMeterStateDecorator
import com.baianat.psmeter.R

class PsMeterVeryStrongStrengthCategory(override var psMeterStateDecorator:PsMeterStateDecorator) : PsMeterStrengthCategory() {

    override fun strengthRatio(): Int = 100
    override fun strengthMessage(): Int {
        return R.string.very_strong
    }

    override fun strengthPhase(): PsMeterStrengthCategoryEnum {
        return PsMeterStrengthCategoryEnum.VERY_STRONG
    }
}