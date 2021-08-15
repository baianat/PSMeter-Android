package com.baianat.psmeter.pass_strength_category

import com.baianat.psmeter.pass_strength_decorator.PsMeterStateDecorator
import com.baianat.psmeter.R

class PsMeterStrongStrengthCategory(override var psMeterStateDecorator:PsMeterStateDecorator) : PsMeterStrengthCategory() {

    override fun strengthRatio(): Int = 80
    override fun strengthMessage(): Int {
        return R.string.strong
    }

    override fun strengthPhase(): PsMeterStrengthCategoryEnum {
        return PsMeterStrengthCategoryEnum.STRONG
    }
}