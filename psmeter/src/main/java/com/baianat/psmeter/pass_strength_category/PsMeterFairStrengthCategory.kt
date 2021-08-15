package com.baianat.psmeter.pass_strength_category

import com.baianat.psmeter.pass_strength_decorator.PsMeterStateDecorator
import com.baianat.psmeter.R

class PsMeterFairStrengthCategory(override var psMeterStateDecorator:PsMeterStateDecorator) : PsMeterStrengthCategory() {

    override fun strengthRatio(): Int = 60
    override fun strengthMessage(): Int {
        return R.string.fair
    }

    override fun strengthPhase(): PsMeterStrengthCategoryEnum {
        return PsMeterStrengthCategoryEnum.FAIR
    }
}